import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Reader;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.DeliveryDeskRepository;
import com.bookshelf.repository.ReaderRepository;
import com.bookshelf.service.impl.ReaderServiceImpl;
import com.flextrade.jfixture.JFixture;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import io.swagger.model.TakeBookRequest;


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
        readerRepository.saveAndFlush(reader);
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));
        bookRepository.saveAndFlush(book);

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId().toString());

        sut.takeBook(reader.getId(), request);

        Assert.assertEquals(1, book.getCountOfReaders().intValue());
        Assert.assertEquals(reader.getId(), book.getCurrentReader().getId());
    }
}
