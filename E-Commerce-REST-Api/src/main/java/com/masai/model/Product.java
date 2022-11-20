package com.masai.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Product {

	private Integer id;
	private String name;
	private Integer producty;
	
	
}
