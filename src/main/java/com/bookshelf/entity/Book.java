package com.bookshelf.entity;

import javax.persistence.*;
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
    private String name;
    private String author;
    private Integer countOfReaders = 0;
    private Long allTimeInMinutes = Long.valueOf(0);

    public Long getAverageTime() {
        return allTimeInMinutes/countOfReaders;
    }
    public Integer getCountOfReaders() {
        return countOfReaders;
    }

    public void incCountOfReaders() {
        this.countOfReaders ++;
    }

    public Long getAllTimeInMinutes() {
        return allTimeInMinutes;
    }

    public void setAllTimeInMinutes(Long allTimeInMinutes) {
        this.allTimeInMinutes = allTimeInMinutes;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<DeliveryDesk> deliveryDesk = new ArrayList<>();

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

    public List<DeliveryDesk> getDeliveryDesk() {
        return deliveryDesk;
    }

    public void setDeliveryDesk(List<DeliveryDesk> deliveryDesk) {
        this.deliveryDesk = deliveryDesk;
    }
}
