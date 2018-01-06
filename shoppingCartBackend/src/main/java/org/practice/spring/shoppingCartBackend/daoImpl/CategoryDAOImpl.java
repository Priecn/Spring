package org.practice.spring.shoppingCartBackend.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.practice.spring.shoppingCartBackend.dao.CategoryDAO;
import org.practice.spring.shoppingCartBackend.dto.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{

	private static List<Category> categories = new ArrayList<>();
	
	static {
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("TV DEsc");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Mobile DEsc");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
		
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Laptop DEsc");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
	}
	
	@Override
	public List<Category> getListOfCategory() {
		return categories;
	}

	@Override
	public Category getProductForGivenCategoryId(int categoryId) {
		for(Category category: categories)
			if(category.getId() == categoryId)
				return category;
		return null;
	}

}
