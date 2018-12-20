import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import com.flextrade.jfixture.JFixture;
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
public class BookTests {
    @Test
    public void can_add_new_book() {
        JFixture fixture = new JFixture();

        Book book = fixture.create(Book.class);

        Assert.assertNotNull(book);
    }

    @Test
    public void can_increment_count_of_readers() {
        JFixture fixture = new JFixture();

        Book book = fixture.create(Book.class);
        book.incCountOfReaders();

        Assert.assertEquals(1L, book.getCountOfReaders().longValue());
    }

    @Test
    public void can_get_average_time() {
        JFixture fixture = new JFixture();

        Book book = fixture.create(Book.class);
        book.setAllTime(2000L);
        book.incCountOfReaders();
        book.incCountOfReaders();

        Assert.assertEquals(1000L, book.getAverageTime().longValue());
    }

}
