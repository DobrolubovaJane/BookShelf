package com.tinkoff.test.controller;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class);
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping()
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }
}

