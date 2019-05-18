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

import com.martino.models.Invoice;
import com.martino.services.InvoiceService;

@RestController
@RequestMapping("/facture")
@CrossOrigin(origins = "*")
public class FactureController implements CrudController<Invoice> {
	@Autowired
	private InvoiceService service;

	@Override
	@PostMapping("/save")
	public Invoice save(@RequestBody Invoice obj) {
		return service.save(obj);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}

	@Override
	@GetMapping("/info/{id}")
	public Invoice getOne(@PathVariable long id) {
		return service.findOne(id);
	}

	@Override
	@GetMapping("/liste")
	public List<Invoice> liste() {
		return service.findAll();
	}
}
