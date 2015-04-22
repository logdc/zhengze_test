package com.jaxb.test1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Customer {
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
}
