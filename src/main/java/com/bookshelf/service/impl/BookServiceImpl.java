package com.bookshelf.service.impl;

import com.bookshelf.exception.NotFoundException;
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

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public BooksListModel getAllBooks(GetAllBooksFilterModel filterModel) {
        return BookMapper.mapBooksToBooksListModel(bookRepository.findAll(new BookSpecification(filterModel.getName())));
    }

    @Override
    @Transactional
    public BookModel getBookById(UUID id) {
        return BookMapper.mapBookToBookModel(findById(id));
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
        bookRepository.delete(findById(id));
    }

    @Override
    @Transactional
    public BookModel updateBook(UUID id, UpdateBookRequest request) {
        Book bookMapper = BookMapper.mapUpdateBookRequestToBook(findById(id), request);
        return BookMapper.mapBookToBookModel(bookRepository.saveAndFlush(bookMapper));
    }

    @Override
    public BooksPopularityModel getPopularity(UUID id) {
        Book book = findById(id);
        BooksPopularityModel model = new BooksPopularityModel();
        model.setReadersCount(book.getCountOfReaders());
        return model;
    }

    @Override
    public AverageTimeModel getAverageTimeOfReading(UUID id) {
        Book book = findById(id);
        Long averageTime = book.getAverageTime();
        AverageTimeModel model = new AverageTimeModel();
        model.setDaysCount((int) (averageTime / (1000*60*60*24)));
        model.setHoursCount((int) (averageTime / (1000*60*60)));
        model.setMinutesCount((int) (averageTime / (1000*60)));
        return model;
    }

    private Book findById(UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new NotFoundException("Please enter correct book id!");
        }
        return book.get();
    }
}
