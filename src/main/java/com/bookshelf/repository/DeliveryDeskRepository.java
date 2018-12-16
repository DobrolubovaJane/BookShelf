package com.bookshelf.repository;

import com.bookshelf.entity.DeliveryDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeliveryDeskRepository extends JpaRepository<DeliveryDesk, Integer> {

}
