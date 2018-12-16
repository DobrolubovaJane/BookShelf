package com.bookshelf.repository;

import com.bookshelf.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface ReaderRepository extends JpaRepository<Reader, UUID> {
}
