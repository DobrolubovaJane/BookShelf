package com.bookshelf.service;

import io.swagger.model.BooksListModel;
import io.swagger.model.BookModel;
import io.swagger.model.AddBookRequest;
import io.swagger.model.UpdateBookRequest;
import io.swagger.model.BooksPopularityModel;
import io.swagger.model.AverageTimeModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BookService {
    public BooksListModel getAllBooks();
    public BookModel getBookById(UUID id);
    public BookModel getBookByName(String name);
    public BookModel addBook(AddBookRequest request);
    public void deleteBook(UUID id);
    public BookModel updateBook(UUID id, UpdateBookRequest request);
    public BooksPopularityModel getPopularity(UUID id);
    public AverageTimeModel getAverageTimeOfReading(UUID id);
}
