package com.martino.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "table_type_analyse")
@Data
public class TypeTest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String designation;
}
