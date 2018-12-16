package com.bookshelf.service;

import com.bookshelf.entity.DeliveryDesk;

import java.util.List;
import java.util.UUID;

public interface DeliveryDeskService {
    public List<DeliveryDesk> getAllRecords();
    public DeliveryDesk startRead(Integer readerId, UUID bookId);
    public DeliveryDesk endRead(Integer deliveryDeskId);
}
