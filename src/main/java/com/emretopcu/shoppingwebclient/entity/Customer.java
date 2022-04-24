package com.emretopcu.shoppingwebclient.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
	
	private String id;
	private String name;
	private String country;
	private ArrayList<Link> links = new ArrayList<>(); 
	
	public Customer() {
		
	}
	
	public Customer(String id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}
	
	public Customer(String id, String name, String country, ArrayList<Link> links) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.links = links;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", country=" + country + ", links=" + links + "]";
	}

}
