package org.practice.spring.shoppingCartBackend.dao;

import java.util.List;

import org.practice.spring.shoppingCartBackend.dto.Category;

public interface CategoryDAO {
	List<Category> getListOfCategory();

	Category getProductForGivenCategoryId(int categoryId);
}
