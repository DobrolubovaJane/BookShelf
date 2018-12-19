package com.bookshelf.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_reader")
    private Reader currentReader;

    private Integer countOfReaders = 0;

    private Long allTime = Long.valueOf(0);

    public Book() {
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader currentReader) {
        this.currentReader = currentReader;
    }
    public Long getAverageTime() {
        return allTime/countOfReaders;
    }

    public Integer getCountOfReaders() {
        return countOfReaders;
    }

    public void incCountOfReaders() {
        this.countOfReaders ++;
    }

    public Long getAllTime() {
        return allTime;
    }

    public void setAllTime(Long allTimeInMinutes) {
        this.allTime = allTimeInMinutes;
    }

}
