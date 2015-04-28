package com.jaxb.test1;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBExample {
	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.setId(100);
		customer.setName("benson");
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


		// test base64 encoding and decoding
		try {

			// encode from obj to string
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(bOut);
			objOut.writeObject(customer);
			BASE64Encoder encoder = new BASE64Encoder();
			String codeStr = encoder.encode(bOut.toByteArray());

			// show encoded string
			System.out.println("---------------------------------");
			System.out.println(codeStr);
			System.out.println("---------------------------------");

			// try to decode string to original object.
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] b = base64Decoder.decodeBuffer(codeStr);
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			ObjectInputStream ois = new ObjectInputStream(bis);
			ois.readObject();

			// close all
			bOut.close();
			objOut.close();
			bis.close();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


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