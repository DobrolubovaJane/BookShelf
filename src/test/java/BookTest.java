import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class })
@SpringBootTest
@WebAppConfiguration
public class BookTest {
    @Test
    public void newBook() {
        Book book = new Book("name for addBook", "author for addBook");
        Assert.assertNotNull(book);
    }

    @Test
    public void incrementCountOfReaders() {
        Book book = new Book("name", "author");
        book.incCountOfReaders();

        Assert.assertEquals(1L, book.getCountOfReaders().longValue());
    }

    @Test
    public void getAverageTime() {
        Book book = new Book("name", "author");
        book.setAllTime(2000L);
        book.incCountOfReaders();
        book.incCountOfReaders();

        Assert.assertEquals(1000L, book.getAverageTime().longValue());
    }

}
