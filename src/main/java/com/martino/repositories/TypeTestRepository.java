package com.martino.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.martino.models.TypeTest;

@Repository
public interface TypeTestRepository extends CrudRepository<TypeTest, Long> {

}
