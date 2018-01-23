package org.learn.spring.ecommerce_backend.dao;

import java.util.List;

import org.learn.spring.ecommerce_backend.dto.Product;

public interface ProductDAO {
	Product get(int productId);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	boolean deActivate(Product product);
	
	//business methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int category);
	List<Product> getLatestActiveProducts(int count);
}
