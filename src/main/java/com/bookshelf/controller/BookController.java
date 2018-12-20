package com.bookshelf.controller;

import com.bookshelf.service.BookService;
import com.bookshelf.service.impl.GetAllBooksFilterModel;
import io.swagger.model.AverageTimeModel;
import io.swagger.model.BookModel;
import io.swagger.model.BooksListModel;
import io.swagger.model.BooksPopularityModel;
import io.swagger.model.UpdateBookRequest;
import io.swagger.model.AddBookRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class BookController implements io.swagger.api.BooksApi {
    private static final Logger LOG = Logger.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    @Override
    public ResponseEntity<BookModel> addBook(@Valid AddBookRequest request) {
        return new ResponseEntity<>(bookService.addBook(request),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BooksListModel> getAll(@Valid String Name) {
        GetAllBooksFilterModel filterModel = new GetAllBooksFilterModel();
        filterModel.setName(Name);
        return new ResponseEntity<>(bookService.getAllBooks(filterModel),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookModel> getById(UUID id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<BookModel> updateBook(UUID id, @Valid UpdateBookRequest request) {
        return new ResponseEntity<>(bookService.updateBook(id, request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteBook(UUID id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BooksPopularityModel> getPopularity(UUID id) {
        return new ResponseEntity<>(bookService.getPopularity(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AverageTimeModel> getAverageTimeOfReading(UUID id) {
        return new ResponseEntity<>(bookService.getAverageTimeOfReading(id), HttpStatus.OK);
    }


}

