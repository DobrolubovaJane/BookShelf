package com.tinkoff.test.service.impl;

import com.tinkoff.test.entity.Reader;
import com.tinkoff.test.repository.ReaderRepository;
import com.tinkoff.test.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Optional<Reader> getReaderById(Integer id) {
        return readerRepository.findById(id);
    }

    @Override
    public void addReader(Reader reader) {
        readerRepository.saveAndFlush(reader);
    }

    @Override
    public void deleteReader(Reader reader) {
        readerRepository.delete(reader);
    }

    @Override
    public void updateReader(Reader reader) {

    }
}
