package com.martino.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.proxies.AnalyseProxy;
import com.martino.proxies.ClientProxy;
import com.martino.services.NumberToLetters;
import com.martino.services.OrderService;
import com.martino.services.SettingService;

import lombok.Data;

@ManagedBean
@Data
public class InvoiceReportModel {
	@Autowired
	private EstablishmentBean establishment;

	private Invoice invoice;

	@Autowired
	private ClientBean client;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SettingService settingService;

	@Autowired
	private AnalyseProxy analyseProxy;

	@Autowired
	private ClientProxy clientProxy;

	private DateTimeFormatter formatter;

	@Autowired
	private NumberToLetters numberConverter;

	public String getInvoiceDate() {
		formatter = DateTimeFormat.mediumDateTime();
		formatter.withZoneUTC();

		return formatter.print(this.invoice.getDate());
	}

	public String getClientName() {
		client = clientProxy.findOne(invoice.getClientId());
		return client.getNom() + " " + client.getPrenoms();
	}

	public String getClientSexe() {
		client = clientProxy.findOne(invoice.getClientId());
		return Sex.pipe(client.getSexe());
	}

	public String getClientAge() {
		client = clientProxy.findOne(invoice.getClientId());
		return Age.pipe(client.getAge());
	}

	public String getClientTel() {
		client = clientProxy.findOne(invoice.getClientId());
		return client.getTel();
	}

	public String getInvoiceSerial() {
		return this.invoice.getSerial();
	}

	public String getTotalPrice() {
		return getOrders().stream().mapToDouble(element -> element.getPrice()).sum() + " F CFA";
	}

	public String getTotalPriceInLetters() {
		double total = getOrders().stream().mapToDouble(element -> element.getPrice()).sum();
		return (numberConverter.convert(total) + " Francs CFA").toUpperCase();
	}

	public List<Order> getOrders() {
		List<Order> list = new ArrayList<>();

		int i = 1;
		for (Order ord : orderService.findAll()) {
			if (ord.getFactureId().equals(this.invoice.getId())) {
				ord.setPrice(ord.getProducePu() * ord.getQuantity());

				// search to the designation in analyse microservice
				AnalyseBean produceParent = analyseProxy.findOne(ord.getProduceId());

				ord.setProduceDesignation(produceParent.getDesignation());

				// adding order number
				ord.setNum(i);
				i++;

				list.add(ord);
			}
		}
		return list;
	}

	public Integer getTotalQuantity() {
		return getOrders().stream().mapToInt(element -> element.getQuantity()).sum();
	}

	public EstablishmentBean getEstablishment() {
		establishment.setFirstName(settingService.findOne("firstName").getValue());
		establishment.setLastName(settingService.findOne("lastName").getValue());
		establishment.setStreetName(settingService.findOne("streetName").getValue());
		establishment.setTownName(settingService.findOne("townName").getValue());
		establishment.setCountryName(settingService.findOne("countryName").getValue());
		establishment.setPostalCode(settingService.findOne("postalCode").getValue());
		establishment.setTel(settingService.findOne("tel").getValue());
		establishment.setEmail(settingService.findOne("email").getValue());

		establishment.setEmetteur(settingService.findOne("emetteur").getValue());
		establishment.setCaissier(settingService.findOne("caissier").getValue());

		return establishment;
	}
}
