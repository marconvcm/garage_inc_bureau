package com.garage_inc.bureau.repository;

import com.garage_inc.bureau.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Iterable<Order> findByScheduledDateTimeAfterAndScheduledDateTimeBefore(LocalDateTime afterDate, LocalDateTime beforeDate);
}
