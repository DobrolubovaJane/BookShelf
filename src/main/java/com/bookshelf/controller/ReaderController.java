package com.bookshelf.controller;

import com.bookshelf.service.impl.ReaderServiceImpl;
import io.swagger.model.ReaderModel;
import io.swagger.model.ReadersListModel;
import io.swagger.model.AddReaderRequest;
import io.swagger.model.UpdateReaderRequest;
import io.swagger.model.TakeBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class ReaderController implements io.swagger.api.ReadersApi {
    @Autowired
    private ReaderServiceImpl readerService;


    @Override
    public ResponseEntity<ReaderModel> addReader(@Valid AddReaderRequest request) {
        return new ResponseEntity<>(readerService.addReader(request),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReadersListModel> deleteReader(UUID id) {
        readerService.deleteReader(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReadersListModel> getAll() {
        return new ResponseEntity<>(readerService.getAllReaders(),HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ReaderModel> getById(UUID id) {
        return new ResponseEntity<>(readerService.getReaderById(id),HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ReaderModel> updateReader(UUID id, @Valid UpdateReaderRequest request) {
        return new ResponseEntity<>(readerService.updateReader(id, request),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> returnBook(UUID id, @Valid TakeBookRequest request) {
        readerService.returnBook(id, request);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> takeBook(UUID id, @Valid TakeBookRequest request) {
        readerService.takeBook(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
