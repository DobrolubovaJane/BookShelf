import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.DeliveryDesk;
import com.bookshelf.entity.Reader;
import com.bookshelf.exception.BadRequestException;
import com.bookshelf.exception.NotFoundException;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.DeliveryDeskRepository;
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
import io.swagger.model.TakeBookRequest;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class })
@SpringBootTest
@WebAppConfiguration
public class StatisticsTests {
    @Autowired
    ReaderServiceImpl sut;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    DeliveryDeskRepository deliveryDeskRepository;

    @Test
    public void can_take_a_book() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId().toString());

        sut.takeBook(reader.getId(), request);
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findByReaderAndBookIds(reader.getId(), book.getId()).get();
        Assert.assertNotNull(deliveryDesk);
        Assert.assertNotNull(deliveryDesk.getStartDate());

        deliveryDeskRepository.deleteAll();
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_take_not_free_book() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        book.setCurrentReader(reader);
        bookRepository.saveAndFlush(book);

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId().toString());

        sut.takeBook(reader.getId(), request);
        deliveryDeskRepository.deleteAll();
    }

    @Test(expected = NotFoundException.class)
    public void throw_when_try_return_a_book_which_didnot_take() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId().toString());

        sut.returnBook(reader.getId(), request);

        deliveryDeskRepository.deleteAll();
    }

    @Test
    public void can_return_a_book() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId().toString());

        sut.takeBook(reader.getId(), request);
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findByReaderAndBookIds(reader.getId(), book.getId()).get();
        Assert.assertNotNull(deliveryDesk);
        Assert.assertNotNull(deliveryDesk.getStartDate());

        sut.returnBook(reader.getId(), request);
        Date date = deliveryDesk.getEndDate();
        Assert.assertNotNull(date);

        deliveryDeskRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
    }
}
