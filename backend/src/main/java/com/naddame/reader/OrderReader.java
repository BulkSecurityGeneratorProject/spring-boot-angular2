package com.naddame.reader;

import com.naddame.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by djamel Hamas on 24/01/2017.
 */
@Service
public interface OrderReader {
    Optional<Order> findOrder(Long id);

    Page<Order> findAllOrders(Pageable pageable);

    Page<Order> findOrders(boolean paid, Pageable pageable);
}
