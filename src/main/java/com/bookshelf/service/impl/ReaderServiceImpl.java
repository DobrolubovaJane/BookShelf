package com.bookshelf.service.impl;

import com.bookshelf.entity.Reader;
import com.bookshelf.mapper.ReaderMapper;
import com.bookshelf.service.ReaderService;
import com.bookshelf.repository.ReaderRepository;
import io.swagger.model.AddReaderRequest;
import io.swagger.model.ReaderModel;
import io.swagger.model.UpdateReaderRequest;
import io.swagger.model.ReadersListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public ReadersListModel getAllReaders() {
        return ReaderMapper.mapReadersToReadersListModel(readerRepository.findAll());
    }

    @Override
    public ReaderModel getReaderById(UUID id) {
        return ReaderMapper.mapReaderToReaderModel(readerRepository.findById(id).get());
    }

    @Override
    public ReaderModel addReader(AddReaderRequest request) {
        Reader reader = ReaderMapper.mapAddReaderRequestToReader(request);
        return ReaderMapper.mapReaderToReaderModel(readerRepository.saveAndFlush(reader));
    }

    @Override
    public void deleteReader(UUID id) {
        Reader reader = readerRepository.findById(id).get();
        readerRepository.delete(reader);
    }

    @Override
    public ReaderModel updateReader(UUID id, UpdateReaderRequest request) {
        Reader reader = readerRepository.findById(id).get();
        return ReaderMapper.mapReaderToReaderModel(readerRepository.saveAndFlush(reader));
    }
}
