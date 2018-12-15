package com.tinkoff.test.service;

import com.tinkoff.test.entity.*;

import java.util.Date;
import java.util.List;

public interface DeliveryDeskService {
    public List<DeliveryDesk> getAllRecords();
    public DeliveryDesk startRead(Integer readerId, Integer bookId);
    public DeliveryDesk endRead(Integer deliveryDeskId);
    public Integer getCountOfReadersByBookId(Book book);
//    public Date getAverageTimeByBookId(Integer id);
}
