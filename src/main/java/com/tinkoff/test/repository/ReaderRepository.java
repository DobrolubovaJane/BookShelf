package com.tinkoff.test.repository;

import com.tinkoff.test.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
}
