package org.learn.spring.ecommerce_backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.learn.spring.ecommerce_backend.dao.CategoryDAO;
import org.learn.spring.ecommerce_backend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Category> getListOfCategory() {
		String selectActiveCategory = "from Category where active = :active";
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory, Category.class);
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	public Category getProductForGivenCategoryId(int categoryId) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(categoryId));
	}

	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean delete(Category category) {
		try {
			sessionFactory.getCurrentSession().delete(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
