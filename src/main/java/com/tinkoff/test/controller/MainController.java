package com.tinkoff.test.controller;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/")
    public String index() {
        bookService.addBook(new Book("name1","author1"));
        return "index";

    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        System.out.println("getAllBooks " + bookService);
        model.addAttribute("books", bookService.getAllBooks());
        return "books";

    }
}
