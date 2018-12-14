package com.tinkoff.test.mapper;

import com.tinkoff.test.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        return book;
    }
}
