import com.bookshelf.config.WebAppConfig;
import com.bookshelf.date.DateTime;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import io.swagger.model.TakeBookRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class })
@SpringBootTest
@WebAppConfiguration
public class StatisticsTests {

    ReaderServiceImpl sut;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    DeliveryDeskRepository deliveryDeskRepository;

    Date date1;
    Date date2;
    Date date3;
    Date date4;


    @PrepareForTest({DateTime.class})

    @Before
    public void setUp() throws Exception {

        date1 = new Date();
        date2 = addDay(date1, 1);
        date3 = addDay(date1, 2);
        date4 = addDay(date1, 3);

        final DateTime dateTimeMock = Mockito.mock(DateTime.class);
        Mockito.when(dateTimeMock.now()).thenReturn(date1, date2, date3, date4);

        sut = new ReaderServiceImpl(readerRepository, bookRepository, deliveryDeskRepository, dateTimeMock);
    }

    @Test
    public void can_take_a_book() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId());

        sut.takeBook(reader.getId(), request);
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findNotClosedDelivery(reader.getId(), book.getId()).get();
        Assert.assertNotNull(deliveryDesk);
        Assert.assertNotNull(deliveryDesk.getStartDate());

        book = bookRepository.findById(book.getId()).get();
        Assert.assertEquals(1, book.getCountOfReaders());
        Assert.assertEquals(reader.getId(), book.getCurrentReader().getId());

    }

    @Test(expected = BadRequestException.class)
    public void throw_when_take_not_free_book() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        book.setCurrentReader(reader);
        bookRepository.saveAndFlush(book);

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId());

        sut.takeBook(reader.getId(), request);
    }

    @Test(expected = NotFoundException.class)
    public void throw_when_try_return_a_book_which_did_not_take() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId());

        sut.returnBook(reader.getId(), request);

    }

    @Test
    public void can_return_a_book() {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId());

        sut.takeBook(reader.getId(), request);
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findNotClosedDelivery(reader.getId(), book.getId()).get();
        Assert.assertNotNull(deliveryDesk);
        Assert.assertNotNull(deliveryDesk.getStartDate());

        sut.returnBook(reader.getId(), request);
        deliveryDesk = deliveryDeskRepository.findById(deliveryDesk.getId()).get();
        Assert.assertNotNull(deliveryDesk.getEndDate());

        book = bookRepository.findById(book.getId()).get();
        Assert.assertEquals(1, book.getCountOfReaders());
        Assert.assertNull(book.getCurrentReader());

    }

    @Test
    public void can_get_count_of_readers() {
        JFixture fixture = new JFixture();

        Reader reader1 = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Reader reader2 = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(book.getId());

        sut.takeBook(reader1.getId(), request);
        sut.returnBook(reader1.getId(), request);
        sut.takeBook(reader2.getId(), request);

        book = bookRepository.findById(book.getId()).get();
        Assert.assertEquals(2, book.getCountOfReaders());
        Assert.assertEquals(reader2.getId(), book.getCurrentReader().getId());

    }

    @PrepareForTest({ReaderServiceImpl.class, DeliveryDesk.class})

    @Test
    public void can_get_avarage_time() throws Exception {
        JFixture fixture = new JFixture();

        Reader reader = readerRepository.saveAndFlush(new Reader(fixture.create(String.class)));
        Book book = bookRepository.saveAndFlush(new Book(fixture.create(String.class), fixture.create(String.class)));

        readBook(reader.getId(), book.getId());
        readBook(reader.getId(), book.getId());

        book = bookRepository.findById(book.getId()).get();
        Assert.assertEquals(2, book.getCountOfReaders());
        Assert.assertEquals(Long.valueOf(1000 * 60 * 60 * 24), book.getAverageTime());
    }

    @After
    public void tearDown() throws Exception {
        deliveryDeskRepository.deleteAll();
        bookRepository.deleteAll();
        readerRepository.deleteAll();
    }

    private void readBook(UUID readerId, UUID bookId) {
        TakeBookRequest request = new TakeBookRequest();
        request.setBookId(bookId);

        sut.takeBook(readerId, request);
        sut.returnBook(readerId, request);
    }

    private static Date addDay(Date date, int days){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);

        return calendar.getTime();
    }
}
