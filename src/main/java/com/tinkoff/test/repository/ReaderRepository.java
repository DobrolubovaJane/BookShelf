package com.tinkoff.test.repository;

import com.tinkoff.test.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
}
