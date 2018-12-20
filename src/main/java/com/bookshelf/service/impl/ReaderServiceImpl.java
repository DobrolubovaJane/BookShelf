package com.bookshelf.service.impl;

import com.bookshelf.date.DateTime;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.DeliveryDesk;
import com.bookshelf.entity.Reader;
import com.bookshelf.exception.BadRequestException;
import com.bookshelf.exception.NotFoundException;
import com.bookshelf.mapper.ReaderMapper;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.DeliveryDeskRepository;
import com.bookshelf.service.ReaderService;
import com.bookshelf.repository.ReaderRepository;
import io.swagger.model.AddReaderRequest;
import io.swagger.model.ReaderModel;
import io.swagger.model.UpdateReaderRequest;
import io.swagger.model.ReadersListModel;
import io.swagger.model.TakeBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderServiceImpl implements ReaderService {

    private ReaderRepository readerRepository;

    private BookRepository bookRepository;

    private DeliveryDeskRepository deliveryDeskRepository;

    private DateTime dateTime;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository,
                             BookRepository bookRepository,
                             DeliveryDeskRepository deliveryDeskRepository,
                             DateTime dateTime) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.deliveryDeskRepository = deliveryDeskRepository;
        this.dateTime = dateTime;
    }

    @Override
    public ReadersListModel getAllReaders() {
        return ReaderMapper.mapReadersToReadersListModel(readerRepository.findAll());
    }

    @Override
    public ReaderModel getReaderById(UUID id) {
        return ReaderMapper.mapReaderToReaderModel(findReaderById(id));
    }

    @Override
    public ReaderModel addReader(AddReaderRequest request) {
        Reader reader = ReaderMapper.mapAddReaderRequestToReader(request);
        return ReaderMapper.mapReaderToReaderModel(readerRepository.saveAndFlush(reader));
    }

    @Override
    public void deleteReader(UUID id) {
        readerRepository.delete(findReaderById(id));
    }

    @Override
    public ReaderModel updateReader(UUID id, UpdateReaderRequest request) {
        Reader reader = ReaderMapper.mapUpdateReaderRequestToReader(findReaderById(id), request);
        return ReaderMapper.mapReaderToReaderModel(readerRepository.saveAndFlush(reader));
    }

    @Override
    public void returnBook(UUID readerId, TakeBookRequest request) {
        UUID bookId = request.getBookId();
        Long duration = getDuration(readerId, bookId);

        Book book = findBookById(bookId);
        book.setAllTime(book.getAllTime() + duration);
        book.setCurrentReader(null);
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void takeBook(UUID readerId, TakeBookRequest request) {
        UUID bookId = request.getBookId();
        Book book = findBookById(bookId);
        if (book.getCurrentReader() != null) {
            throw new BadRequestException("Current book is already being read!");
        }

        Reader reader = findReaderById(readerId);
        createDeliveryDesk(book, reader);

        book.incCountOfReaders();
        book.setCurrentReader(reader);
        bookRepository.saveAndFlush(book);
    }

    private Reader findReaderById(UUID id) {
        Optional<Reader> reader = readerRepository.findById(id);
        if (!reader.isPresent()) {
            throw new NotFoundException("Please enter correct reader id!");
        }
        return reader.get();
    }

    private Book findBookById(UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new NotFoundException("Please enter correct book id!");
        }
        return book.get();
    }

    private Long getDuration(UUID readerId, UUID bookId) {
        Optional<DeliveryDesk> deliveryDeskOptional = deliveryDeskRepository.findNotClosedDelivery(readerId, bookId);
        if (!deliveryDeskOptional.isPresent()) {
            throw new NotFoundException("Please enter correct reader id and book id!");
        }

        DeliveryDesk deliveryDesk = deliveryDeskOptional.get();
        deliveryDesk.setEndDate(dateTime.now());
        deliveryDeskRepository.saveAndFlush(deliveryDesk);
        return deliveryDesk.getEndDate().getTime() - deliveryDesk.getStartDate().getTime();
    }

    private void createDeliveryDesk(Book book, Reader reader) {
        DeliveryDesk deliveryDesk = new DeliveryDesk();
        deliveryDesk.setReader(reader);
        deliveryDesk.setBook(book);
        deliveryDesk.setStartDate(dateTime.now());
        deliveryDeskRepository.saveAndFlush(deliveryDesk);
    }
}
