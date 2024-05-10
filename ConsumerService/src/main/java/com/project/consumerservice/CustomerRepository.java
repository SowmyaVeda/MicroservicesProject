package com.project.consumerservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    Customer findByid(int id);

    @Query(value="select p from Customer p where p.customerEmail=?1")
    List<Customer> getCustomerByEmail(String email);
}


