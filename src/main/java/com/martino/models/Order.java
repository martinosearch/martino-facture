package com.martino.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_order")
@Data
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private Long factureId;

	@NotNull
	private Long produceId;

	@NotNull
	private Double producePu;

	@NotNull
	private Integer quantity;

	@NotNull
	private boolean payed;

	@Transient
	private String produceDesignation;

	@Transient
	private Double price;

	@Transient
	private Integer num;
}
