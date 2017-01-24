package com.naddame.writer.impl;

import com.naddame.model.Authority;
import com.naddame.model.Order;
import com.naddame.model.util.Names;
import com.naddame.repository.OrderRepository;
import com.naddame.repository.SequenceRepository;
import com.naddame.writer.OrderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by djamel Hamas on 24/01/2017.
 */
@Service
public class OrderWriterImpl implements OrderWriter {
    private final Logger log = LoggerFactory.getLogger(OrderWriterImpl.class);

    @Autowired private OrderRepository orderRepository;
    @Autowired private SequenceRepository sequenceRepository;


    @Override
    public Order createOrder(Order order) {
        if (null == order) throw new RuntimeException("order mustn't be null");
        if (null != order.getId()) throw new RuntimeException("order have already an id, use update instead");

        order.setId(sequenceRepository.nextValue(Names.order_seq));
        order = this.orderRepository.save(order);
        log.debug("Created Information for Order: {}", order);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        Optional.of(orderRepository
                .findOne(order.getId()))
                .ifPresent(o -> {
                    orderRepository.save(order);
                    log.debug("Changed Information for Order: {}", order);
                });
    }

    @Override
    public void deleteOrder(Long id) {
        this.orderRepository.delete(id);
    }
}
