package com.springjpastudy.repository.orderRepository;


import com.springjpastudy.domain.order.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o join o.member m",
                Order.class
        ).getResultList();
    }

    //


}
