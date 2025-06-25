package com.giftydelivery.repository;

import com.giftydelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatusAndScheduledTimeBefore(String status, LocalDateTime time);
}
