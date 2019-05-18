package com.martino.controllers;

import java.util.List;

public interface CrudController<T> {

	List<T> liste();

	T save(T obj);

	void delete(long id);

	T getOne(long id);

}
