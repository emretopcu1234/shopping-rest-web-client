package com.emretopcu.shoppingwebclient;

import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emretopcu.shoppingwebclient.entity.Customer;

@SpringBootApplication
public class ShoppingWebClientApplication implements CommandLineRunner {
	
	Client client;
	WebTarget webTarget;
	List<Customer> customers;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingWebClientApplication.class, args);
	}
	
	public void init() {
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080");
	}

	@Override
	public void run(String... args) throws Exception {
		init();
		
		// GET CUSTOMERS LIST
		try {
			customers = webTarget.path("/customers").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Customer>> () {});
		}
		catch(Exception e) {
			System.out.println(e.getClass().getCanonicalName());
		}
		
		// GET SPECIFIC CUSTOMER
		try {
			Random random = new Random();
			int index = random.nextInt(customers.size());
			webTarget.path("/customers/" + customers.get(index).getId()).request(MediaType.APPLICATION_JSON).get(Customer.class);
		}
		catch(Exception e) {
			System.out.println(e.getClass().getCanonicalName());
		}
		
		// POST NEW CUSTOMER
		try {
			Customer customer = new Customer("C1001", "Some Name", "Some Country");
			webTarget.path("/customers").request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		}
		catch(Exception e) {
			System.out.println(e.getClass().getCanonicalName());
		}
		
		// UPDATE A CUSTOMER
		try {
			Customer customer = new Customer("C1001", "Some Name2", "Some Country2");
			webTarget.path("/customers/" + customer.getId()).request(MediaType.APPLICATION_JSON).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
		}
		catch(Exception e) {
			System.out.println(e.getClass().getCanonicalName());
		}
		
		// DELETE A CUSTOMER
		try {
			Random random = new Random();
			int index = random.nextInt(customers.size());
			webTarget.path("/customers/" + customers.get(index).getId()).request(MediaType.APPLICATION_JSON).delete(Customer.class);
		}
		catch(Exception e) {
			System.out.println(e.getClass().getCanonicalName());
		}
	}
}
