package com.martino.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.models.Setting;
import com.martino.repositories.SettingRepository;

@Service
@Transactional
public class SettingService implements CrudStrService<Setting> {
	@Autowired
	private SettingRepository repository;

	@Override
	public void delete(String id) {
		Setting obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<Setting> findAll() {
		List<Setting> liste = new ArrayList<>();
		for (Setting obj : repository.findAll()) {
			liste.add(obj);
		}
		return liste;
	}

	@Override
	public Setting findOne(String id) {
		Optional<Setting> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Setting save(Setting obj) {
		return repository.save(obj);
	}
}
