package com.naddame.api;


import com.naddame.api.utils.HeaderUtil;
import com.naddame.api.utils.PaginationUtil;
import com.naddame.model.Order;
import com.naddame.reader.OrderReader;
import com.naddame.security.AuthoritiesConstants;
import com.naddame.writer.OrderWriter;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing Orders.
 *
 * <p>This class accesses the Order entity, and needs to fetch its collection of items.</p>
 *
 */
@RestController
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    @Inject
    private OrderReader orderReader;

    @Inject
    private OrderWriter orderWriter;


    /**
     * POST  /orders  : Creates a new order.
     * <p>
     * Creates a new order if id is null else throw exception order already exists, use put method to update
     * </p>
     *
     * @param order the order to create
     * @return the ResponseEntity with status 201 (Created) and with body the new order, or with status 400 (Bad Request) if the id is not null
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/orders")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createOrder(@RequestBody Order order) throws URISyntaxException {
        log.debug("REST request to save order : {}", order);

        //Lowercase the order login before comparing with database
        if (null != order.getId()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("orderManagement", "orderexists", "Order have already an id"))
                    .body(null);
        }else {
            Order newOrder = orderWriter.createOrder(order);
            return ResponseEntity.created(new URI("/api/orders/" + newOrder.getId()))
                    .headers(HeaderUtil.createAlert( "An order is created with identifier " + newOrder.getId(), String.valueOf(newOrder.getId())))
                    .body(newOrder);
        }
    }

    /**
     * PUT  /orders : Updates an existing order.
     *
     * @param order the order to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated order,
     * or with status 400 (Bad Request) if the login or email is already in use,
     * or with status 500 (Internal Server Error) if the order couldn't be updated
     */
    @PutMapping("/orders")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        log.debug("REST request to update order : {}", order);
        Optional<Order> existingOrder = orderReader.findOrder(order.getId());
        if (!existingOrder.isPresent()) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("orderManagement", "orderNotExists", "Can't update, no order found with the given id")).body(null);
        }
        orderWriter.updateOrder(order);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("An order is updated with identifier " + order.getId(), String.valueOf(order.getId())))
                .body(order);
    }

    /**
     * GET  /orders : get all orders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all orders
     * @throws URISyntaxException if the pagination headers couldn't be generated
     */
    @GetMapping("/orders")
    public ResponseEntity<?> findAllOrders(@ApiParam Pageable pageable)
            throws URISyntaxException {
        Page<Order> page = orderReader.findAllOrders(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orders");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /orders/:paid : get orders filter by paid
     *
     * @param paid the filter by paid orders status
     * @return the ResponseEntity with status 200 (OK) and with body the orders, or with status 404 (Not Found)
     */
    @GetMapping("/orders/{paid}")
    public ResponseEntity<?> findOrdersByPaid(@PathVariable boolean paid, @ApiParam Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get Orders : {}", paid);
        Page<Order> page = orderReader.findOrders(paid, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orders");

        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * DELETE /orders/:id : delete the "id" Order.
     *
     * @param id the id of the order to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/orders/{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.debug("REST request to delete Order: {}", id);
        orderWriter.deleteOrder(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "An order is deleted with identifier " + id, String.valueOf(id))).build();
    }
}
