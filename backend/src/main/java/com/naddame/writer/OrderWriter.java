package com.naddame.writer;

import com.naddame.model.Order;
import org.springframework.stereotype.Service;

/**
 * Created by djamel Hamas on 24/01/2017.
 */
@Service
public interface OrderWriter {
    Order createOrder(Order order);
    void updateOrder(Order order);

    void deleteOrder(Long id);
}
