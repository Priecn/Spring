package org.practice.spring.shoppingCartBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.practice.spring.shoppingCartBackend.dao.ProductDAO;
import org.practice.spring.shoppingCartBackend.dto.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.practice.spring.shoppingCartBackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}
	
	@Test
	public void testCRUDProduct() {
		// create a product
		product = new Product();
		
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some desc for Oppo");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Product added successfully", true, productDAO.add(product));
		
		//reading and updating and deactivating product
		
		product = productDAO.get(2);
		assertEquals("fetched successfully", product.getName(), "Samsung s7");
		
		product.setName("Samsung Galaxy S7");
		assertEquals("Updated successfully", true, productDAO.update(product));
		
		assertEquals("Deactivated successfully", true, productDAO.deActivate(product));
		
		//List All
		assertEquals("Got the req List", 6, productDAO.list().size());
		
		//List All Active
		assertEquals("Got the req List", 5, productDAO.listActiveProducts().size());
		
		//List All Active By category
		assertEquals("Got the req List", 3, productDAO.listActiveProductsByCategory(3).size());
		
		//List latest Active
		assertEquals("Got the req List", 3, productDAO.getLatestActiveProducts(3).size());
				
	}
}
