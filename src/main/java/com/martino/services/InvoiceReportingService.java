package com.martino.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import com.martino.models.Invoice;
import com.martino.models.InvoiceReportModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class InvoiceReportingService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String logo_path = "/jasper/images/hospital.png";
	private final String invoice_template = "/jasper/invoice_template.jrxml";

	@Autowired
	private InvoiceReportModel model;
	@Autowired
	private InvoiceService invoiceService;

	public Invoice generateInvoiceFor(Invoice currentInvoice) throws IOException {

		File pdfFile = File.createTempFile("my-invoice", ".pdf");

		logger.info(String.format("*************** Invoice pdf path : %s", pdfFile.getAbsolutePath()));

		// start
		logger.info("*************** Start invoice generation...");
		currentInvoice.setDownloadUri(pdfFile.getName());

		// update invoice info in the db
		invoiceService.save(currentInvoice);

		try (FileOutputStream pos = new FileOutputStream(pdfFile)) {
			// Load invoice jrxml template.
			final JasperReport report = loadTemplate();

			logger.info("*************** Invoice template loaded successfully...");

			// Create parameters map.
			final Map<String, Object> parameters = parameters(currentInvoice, Locale.FRENCH);

			// Create an empty datasource.
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
					Collections.singletonList("Invoice"));

			// Render as PDF.
			JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

		} catch (final Exception e) {
			logger.error(String.format("An error occured during PDF creation: %s", e));
		}

		return currentInvoice;
	}

	// Fill template order parametres
	private Map<String, Object> parameters(Invoice currentInvoice, Locale locale) {
		final Map<String, Object> parameters = new HashMap<>();

		model.setInvoice(currentInvoice);

		parameters.put("logo", getClass().getResourceAsStream(logo_path));
		parameters.put("invoice_model", model);
		parameters.put("REPORT_LOCALE", locale);

		return parameters;
	}

	// Load invoice jrxml template
	private JasperReport loadTemplate() throws JRException {
		logger.info(String.format("Invoice template path : %s", invoice_template));
		final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template);
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
		return JasperCompileManager.compileReport(jasperDesign);
	}

}
