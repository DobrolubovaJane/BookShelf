package com.tinkoff.test.service.impl;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.entity.DeliveryDesk;
import com.tinkoff.test.entity.Reader;
import com.tinkoff.test.repository.BookRepository;
import com.tinkoff.test.repository.DeliveryDeskRepository;
import com.tinkoff.test.repository.ReaderRepository;
import com.tinkoff.test.service.DeliveryDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryDeskServiceImpl implements DeliveryDeskService {
    @Autowired
    private DeliveryDeskRepository deliveryDeskRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<DeliveryDesk> getAllRecords() {
        return deliveryDeskRepository.findAll();
    }

    @Override
    public DeliveryDesk startRead(Integer readerId, Integer bookId) {
        Book book = bookRepository.findById(bookId).get();
        Reader reader = readerRepository.findById(readerId).get();
       return deliveryDeskRepository.saveAndFlush(new DeliveryDesk(book, reader, new Date()));
    }

    @Override
    public DeliveryDesk endRead(Integer deliveryDeskId) {
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findById(deliveryDeskId).get();
        deliveryDesk.setEndDate(new Date());
        return deliveryDeskRepository.saveAndFlush(deliveryDesk);
    }

    @Override
    public Integer getCountOfReadersByBookId(Book book) {
        return deliveryDeskRepository.getCountOfReader(book);
    }

//    @Override
//    public Date getAverageTimeByBookId(Integer id) {
//        return deliveryDeskRepository.getAverageTime(id);
//    }
}
