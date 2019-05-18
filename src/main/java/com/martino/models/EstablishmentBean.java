package com.martino.models;

import javax.annotation.ManagedBean;

import lombok.Data;

@Data
@ManagedBean
public class EstablishmentBean {
	private String firstName;
	private String lastName;
	private String streetName;
	private String townName;
	private String countryName;
	private String postalCode;
	private String tel;
	private String email;
	private String emetteur;
	private String caissier;
}
