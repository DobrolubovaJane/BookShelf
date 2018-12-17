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

    @Query("Select d from DeliveryDesk d where d.reader.id =: reader_id and d.book.id =: book_id")
    public DeliveryDesk findByReaderAndBookIds(UUID reader_id, UUID book_id);

    @Query("select count(d.book.id) from DeliveryDesk d where d.book.id =: id")
    Integer getCountOfReaders(@Param("id") UUID id);

    @Query("select avg(d.startDate) from DeliveryDesk d where d.book.id =: book_id ")
    Date getAverageStartTime(@Param("book_id") UUID book_id);

    @Query("select avg(d.endDate) from DeliveryDesk d where d.book.id =: book_id ")
    Date getAverageEndTime(@Param("book_id") UUID book_id);
}
