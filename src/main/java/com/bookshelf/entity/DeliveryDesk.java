package com.bookshelf.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_desk")
public class DeliveryDesk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public DeliveryDesk() {
    }

    public DeliveryDesk(Book book, Reader reader, Date startDate, Date endDate) {
        this.book = book;
        this.reader = reader;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DeliveryDesk(Book book, Reader reader, Date startDate) {
        this.book = book;
        this.reader = reader;
        this.startDate = startDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
