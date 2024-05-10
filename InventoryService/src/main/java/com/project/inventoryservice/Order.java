package com.project.inventoryservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {


    private int id;
    private int customerId;
    private int productId;
    private int productCount;
    private int price;
    private String status;
    private String source;


}