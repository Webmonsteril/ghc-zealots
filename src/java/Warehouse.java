package com.company;

import java.util.HashMap;

public class Warehouse {

    private Cell location;
    private HashMap<ProductType, Integer> products = new HashMap<>();

    public HashMap<ProductType, Integer> getProducts() {
		return products;
	}

    public void setProducts(HashMap<ProductType, Integer> products) {
		this.products = products;
	}

    public Cell getLocation() {
        return location;
    }

    public void setLocation(Cell location) {
        this.location = location;
    }

}
