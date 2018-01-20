package org.practice.spring.shoppingCartBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.practice.spring.shoppingCartBackend.dao.CategoryDAO;
import org.practice.spring.shoppingCartBackend.dto.Category;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.practice.spring.shoppingCartBackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescription("Laptop DEsc");
		category.setImageURL("CAT_3.png");
		
		assertEquals("Successfully added a category", true, categoryDAO.add(category));
	}*/
	
	/*@Test
	public void testGetCategory() {
		category = categoryDAO.getProductForGivenCategoryId(3);
		assertEquals("Got the category", "Laptop", category.getName());
	}*/
	
	/*@Test
	public void testUpdateCategory() {
		category = new Category();
		category.setId(3);
		category.setDescription("Get best Laptops at lowest price");
		assertEquals("Updated the category", true, categoryDAO.update(category));
	}*/
	
	@Test
	public void testDeleteCategory() {
		category = new Category();
		category.setId(2);
		assertEquals("Deleted the category", true, categoryDAO.delete(category));
	}
}
