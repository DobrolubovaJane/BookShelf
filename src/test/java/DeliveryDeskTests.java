import com.bookshelf.config.WebAppConfig;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.DeliveryDesk;
import com.bookshelf.entity.Reader;
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
public class DeliveryDeskTests {
    @Test
    public void can_add_new_delivery_desk() {
        JFixture fixture = new JFixture();

        DeliveryDesk deliveryDesk = fixture.create(DeliveryDesk.class);
        Assert.assertNotNull(deliveryDesk);
    }
}
