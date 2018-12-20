import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import com.bookshelf.exception.BadRequestException;
import com.bookshelf.exception.NotFoundException;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.service.impl.BookServiceImpl;
import com.bookshelf.service.impl.GetAllBooksFilterModel;
import com.flextrade.jfixture.JFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import io.swagger.model.AddBookRequest;
import io.swagger.model.BookModel;
import io.swagger.model.UpdateBookRequest;
import io.swagger.model.BooksListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class })
@SpringBootTest
@WebAppConfiguration
public class BookServiceTests {

    @Autowired
    private BookServiceImpl sut;

    @Autowired
    private BookRepository repository;

    @Test
    public void can_add_new_book() {
        JFixture fixture = new JFixture();

        AddBookRequest request = fixture.create(AddBookRequest.class);

        BookModel result = sut.addBook(request);

        Assert.assertNotNull(result);

        Optional<Book> stored = repository.findById(result.getId());

        Assert.assertTrue(stored.isPresent());

        Book storedBook = stored.get();

        Assert.assertNotNull(stored);
        Assert.assertEquals(request.getName(), storedBook.getName());
        Assert.assertEquals(request.getAuthor(), storedBook.getAuthor());
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_try_create_book_without_name() {
        JFixture fixture = new JFixture();
        AddBookRequest request = fixture.create(AddBookRequest.class);

        request.setName("");

        sut.addBook(request);
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_try_create_book_without_author() {
        JFixture fixture = new JFixture();
        AddBookRequest request = fixture.create(AddBookRequest.class);

        request.setAuthor("");

        sut.addBook(request);
    }

    @Test
    public void can_update_book() {
        JFixture fixture = new JFixture();

        Book existing = repository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        UpdateBookRequest request = fixture.create(UpdateBookRequest.class);

        BookModel result = sut.updateBook(existing.getId(), request);

        Assert.assertNotNull(result);

        Optional<Book> stored = repository.findById(result.getId());

        Assert.assertTrue(stored.isPresent());

        Book storedBook = stored.get();

        Assert.assertNotNull(stored);
        Assert.assertEquals(request.getName(), storedBook.getName());
        Assert.assertEquals(request.getAuthor(), storedBook.getAuthor());
    }

    @Test(expected = NotFoundException.class)
    public void throws_when_try_update_not_existing_book() {
        JFixture fixture = new JFixture();

        UpdateBookRequest request = fixture.create(UpdateBookRequest.class);

        sut.updateBook(UUID.randomUUID(), request);
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_try_update_book_without_name() {
        JFixture fixture = new JFixture();

        Book existing = repository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        UpdateBookRequest request = fixture.create(UpdateBookRequest.class);

        request.setName("");

        sut.updateBook(existing.getId(), request);
    }

    @Test(expected = BadRequestException.class)
    public void throw_when_try_update_book_without_author() {
        JFixture fixture = new JFixture();

        Book existing = repository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        UpdateBookRequest request = fixture.create(UpdateBookRequest.class);

        request.setAuthor("");

        sut.updateBook(existing.getId(), request);
    }

    @Test
    public void can_get_book_by_id() {
        JFixture fixture = new JFixture();

        Book expected = repository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        BookModel actual = sut.getBookById(expected.getId());

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getAuthor(), actual.getAuthor());
    }

    @Test(expected = NotFoundException.class)
    public void throw_when_try_get_not_existing_book() {
        sut.getBookById(UUID.randomUUID());
    }

    @Test
    public void can_get_book_list() {
        JFixture fixture = new JFixture();

        List<Book> expectedList = new ArrayList<Book> ();
        expectedList.add(repository.save(new Book(fixture.create(String.class), fixture.create(String.class))));
        expectedList.add(repository.save(new Book(fixture.create(String.class), fixture.create(String.class))));
        expectedList.add(repository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class))));

        BooksListModel actualList = sut.getAllBooks(new GetAllBooksFilterModel());

        Assert.assertNotNull(actualList);
        Assert.assertEquals((Integer)3, actualList.getTotal());
        Assert.assertEquals(3, actualList.getItems().size());
        Assert.assertEquals(expectedList.get(0).getId(), actualList.getItems().get(0).getId());
    }

    @Test
    public void can_find_book_by_name() {

        JFixture fixture = new JFixture();

        String expectedBookName = "expectedName";

        List<Book> expectedList = new ArrayList<Book> ();
        expectedList.add(repository.save(new Book(fixture.create(String.class), fixture.create(String.class))));
        expectedList.add(repository.save(new Book(fixture.create(String.class), fixture.create(String.class))));
        expectedList.add(repository.saveAndFlush(new Book(expectedBookName, fixture.create(String.class))));

        GetAllBooksFilterModel filterModel = new GetAllBooksFilterModel();
        filterModel.setName("expectedName");
        BooksListModel actualList = sut.getAllBooks(filterModel);

        Assert.assertNotNull(actualList);
        Assert.assertEquals((Integer)1, actualList.getTotal());
        Assert.assertEquals(1, actualList.getItems().size());
        Assert.assertEquals(expectedBookName, actualList.getItems().get(0).getName());
    }

    @Test
    public void return_empty_list_when_books_not_found_by_searchQuery() {
        JFixture fixture = new JFixture();

        String expectedBookName = "expectedName";

        List<Book> expectedList = new ArrayList<Book> ();
        expectedList.add(repository.save(new Book(expectedBookName, fixture.create(String.class))));
        expectedList.add(repository.save(new Book(expectedBookName, fixture.create(String.class))));
        expectedList.add(repository.saveAndFlush(new Book(expectedBookName, fixture.create(String.class))));

        GetAllBooksFilterModel filterModel = new GetAllBooksFilterModel();
        filterModel.setName("notExpectedName");
        BooksListModel actualList = sut.getAllBooks(filterModel);

        Assert.assertEquals((Integer)0, actualList.getTotal());
        Assert.assertEquals(0, actualList.getItems().size());
    }

    @Test(expected = NotFoundException.class)
    public void can_delete_book_by_id() {
        JFixture fixture = new JFixture();

        Book expected = repository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        sut.deleteBook(expected.getId());
        sut.getBookById(expected.getId());
    }

    @Test(expected = NotFoundException.class)
    public void throw_when_try_delete_not_existing_book() {
        sut.deleteBook(UUID.randomUUID());
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }
}
