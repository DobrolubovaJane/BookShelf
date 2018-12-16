package com.bookshelf.service.impl;

import com.bookshelf.repository.BookRepository;
import com.bookshelf.entity.Book;
import com.bookshelf.mapper.BookMapper;
import com.bookshelf.service.BookService;
import io.swagger.model.AverageTimeModel;
import io.swagger.model.BooksPopularityModel;
import io.swagger.model.BookModel;
import io.swagger.model.BooksListModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

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
    public BookModel addBook(io.swagger.model.AddBookRequest request) {
        Book book = BookMapper.mapAddBookRequestToBook(request);
        LOG.debug("addBook " + book.toString());
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
    public BookModel updateBook(UUID id, io.swagger.model.UpdateBookRequest request) {

        Book book = BookMapper.mapUpdateBookRequestToBook(bookRepository.findById(id).get(), request);
        return BookMapper.mapBookToBookModel(bookRepository.saveAndFlush(book));
    }

    @Override
    public BooksPopularityModel getPopularity(UUID id) {
        BooksPopularityModel model = new BooksPopularityModel();
        model.setReadersCount(bookRepository.getCountOfReaders(id));
        return model;
    }

    @Override
    public AverageTimeModel getAverageTimeOfReading(UUID id) {
        Date date = bookRepository.getAverageTime(id);
        AverageTimeModel model = new AverageTimeModel();
        return null;
    }
}
