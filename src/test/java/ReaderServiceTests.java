import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Reader;
import com.bookshelf.exception.BadRequestException;
import com.bookshelf.exception.NotFoundException;
import com.bookshelf.repository.ReaderRepository;
import com.bookshelf.service.impl.ReaderServiceImpl;
import com.flextrade.jfixture.JFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import io.swagger.model.AddReaderRequest;
import io.swagger.model.ReaderModel;
import io.swagger.model.UpdateReaderRequest;
import io.swagger.model.ReadersListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class })
@SpringBootTest
@WebAppConfiguration
public class ReaderServiceTests {
    @Autowired
    ReaderServiceImpl sut;

    @Autowired
    ReaderRepository repository;

    @Test
    public void can_add_new_reader() {
        JFixture fixture = new JFixture();

        AddReaderRequest request = fixture.create(AddReaderRequest.class);

        ReaderModel result = sut.addReader(request);

        Assert.assertNotNull(result);

        Optional<Reader> stored = repository.findById(result.getId());

        Assert.assertTrue(stored.isPresent());

        Reader storedReader = stored.get();

        Assert.assertNotNull(stored);
        Assert.assertEquals(request.getName(), storedReader.getName());
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_try_create_reader_without_name() {
        JFixture fixture = new JFixture();
        AddReaderRequest request = fixture.create(AddReaderRequest.class);

        request.setName("");

        sut.addReader(request);
    }

    @Test
    public void can_update_new_reader() {
        JFixture fixture = new JFixture();

        Reader existing = repository.saveAndFlush(new Reader(fixture.create(String.class)));
        UpdateReaderRequest request = fixture.create(UpdateReaderRequest.class);

        ReaderModel result = sut.updateReader(existing.getId(), request);

        Assert.assertNotNull(result);

        Optional<Reader> stored = repository.findById(result.getId());

        Assert.assertTrue(stored.isPresent());

        Reader storedReader = stored.get();

        Assert.assertNotNull(stored);
        Assert.assertEquals(request.getName(), storedReader.getName());
    }

    @Test(expected = NotFoundException.class)
    public void throws_when_try_update_not_existing_reader() {
        JFixture fixture = new JFixture();

        UpdateReaderRequest request = fixture.create(UpdateReaderRequest.class);

        sut.updateReader(UUID.randomUUID(), request);
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_try_update_reader_without_name() {
        JFixture fixture = new JFixture();

        Reader existing = repository.saveAndFlush(new Reader(fixture.create(String.class)));

        UpdateReaderRequest request = fixture.create(UpdateReaderRequest.class);

        request.setName("");

        sut.updateReader(existing.getId(), request);
    }

    @Test
    public void can_get_reader_by_id() {
        JFixture fixture = new JFixture();

        Reader expected = repository.saveAndFlush(new Reader(fixture.create(String.class)));

        ReaderModel actual = sut.getReaderById(expected.getId());

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    @Test(expected = NotFoundException.class)
    public void throw_when_try_get_not_existing_book() {
        sut.getReaderById(UUID.randomUUID());
    }

    @Test
    public void can_get_reader_list() {
        JFixture fixture = new JFixture();

        List<Reader> expectedList = new ArrayList<>();
        expectedList.add(repository.save(new Reader(fixture.create(String.class))));
        expectedList.add(repository.save(new Reader(fixture.create(String.class))));
        expectedList.add(repository.saveAndFlush(new Reader(fixture.create(String.class))));

        ReadersListModel actualList = sut.getAllReaders();

        Assert.assertNotNull(actualList);
        Assert.assertEquals((Integer)3, actualList.getTotal());
        Assert.assertEquals(3, actualList.getItems().size());
        Assert.assertEquals(expectedList.get(0).getId(), actualList.getItems().get(0).getId());
    }

    @Test(expected = NotFoundException.class)
    public void can_delete_reader_by_id() {
        JFixture fixture = new JFixture();

        Reader expected = repository.saveAndFlush(new Reader(fixture.create(String.class)));

        sut.deleteReader(expected.getId());
        sut.getReaderById(expected.getId());
    }

    @Test(expected = NotFoundException.class)
    public void throw_when_try_delete_not_existing_reader() {
        sut.deleteReader(UUID.randomUUID());
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }
}
