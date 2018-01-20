package org.practice.spring.shoppingCartBackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.practice.spring.shoppingCartBackend.dao.CategoryDAO;
import org.practice.spring.shoppingCartBackend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Category> getListOfCategory() {
		String selectActiveCategory = "from Category where active = :active";
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory, Category.class);
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	@Override
	public Category getProductForGivenCategoryId(int categoryId) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(categoryId));
	}

	@Override
	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
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
