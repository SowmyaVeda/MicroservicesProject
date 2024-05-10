package com.project.orderprocessservice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {


    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customerId", nullable = true)
    private int customerId;
    @Column(name = "productId", nullable = true)
    private int productId;
    @Column(name = "productCount", nullable = true)
    private int productCount;
    @Column(name = "price", nullable = true)
    private int price;
    @Column(name = "status", nullable = true, length = 15)
    private String status;
    @Column(name = "source", nullable = true, length = 15)
    private String source;

    public Order() {
    }

    public Order(int id, int customerId, int productId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.status = status;
    }

    public Order(int id, int customerId, int productId, int productCount, int price) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productCount = productCount;
        this.price = price;
        this.status = "NEW";
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", productCount=" + productCount +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", source='" + source + '\'' +
                '}';
    }



}