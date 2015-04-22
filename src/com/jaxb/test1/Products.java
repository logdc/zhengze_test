package com.jaxb.test1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by applec on 15/4/22.
 */
@XmlRootElement(name="products")
public class Products {
	private long testAttr;
	private List<Product> productList;

	public long getTestAttr() {
		return testAttr;
	}
	@XmlElement
	public void setTestAttr(long testAttr) {
		this.testAttr = testAttr;
	}

	public List<Product> getProductList() {
		return productList;
	}
	@XmlElement(name="product")
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
