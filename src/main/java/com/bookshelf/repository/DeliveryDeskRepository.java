package com.bookshelf.repository;

import com.bookshelf.entity.DeliveryDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface DeliveryDeskRepository extends JpaRepository<DeliveryDesk, Integer> {

    @Query("Select d from DeliveryDesk d where d.reader.id = :reader_id and d.book.id = :book_id and d.endDate = null")
    public Optional<DeliveryDesk> findNotClosedDelivery(@Param("reader_id") UUID readerId, @Param("book_id") UUID bookId);
}

