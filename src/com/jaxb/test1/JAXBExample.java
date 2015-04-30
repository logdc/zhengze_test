package com.jaxb.test1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBExample {

	public static Customer getSampleCustomer(){

		Customer customer = new Customer();
		customer.setId(10);
		customer.setName("benson11111");
		customer.setAge(23);
		Products products = new Products();
//		products.setTestAttr(34234);
		List<Product> productList = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setId(99);
		product1.setName("pro1");
		product1.setTypeName("A");
		productList.add(product1);
		Product product2 = new Product();
		product2.setId(199);
		product2.setName("pro2");
		product2.setTypeName("B");
		productList.add(product2);
		products.setProductList(productList);
		customer.setProducts(products);

		return customer;
	}

	public static void main(String[] args) {
		Customer customer = getSampleCustomer();

		try {

		//	File file = new File("/Users/applec/Documents/IdeaProjects/zhengze_test/file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		//	jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}