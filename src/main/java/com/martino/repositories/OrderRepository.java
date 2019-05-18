package com.martino.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.martino.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
