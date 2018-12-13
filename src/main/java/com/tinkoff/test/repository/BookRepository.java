package com.tinkoff.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tinkoff.test.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer>{

    @Query("Select b from Book b where b.name =: name")
    Book findByBookName(@Param("name") String name);
}
