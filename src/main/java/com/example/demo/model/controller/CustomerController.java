package com.example.demo.model.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Address;
import com.example.demo.model.Customer;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class CustomerController {

	@GetMapping("/hello/test")
	public String getCustomer() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File resource = new ClassPathResource("customer.json").getFile();
		// read JSON file and convert to a customer object
		Customer customer = objectMapper.readValue(resource, Customer.class);
		// print customer details
		System.out.println(customer);
		
		//2nd part
		Customer customerObj = new Customer();
		customerObj.setId(567L);
		customerObj.setName("Maria Kovosi");
		customerObj.setEmail("maria@example.com");
		customerObj.setPhone("+12 785 4895 321");
		customerObj.setAge(29);
		customerObj.setProjects(new String[] { "Path Finder App", "Push Notifications" });

		Address address = new Address();
		address.setStreet("Karchstr.");
		address.setCity("Hanover");
		address.setZipcode(66525);
		address.setCountry("Germany");
		customerObj.setAddress(address);

		List<String> paymentMethods = new ArrayList<>();
		paymentMethods.add("PayPal");
		paymentMethods.add("SOFORT");
		customerObj.setPaymentMethods(paymentMethods);

		Map<String, Object> info = new HashMap<>();
		info.put("gender", "female");
		info.put("married", "No");
		info.put("income", "120,000 EURO");
		info.put("source", "Google Search");
		customerObj.setProfileInfo(info);

		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		// write customerObj object to customer2.json file
		objectMapper.writeValue(new File("test"), customerObj);
		//print json data
		System.out.println(customerObj);
		
		//3rd part
		//convert json data into map as well
		Map<?,?> readValue = objectMapper.readValue(resource, Map.class);
		System.out.println(readValue);
		for (Map.Entry<?, ?> entry :readValue.entrySet()){
			System.out.println(entry.getKey()+"    "+entry.getValue());
		}
		
		//4th part
		//convert specific data from json to tree node
		JsonNode rootNode = objectMapper.readTree(resource);
		System.out.println("Customer name :"+rootNode.path("name").asText());
		return rootNode.path("name").asText();
	}

}
