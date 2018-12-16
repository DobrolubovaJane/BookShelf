package com.bookshelf.mapper;

import com.bookshelf.entity.Book;
import io.swagger.model.BookModel;
import io.swagger.model.BooksListModel;
import io.swagger.model.UpdateBookRequest;
import io.swagger.model.AddBookRequest;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    private BookMapper(){}

    public  static Book mapAddBookRequestToBook(AddBookRequest request) {
        Book book = new Book();
        book.setName(request.getName());
        book.setAuthor(request.getAuthor());
        return book;
    }


    public static BooksListModel mapBooksToBooksListModel(List<Book> books) {
        BooksListModel booksListModel = new BooksListModel();
        List<BookModel> bookModels = books.stream().map(book -> {
            BookModel bookModel = new BookModel();
            bookModel.setName(book.getName());
            bookModel.setAuthor(book.getAuthor());
            return bookModel;
        }).collect(Collectors.toList());
        booksListModel.setItems(bookModels);
        return booksListModel;
    }

    public static BookModel mapBookToBookModel(Book book) {
        BookModel bookModel = new BookModel();
        bookModel.setName(book.getName());
        bookModel.setAuthor(book.getAuthor());
        return bookModel;
    }

    public static Book mapUpdateBookRequestToBook(Book book, UpdateBookRequest request) {
        book.setName(request.getName());
        book.setAuthor(request.getAuthor());
        return book;
    }
}
