package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.model.Address;
import com.example.demo.model.Customer;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class CodeExampleApplication {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

		SpringApplication.run(CodeExampleApplication.class, args);
		ObjectMapper objectMapper = new ObjectMapper();

		ClassPathResource res = new ClassPathResource("customer.json");
		File file = new File(res.getPath());
		System.out.println(file + " with some data");
		
		File resource = new ClassPathResource("customer.json").getFile();
		System.out.println(resource+" with extr data");
		String text = new String(Files.readAllBytes(resource.toPath()));
		//System.out.println(text+" with other value");

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
        customerObj.setProjects(new String[]{"Path Finder App", "Push Notifications"});

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
        //write customerObj object to customer2.json file
        objectMapper.writeValue(new File("json"), customerObj);
    }

	

}
