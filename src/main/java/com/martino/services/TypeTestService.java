package com.martino.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.models.TypeTest;
import com.martino.repositories.TypeTestRepository;

@Service
@Transactional
public class TypeTestService implements CrudLongService<TypeTest> {
	@Autowired
	private TypeTestRepository repository;

	@Override
	public void delete(Long id) {
		TypeTest obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<TypeTest> findAll() {
		List<TypeTest> liste = new ArrayList<>();
		for (TypeTest obj : repository.findAll()) {
			liste.add(obj);
		}
		return liste;
	}

	@Override
	public TypeTest findOne(Long id) {
		Optional<TypeTest> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public TypeTest save(TypeTest obj) {
		return repository.save(obj);
	}
}
