package com.martino.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "table_setting")
@Data
public class Setting {
	@Id
	private String key;
	private String value;
}
