package com.tinkoff.test.service;

import com.tinkoff.test.entity.*;

import java.util.List;

public interface DeliveryBookService {
    public List<DeliveryDesk> getAllRecords();
    public void startRead(Reader reader, Book book);
    public void endRead();
}
