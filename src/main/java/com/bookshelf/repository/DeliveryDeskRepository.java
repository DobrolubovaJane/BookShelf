package com.bookshelf.repository;

import com.bookshelf.entity.DeliveryDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
@Transactional
public interface DeliveryDeskRepository extends JpaRepository<DeliveryDesk, Integer> {

    @Query("Select d from DeliveryDesk d where d.reader_id =: reader_id and d.book_id =: book_id")
    public DeliveryDesk findByReaderAndBookIds(@Param("reader_id") UUID reader_id, @Param("book_id") UUID book_id);

    @Query("select count(d.book) from DeliveryDesk d where d.book =: id")
    Integer getCountOfReaders(@Param("id") UUID id);

    @Query("select avg(d.start_date) as avg from DeliveryDesk d where d.book_id =: book_id ")
    Date getAverageStartTime(@Param("book_id") UUID book_id);

    @Query("select avg(d.end_time) as avg from DeliveryDesk d where d.book_id =: book_id ")
    Date getAverageEndTime(@Param("book_id") UUID book_id);
}
