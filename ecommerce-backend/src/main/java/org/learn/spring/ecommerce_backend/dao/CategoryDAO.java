package org.learn.spring.ecommerce_backend.dao;

import java.util.List;

import org.learn.spring.ecommerce_backend.dto.Category;

public interface CategoryDAO {
	List<Category> getListOfCategory();
	Category getProductForGivenCategoryId(int categoryId);
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	
	
	
}
