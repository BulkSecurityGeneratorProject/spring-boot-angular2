package com.naddame.reader.impl;

import com.naddame.model.Order;
import com.naddame.model.User;
import com.naddame.reader.OrderReader;
import com.naddame.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by djamel Hamas on 24/01/2017.
 */
@Service
public class OrderReaderImpl implements OrderReader {

    @Autowired private OrderRepository orderRepository;

    @Override
    public Optional<Order> findOrder(Long id) {
        return Optional.of(orderRepository.findOne(id));
    }

    @Override
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findOrders(boolean paid, Pageable pageable) {
        return orderRepository.findOneByPaid(paid, pageable);
    }
}
