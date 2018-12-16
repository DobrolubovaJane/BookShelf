package com.bookshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookshelf.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, UUID>{

    @Query("Select b from Book b where b.name =: name")
    Book findByBookName(@Param("name") String name);


    @Query("select count(d.book) from DeliveryDesk d where d.book =: id")
    Integer getCountOfReaders(@Param("id") UUID id);

    @Query("select avg(to_date(d.start_date,'DD-MON-RR HH:MI:SS AM')) as avg from DeliveryDesk d where d.book =: id ")
    Date getAverageTime(@Param("id") UUID id);
}
