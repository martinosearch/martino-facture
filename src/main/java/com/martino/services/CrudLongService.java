package com.martino.services;

import java.util.List;

public interface CrudLongService<T> {
	void delete(Long id);

	List<T> findAll();

	T findOne(Long id);

	T save(T obj);
}
