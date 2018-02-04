package org.learn.spring.ecommerce_backend.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.learn.spring.ecommerce_backend.dao.CategoryDAO;
import org.learn.spring.ecommerce_backend.dao.ProductDAO;
import org.learn.spring.ecommerce_backend.dto.Category;
import org.learn.spring.ecommerce_backend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CategoryDAO categoryDAO;
	
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Product> list() {
		try {
			String queryToGetListOfProduct = "from Product";
			Query<Product> query = sessionFactory.getCurrentSession().createQuery(queryToGetListOfProduct, Product.class);
			return query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean delete(Product product) {
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean deActivate(Product product) {
		try {
			product.setActive(false);
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean activate(Product product) {
		try {
			product.setActive(true);
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public List<Product> listActiveProducts() {
		try {
			String queryToGetListOfActiveProduct = "from Product where active = :active";
			Query<Product> query = sessionFactory.getCurrentSession().createQuery(queryToGetListOfActiveProduct, Product.class);
			query.setParameter("active", true);
			return query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Product> listActiveProductsByCategory(int category) {
		try {
			String queryToGetListOfProductByCategory = "from Product where active = :active and categoryId = :categoryId";
			Query<Product> query = sessionFactory.getCurrentSession().createQuery(queryToGetListOfProductByCategory, Product.class);
			query.setParameter("categoryId", Integer.valueOf(category));
			query.setParameter("active", true);
			return query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<Product> listRecentSixActiveProductsByCategory(int category) {
		try {
			String queryToGetListOfProductByCategory = "from Product where active = :active and categoryId = :categoryId order by id desc";
			Query<Product> query = sessionFactory.getCurrentSession().createQuery(queryToGetListOfProductByCategory, Product.class);
			query.setParameter("categoryId", Integer.valueOf(category));
			query.setParameter("active", true);
			query.setMaxResults(6);
			return query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Product> getLatestActiveProducts(int count) {
		try {
			String queryToGetListOfActiveProduct = "from Product where active = :active order by id";
			Query<Product> query = sessionFactory.getCurrentSession().createQuery(queryToGetListOfActiveProduct, Product.class);
			query.setParameter("active", true);
			return query.setFirstResult(0).setMaxResults(count).getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, List<Product>> getMapOfProductForEachCategory() {
		List<Category> listOfCategory = categoryDAO.getListOfCategory();
		Map<Integer, List<Product>> map = null;
		if(listOfCategory != null) {
			map = new HashMap<Integer, List<Product>>();
			for(Category category: listOfCategory) {
				List<Product> listOfProduct = listRecentSixActiveProductsByCategory(category.getId());
				if(listOfCategory != null)
					map.put(category.getId(), listOfProduct);
			}
			return map;
		}
		return null;
	}

}
