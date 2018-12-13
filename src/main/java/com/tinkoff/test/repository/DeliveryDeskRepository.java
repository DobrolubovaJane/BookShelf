package com.tinkoff.test.repository;

import com.tinkoff.test.entity.DeliveryDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeliveryDeskRepository extends JpaRepository<DeliveryDesk, Integer> {
}
