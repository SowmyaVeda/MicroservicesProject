package com.project.productcatalogservices;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer>
{
    Product findByid(int id);

    @Query(value="select p from Product p where p.productActive=true")
    List<Product> getActiveProducts();
}