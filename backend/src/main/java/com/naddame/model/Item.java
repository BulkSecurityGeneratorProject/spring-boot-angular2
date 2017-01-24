package com.naddame.model;

import java.io.Serializable;

/**
 * Created by djamel Hamas on 24/01/2017.
 */
public class Item implements Serializable {
    private String itemId;
    private float price;
    private String label;

    public Item() {
    }

    public String getItemId() {
        return itemId;
    }

    public float getPrice() {
        return price;
    }

    public String getLabel() {
        return label;
    }
}
