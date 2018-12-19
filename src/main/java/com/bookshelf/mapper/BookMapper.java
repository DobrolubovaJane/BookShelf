package com.bookshelf.mapper;

import com.bookshelf.entity.Book;
import com.bookshelf.exception.BadRequestException;
import io.swagger.model.BookModel;
import io.swagger.model.BooksListModel;
import io.swagger.model.UpdateBookRequest;
import io.swagger.model.AddBookRequest;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    private BookMapper(){}

    public  static Book mapAddBookRequestToBook(AddBookRequest request) {
        String name = request.getName();
        String author = request.getAuthor();

        if (name == null || name.isEmpty()) {
            throw new BadRequestException("name is required");
        }

        if (author == null || author.isEmpty()) {
            throw new BadRequestException("author is required");
        }

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        return book;
    }


    public static BooksListModel mapBooksToBooksListModel(List<Book> books) {
        BooksListModel booksListModel = new BooksListModel();
        List<BookModel> bookModels = books.stream().map(book -> {
            BookModel bookModel = new BookModel();
            bookModel.setId(book.getId());
            bookModel.setName(book.getName());
            bookModel.setAuthor(book.getAuthor());
            return bookModel;
        }).collect(Collectors.toList());
        booksListModel.setItems(bookModels);
        booksListModel.setTotal(bookModels.size());
        return booksListModel;
    }

    public static BookModel mapBookToBookModel(Book book) {
        BookModel bookModel = new BookModel();
        bookModel.setId(book.getId());
        bookModel.setName(book.getName());
        bookModel.setAuthor(book.getAuthor());
        return bookModel;
    }

    public static Book mapUpdateBookRequestToBook(Book book, UpdateBookRequest request) {

        String name = request.getName();
        String author = request.getAuthor();

        if (name == null || name.isEmpty()) {
            throw new BadRequestException("name is required");
        }

        if (author == null || author.isEmpty()) {
            throw new BadRequestException("author is required");
        }

        book.setName(name);
        book.setAuthor(author);
        return book;
    }
}
