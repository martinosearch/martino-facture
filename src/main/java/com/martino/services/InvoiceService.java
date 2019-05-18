package com.martino.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.models.Invoice;
import com.martino.repositories.FactureRepository;

@Service
@Transactional
public class InvoiceService implements CrudLongService<Invoice> {
	@Autowired
	private FactureRepository repository;
	private final static DecimalFormat formatter = new DecimalFormat("000000");

	Invoice currentInvoice;

	@Override
	public void delete(Long id) {
		Invoice obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<Invoice> findAll() {
		List<Invoice> liste = new ArrayList<>();
		for (Invoice obj : repository.findAll()) {
			liste.add(obj);
		}
		return liste;
	}

	@Override
	public Invoice findOne(Long id) {
		Optional<Invoice> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Invoice save(Invoice obj) {
		currentInvoice = obj;
		setInvoiceCode();
		currentInvoice = repository.save(currentInvoice);

		return currentInvoice;
	}

	private Invoice setInvoiceCode() {
		currentInvoice.setSerial("SAN-" + formatter.format(currentInvoice.getId()));
		return currentInvoice;
	}
}
