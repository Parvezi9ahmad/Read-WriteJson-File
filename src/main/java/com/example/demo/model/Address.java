package com.example.demo.model;

import lombok.Data;

@Data
public class Address {
	private String street;
	private String city;
	private int zipcode;
	private String country;
}
