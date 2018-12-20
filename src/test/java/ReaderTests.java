import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Reader;
import com.flextrade.jfixture.JFixture;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.After;
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
public class ReaderTests {
    @Test
    public void can_add_new_reader() {
        JFixture fixture = new JFixture();

        Reader reader = fixture.create(Reader.class);
        Assert.assertNotNull(reader);
    }

}
