import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.DeliveryDeskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = "com.bookshelf.repository")
@EntityScan(basePackages = "com.bookshelf.entity")
@ContextConfiguration(classes = {BookRepository.class})
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void firtTest() {
        Book book = new Book("name1", "author1");
        testEntityManager.persist(book);
        testEntityManager.flush();
        bookRepository.saveAndFlush(book);
        Book findBook = bookRepository.findByBookName("name1");

        assertThat(findBook.getName(), is(book.getName()));
    }
}
