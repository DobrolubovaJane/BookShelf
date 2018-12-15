package com.tinkoff.test.service;

import com.tinkoff.test.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    public List<Book> getAllBooks();
    public Optional<Book> getBookById(Integer d);
    public Book getBookByName(String name);
    public void addBook(Book book);
    public void deleteBook(Book book);
    public void updateBook(Book book);

}
