package org.learn.spring.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.learn.spring.ecommerce_backend.dao.ProductDAO;
import org.learn.spring.ecommerce_backend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	public Map<Integer, List<Product>> getRecentProduct(boolean firstThree) {
		Map<Integer, List<Product>> tempMap = productDAO.getMapOfProductForEachCategory();
		if (tempMap != null) {
			Map<Integer, List<Product>> mapOfRecentProduct = new HashMap<>();
			if (firstThree) {
				for (Entry<Integer, List<Product>> en : tempMap.entrySet()) {
					if (en.getValue().size() > 3)
						mapOfRecentProduct.put(en.getKey(), en.getValue().subList(0, 3));
					else
						mapOfRecentProduct.put(en.getKey(), en.getValue());
				}
			} else {
				for(Entry<Integer, List<Product>> en: tempMap.entrySet()) {
					if(en.getValue().size() > 3)
						mapOfRecentProduct.put(en.getKey(), en.getValue().subList(3, en.getValue().size()));
				}
			}
			return mapOfRecentProduct;
		}
		return null;
	}
}
