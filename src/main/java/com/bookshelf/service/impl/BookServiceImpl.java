package com.bookshelf.service.impl;

import com.bookshelf.repository.BookRepository;
import com.bookshelf.entity.Book;
import com.bookshelf.mapper.BookMapper;
import com.bookshelf.repository.DeliveryDeskRepository;
import com.bookshelf.service.BookService;
import io.swagger.model.AverageTimeModel;
import io.swagger.model.BooksPopularityModel;
import io.swagger.model.BookModel;
import io.swagger.model.BooksListModel;
import org.apache.log4j.Logger;
import io.swagger.model.AddBookRequest;
import io.swagger.model.UpdateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DeliveryDeskRepository deliveryDeskRepository;

    @Override
    @Transactional
    public BooksListModel getAllBooks() {
        return BookMapper.mapBooksToBooksListModel(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookModel getBookById(UUID id) {
        return BookMapper.mapBookToBookModel(bookRepository.findById(id).get());
    }

    @Override
    @Transactional
    public BookModel getBookByName(String name) {
        return BookMapper.mapBookToBookModel(bookRepository.findByBookName(name));
    }

    @Override
    @Transactional
    public BookModel addBook(AddBookRequest request) {
        Book book = BookMapper.mapAddBookRequestToBook(request);
        return BookMapper.mapBookToBookModel(bookRepository.saveAndFlush(book));
    }

    @Override
    @Transactional
    public void deleteBook(UUID id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
    }

    @Override
    @Transactional
    public BookModel updateBook(UUID id, UpdateBookRequest request) {

        Book book = BookMapper.mapUpdateBookRequestToBook(bookRepository.findById(id).get(), request);
        return BookMapper.mapBookToBookModel(bookRepository.saveAndFlush(book));
    }

    @Override
    public BooksPopularityModel getPopularity(UUID id) {
        BooksPopularityModel model = new BooksPopularityModel();
        model.setReadersCount(deliveryDeskRepository.getCountOfReaders(id));
        return model;
    }

    @Override
    public AverageTimeModel getAverageTimeOfReading(UUID id) {
        Date startDate = deliveryDeskRepository.getAverageStartTime(id);
        Date endDate = deliveryDeskRepository.getAverageEndTime(id);
        AverageTimeModel model = new AverageTimeModel();
        model.setDaysCount(endDate.getDay() - startDate.getDay());
        model.setHoursCount(endDate.getHours() - startDate.getHours());
        model.setMinutesCount(endDate.getMinutes() - startDate.getMinutes());
        return model;
    }
}
