package com.naddame.repository;

import com.naddame.model.Order;
import com.naddame.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the User entity.
 */
public interface OrderRepository extends MongoRepository<Order, Long> {

    Optional<Order> findOneByOrderId(String orderId);
    Page<Order> findOneByPaid(boolean paid, Pageable pageable);
}
