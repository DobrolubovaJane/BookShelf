package com.bookshelf.service;


import io.swagger.model.AddReaderRequest;
import io.swagger.model.ReaderModel;
import io.swagger.model.UpdateReaderRequest;
import io.swagger.model.ReadersListModel;
import io.swagger.model.TakeBookRequest;

import javax.validation.Valid;
import java.util.UUID;

public interface ReaderService {
    public ReadersListModel getAllReaders();
    public ReaderModel getReaderById(UUID id);
    public ReaderModel addReader(AddReaderRequest request);
    public void deleteReader(UUID id);
    public ReaderModel updateReader(UUID id, UpdateReaderRequest request);
    public void returnBook(UUID id, TakeBookRequest request);
    public void takeBook(UUID id, TakeBookRequest request);
}
