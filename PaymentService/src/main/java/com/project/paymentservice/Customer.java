package com.project.paymentservice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;
    @Column(name = "customer_email", nullable = false, length = 50)
    private String customerEmail;
    @Column(name = "address", nullable = true,length = 100)
    private String address;
    @Column(name = "amount_available", nullable = true)
    private int amountAvailable;
    @Column(name = "amount_reserved", nullable = true)
    private int amountReserved;

}