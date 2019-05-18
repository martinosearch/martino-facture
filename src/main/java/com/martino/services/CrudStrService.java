package com.martino.services;

import java.util.List;

public interface CrudStrService<T> {
	void delete(String id);

	List<T> findAll();

	T findOne(String id);

	T save(T obj);
}
