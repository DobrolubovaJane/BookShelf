package com.tinkoff.test.service.impl;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.repository.BookRepository;
import com.tinkoff.test.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book getBookByName(String name) {
        return bookRepository.findByBookName(name);
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        System.out.println("addBook");
        LOG.debug("addBook " + book.toString());
        bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {

    }

    @Override
    @Transactional
    public void updateBook(Book book) {

    }
}
