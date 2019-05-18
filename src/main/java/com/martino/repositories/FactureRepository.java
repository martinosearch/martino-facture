package com.martino.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.martino.models.Invoice;

@Repository
public interface FactureRepository extends CrudRepository<Invoice, Long> {

}
