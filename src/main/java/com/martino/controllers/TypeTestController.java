package com.martino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.models.TypeTest;
import com.martino.services.TypeTestService;

@RestController
@RequestMapping("/type-test")
@CrossOrigin(origins = "*")
public class TypeTestController implements CrudController<TypeTest> {
	@Autowired
	private TypeTestService service;

	@Override
	@PostMapping("/save")
	public TypeTest save(@RequestBody TypeTest obj) {
		return service.save(obj);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}

	@Override
	@GetMapping("/info/{id}")
	public TypeTest getOne(@PathVariable long id) {
		return service.findOne(id);
	}

	@Override
	@GetMapping("/liste")
	public List<TypeTest> liste() {
		return service.findAll();
	}
}
