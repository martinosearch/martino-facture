package com.martino.models;

import javax.annotation.ManagedBean;

import lombok.Data;

@Data
@ManagedBean
public class ClientBean {
	private Long id;
	private String nom;
	private String prenoms;
	private Integer sexe;
	private Integer age;
	private String tel;
	private String email;
}
