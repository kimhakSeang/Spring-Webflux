package com.learn.webflux.week_9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table
@Data
public class Customer {
	
	@Id
	private int id;
	private String name;
	private String email;
}
