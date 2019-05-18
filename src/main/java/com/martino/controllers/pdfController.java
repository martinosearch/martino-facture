package com.martino.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.models.Invoice;
import com.martino.services.InvoiceReportingService;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "*")
public class pdfController {
	@Autowired
	private InvoiceReportingService service;

	@PostMapping("/facture")
	public Invoice generate(@RequestBody Invoice currentInvoice) throws IOException {
		return service.generateInvoiceFor(currentInvoice);
	}
}
