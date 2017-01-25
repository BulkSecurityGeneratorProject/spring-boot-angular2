package com.naddame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djamel Hamas on 24/01/2017.
 */
@Document(collection = "nad_orders")
public class Order  extends AbstractAuditingEntity implements Serializable {

    @Id
    public Long id;
    @Indexed(unique = true)
    public String orderId;
    public Short diners;
    public Set<Item> items = new HashSet<>();
    public String comments;
    public float total;

    @Indexed
    public boolean paid = false;

    private ZonedDateTime closed = ZonedDateTime.now();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Short getDiners() {
        return diners;
    }

    public void setDiners(Short diners) {
        this.diners = diners;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public ZonedDateTime getClosed() {
        return closed;
    }

    public void setClosed(ZonedDateTime closed) {
        this.closed = closed;
    }
}
