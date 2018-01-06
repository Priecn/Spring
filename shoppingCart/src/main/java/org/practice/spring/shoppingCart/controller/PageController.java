package org.practice.spring.shoppingCart.controller;

import org.practice.spring.shoppingCartBackend.dao.CategoryDAO;
import org.practice.spring.shoppingCartBackend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value= {"/", "/home", "/index"}, method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);
		
		//passing the categories
		mv.addObject("categories", categoryDAO.getListOfCategory());
		return mv;
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	
	@RequestMapping(value= {"/show/all/products"}, method=RequestMethod.GET)
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);
		
		//passing the categories
		mv.addObject("categories", categoryDAO.getListOfCategory());
		return mv;
	}
	
	@RequestMapping(value= {"/show/category/{categoryId}/products"}, method=RequestMethod.GET)
	public ModelAndView showProductsOfCategory(
			@PathVariable int categoryId
			) {
		ModelAndView mv = new ModelAndView("page");
		//category DAO to fetch single category
		Category category = categoryDAO.getProductForGivenCategoryId(categoryId);
		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProducts", true);
		
		//passing the categories
		mv.addObject("categories", categoryDAO.getListOfCategory());
		
		//passing single category
		mv.addObject("category", category);
		return mv;
	}
}
