package com.tinkoff.test.controller;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.entity.Reader;
import com.tinkoff.test.service.impl.BookServiceImpl;
import com.tinkoff.test.service.impl.ReaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    @Autowired
    private ReaderServiceImpl readerService;

    @GetMapping()
    public List<Reader> getAllReader() {
        return readerService.getAllReaders();
    }

    @PostMapping()
    public Reader addBook(@RequestBody Reader reader) {
        readerService.addReader(reader);
        return reader;
    }
}
