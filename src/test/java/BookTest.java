import com.bookshelf.entity.Book;
import com.bookshelf.service.impl.BookServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackages = "com.tinkoff.test.repository")
@EntityScan(basePackages = "com.tinkoff.test.entity")
@ContextConfiguration(classes = BookServiceImpl.class)
public class BookTest{

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void addBook() {
//        Book book = new Book("name1", "author1");
//        System.out.println(book);
//       System.out.println("bookService" + bookService);
//       bookService.addBook(book);
   }
}
