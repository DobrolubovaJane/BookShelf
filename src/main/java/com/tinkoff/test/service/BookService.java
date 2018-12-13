package com.tinkoff.test.service;

import com.tinkoff.test.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    public List<Book> getAllBooks();
    public Book getBookByName(String name);
    public void addBook(Book book);
    public void deleteBook(Integer id);
    public void updateBook(Book book);

}
