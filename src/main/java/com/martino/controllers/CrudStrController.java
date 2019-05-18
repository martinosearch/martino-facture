package com.martino.controllers;

import java.util.List;

public interface CrudStrController<T> {

	List<T> liste();

	T save(T obj);

	void delete(String id);

	T getOne(String id);

}
