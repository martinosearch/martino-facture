package com.martino.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.models.Order;
import com.martino.repositories.OrderRepository;

@Service
@Transactional
public class OrderService implements CrudLongService<Order> {
	@Autowired
	private OrderRepository repository;

	@Override
	public void delete(Long id) {
		Order obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<Order> findAll() {
		List<Order> liste = new ArrayList<>();
		for (Order obj : repository.findAll()) {
			liste.add(obj);
		}
		return liste;
	}

	@Override
	public Order findOne(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Order save(Order obj) {
		return repository.save(obj);
	}
}
