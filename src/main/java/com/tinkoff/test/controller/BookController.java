package com.tinkoff.test.controller;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.service.impl.BookServiceImpl;
import io.swagger.model.BooksListModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
/*@RequestMapping("/api/books")*/
public class BookController implements io.swagger.api.BooksApi {
    private static final Logger LOG = Logger.getLogger(BookController.class);
    @Autowired
    private BookServiceImpl bookService;

    /*@GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping()
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }*/

    @Override
    public ResponseEntity<Void> addBook(@Valid io.swagger.model.AddBookRequest request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BooksListModel> getAll(@Valid String searchQuery) {
        List<Book> books = bookService.getAllBooks();
        BooksListModel result = new BooksListModel();
        result.total(10);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

