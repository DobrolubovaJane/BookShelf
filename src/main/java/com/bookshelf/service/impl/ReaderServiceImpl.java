package com.bookshelf.service.impl;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.DeliveryDesk;
import com.bookshelf.entity.Reader;
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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DeliveryDeskRepository deliveryDeskRepository;

    @Override
    public ReadersListModel getAllReaders() {
        return ReaderMapper.mapReadersToReadersListModel(readerRepository.findAll());
    }

    @Override
    public ReaderModel getReaderById(UUID id) {
        return ReaderMapper.mapReaderToReaderModel(readerRepository.findById(id).get());
    }

    @Override
    public ReaderModel addReader(AddReaderRequest request) {
        Reader reader = ReaderMapper.mapAddReaderRequestToReader(request);
        return ReaderMapper.mapReaderToReaderModel(readerRepository.saveAndFlush(reader));
    }

    @Override
    public void deleteReader(UUID id) {
        Reader reader = readerRepository.findById(id).get();
        readerRepository.delete(reader);
    }

    @Override
    public ReaderModel updateReader(UUID id, UpdateReaderRequest request) {
        Reader reader = readerRepository.findById(id).get();
        reader.setName(request.getName());
        return ReaderMapper.mapReaderToReaderModel(readerRepository.saveAndFlush(reader));
    }

    @Override
    public void returnBook(UUID readerId, TakeBookRequest request) {
        UUID bookId = UUID.fromString(request.getBookId());
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findByReaderAndBookIds(readerId, bookId).get();
        deliveryDesk.setEndDate(new Date());
        Long time = deliveryDesk.getEndDate().getTime() - deliveryDesk.getStartDate().getTime();

        Book book = bookRepository.findById(bookId).get();
        book.setAllTimeInMinutes(book.getAllTimeInMinutes() + time);
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void takeBook(UUID readerId, TakeBookRequest request) {
        DeliveryDesk deliveryDesk = new DeliveryDesk();
        deliveryDesk.setReader(readerRepository.findById(readerId).get());
        Book book = bookRepository.findById(UUID.fromString(request.getBookId())).get();
        deliveryDesk.setBook(book);
        deliveryDesk.setStartDate(new Date());
        deliveryDeskRepository.saveAndFlush(deliveryDesk);
        book.incCountOfReaders();
        bookRepository.saveAndFlush(book);
    }
}
