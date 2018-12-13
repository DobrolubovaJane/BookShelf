package com.tinkoff.test.service;


import com.tinkoff.test.entity.Reader;

import java.util.List;

public interface ReaderService {
    public List<Reader> getAllReaders();
    public void addReader(String name);
    public void deleteReader(Integer id);
    public void updateReader(Integer id, String name);
}
