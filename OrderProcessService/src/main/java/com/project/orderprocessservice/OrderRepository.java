package com.project.orderprocessservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
    Order findByid(int id);


    @Transactional
    @Modifying
    @Query(value="update Order o set o.status = ?1 where o.id = ?2")
    void updateStatusByOrderid(String status, int orderid);

    @Transactional
    @Modifying
    @Query(value="update Order o set o.status = ?1 , o.source=?2 where o.id = ?3")
    void updateByOrderid(String status, String source, int orderid);
}