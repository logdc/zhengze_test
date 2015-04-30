package com.md5.test1;

import com.common.test.ObjectUtil;
import com.jaxb.test1.Customer;
import com.jaxb.test1.JAXBExample;
import com.jaxb.test1.Product;
import com.jaxb.test1.Products;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BASE64Test {
	public static void main(String[] args) {

		Customer customer = JAXBExample.getSampleCustomer();


		// test base64 encoding and decoding
		try {

			// encode from obj to string
			BASE64Encoder encoder = new BASE64Encoder();
			String codeStr = encoder.encode(ObjectUtil.serialize(customer));

			// show encoded string
			System.out.println("---------------------------------");
			System.out.println(codeStr);

			// try to decode string to original object.
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] b = base64Decoder.decodeBuffer(codeStr);

			// TODO code below can be replaced by ObjectUtil#deserialize
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			ObjectInputStream ois = new ObjectInputStream(bis);
			ois.readObject();
			System.out.println("---------------------------------");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}



	}
}