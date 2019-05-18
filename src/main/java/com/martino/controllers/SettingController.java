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

import com.martino.models.Setting;
import com.martino.services.SettingService;

@RestController
@RequestMapping("/setting")
@CrossOrigin(origins = "*")
public class SettingController implements CrudStrController<Setting> {
	@Autowired
	private SettingService service;

	@Override
	@PostMapping("/save")
	public Setting save(@RequestBody Setting obj) {
		return service.save(obj);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	@GetMapping("/info/{id}")
	public Setting getOne(@PathVariable String id) {
		return service.findOne(id);
	}

	@Override
	@GetMapping("/liste")
	public List<Setting> liste() {
		return service.findAll();
	}
}
