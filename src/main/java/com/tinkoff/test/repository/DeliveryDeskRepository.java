package com.tinkoff.test.repository;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.entity.DeliveryDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeliveryDeskRepository extends JpaRepository<DeliveryDesk, Integer> {

    @Query("select count(d.book) from DeliveryDesk d where d.book =: book")
    Integer getCountOfReader(@Param("book") Book book);
//
//    @Query("select avg(d.endDate - d.startDate) from DeliveryDesk d where d.book_id =: bookId ")
//    Integer getAverageTime(@Param("bookId") Integer bookId);
}
