package com.jaxb.test1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Customer implements Serializable{

	private static final long serialVersionUID = 6420486923869074233L;

	private String name;
	private int age;
	private int id;
	private Products products;

	public String getName() {
		return name;
	}

	@XmlElement(name = "name_new")
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public Products getProducts() {
		return products;
	}

	@XmlElement (name="products")
	public void setProducts(Products products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Customer)) return false;

		Customer customer = (Customer) o;

		if (age != customer.age) return false;
		if (id != customer.id) return false;
		if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
		if (products != null ? !products.equals(customer.products) : customer.products != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + age;
		result = 31 * result + id;
		result = 31 * result + (products != null ? products.hashCode() : 0);
		return result;
	}
}
