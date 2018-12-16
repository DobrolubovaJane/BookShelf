package com.bookshelf.service.impl;

import com.bookshelf.entity.DeliveryDesk;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.DeliveryDeskRepository;
import com.bookshelf.repository.ReaderRepository;
import com.bookshelf.service.DeliveryDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public DeliveryDesk startRead(Integer readerId, UUID bookId) {
//        Book book = bookRepository.findById(bookId).get();
//        Reader reader = readerRepository.findById(readerId).get();
//       return deliveryDeskRepository.saveAndFlush(new DeliveryDesk(book, reader, new Date()));
        return null;
    }

    @Override
    public DeliveryDesk endRead(Integer deliveryDeskId) {
        DeliveryDesk deliveryDesk = deliveryDeskRepository.findById(deliveryDeskId).get();
        deliveryDesk.setEndDate(new Date());
        return deliveryDeskRepository.saveAndFlush(deliveryDesk);
    }

//    @Override
//    public Integer getCountOfReadersByBookId(Book book) {
//        return deliveryDeskRepository.getCountOfReader(book);
//    }

//    @Override
//    public Date getAverageTimeByBookId(Integer id) {
//        return deliveryDeskRepository.getAverageTime(id);
//    }
}
