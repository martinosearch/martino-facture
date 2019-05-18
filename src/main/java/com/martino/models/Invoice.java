package com.martino.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_invoice")
@Data
@NoArgsConstructor
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private Long clientId;
	@NotEmpty
	private String serial;
	private DateTime date;
	private String downloadUri;
}
