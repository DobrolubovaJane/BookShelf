package com.tinkoff.test.service;


import com.tinkoff.test.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
    public List<Reader> getAllReaders();
    public Optional<Reader> getReaderById(Integer id);
    public void addReader(Reader reader);
    public void deleteReader(Reader reader);
    public void updateReader(Reader reader);
}
