package com.bookshelf.service.impl;

import com.bookshelf.repository.BookRepository;
import com.bookshelf.entity.Book;
import com.bookshelf.mapper.BookMapper;
import com.bookshelf.repository.BookSpecification;
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
    public BooksListModel getAllBooks(GetAllBooksFilterModel filterModel) {
        return BookMapper.mapBooksToBooksListModel(bookRepository.findAll(new BookSpecification(filterModel.getName())));
    }

    @Override
    @Transactional
    public BookModel getBookById(UUID id) {
        return BookMapper.mapBookToBookModel(bookRepository.findById(id).get());
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
        Book book = bookRepository.findById(id).get();
        model.setReadersCount(book.getCountOfReaders());
        return model;
    }

    @Override
    public AverageTimeModel getAverageTimeOfReading(UUID id) {
        Book book = bookRepository.findById(id).get();
        Long averageTime = book.getAverageTime();
        AverageTimeModel model = new AverageTimeModel();
        model.setDaysCount((int) (averageTime / (1000*60*60*24)));
        model.setHoursCount((int) (averageTime / (1000*60*60)));
        model.setMinutesCount((int) (averageTime / (1000*60)));
        return model;
    }
}
