package com.project.inventoryservice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name", nullable = false, length = 20)
    private String productName;
    @Column(name = "count", nullable = true)
    private int productAvailableCount;
    @Column(name = "price", nullable = true)
    private int productPrice;
    @Column(name = "active", nullable = true, length = 15)
    private Boolean productActive;
    @Column(name = "reserved_count", nullable = true)
    private int productReservedCount;

    public int getAvailableItems() {
        return productAvailableCount;
    }

    public void setAvailableItems(int availableItems) {
        this.productAvailableCount = availableItems;
    }

    public int getReservedItems() {
        return productReservedCount;
    }

    public void setReservedItems(int reservedItems) {
        this.productReservedCount = reservedItems;
    }

}