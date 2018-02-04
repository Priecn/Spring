package org.learn.spring.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.learn.spring.ecommerce.exception.ProductNotFoundException;
import org.learn.spring.ecommerce.service.ProductService;
import org.learn.spring.ecommerce_backend.dao.CategoryDAO;
import org.learn.spring.ecommerce_backend.dao.ProductDAO;
import org.learn.spring.ecommerce_backend.dto.Category;
import org.learn.spring.ecommerce_backend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/", "/home", "/index" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);

		// logging
		// logger.info("Inside PageController index method - INFO");
		// logger.debug("Inside PageController index method - DEBUG");

		// passing the categories
		mv.addObject("categories", categoryDAO.getListOfCategory());
		//passing recent products
		mv.addObject("mostRecentProducts", productService.getRecentProduct(true));
		mv.addObject("nextRecentProducts", productService.getRecentProduct(false));
		return mv;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("userClickContact", true);
		return mv;
	}

	@RequestMapping(value = { "/show/all/products" }, method = RequestMethod.GET)
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);

		// passing the categories
		mv.addObject("categories", categoryDAO.getListOfCategory());
		return mv;
	}

	@RequestMapping(value = { "/show/category/{categoryId}/products" }, method = RequestMethod.GET)
	public ModelAndView showProductsOfCategory(@PathVariable int categoryId) {
		ModelAndView mv = new ModelAndView("page");
		// category DAO to fetch single category
		Category category = categoryDAO.getProductForGivenCategoryId(categoryId);
		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProducts", true);

		// passing the categories
		mv.addObject("categories", categoryDAO.getListOfCategory());

		// passing single category
		mv.addObject("category", category);
		return mv;
	}

	// show single product
	@RequestMapping(value = "/show/{productId}/product")
	public ModelAndView showSingleProduct(@PathVariable int productId) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(productId);
		if (product == null)
			throw new ProductNotFoundException();
		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		return mv;
	}

	// having similar mapping to out flow id
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid Username and password!");
		} else if (logout != null) {
			mv.addObject("logout", "User has successfully logged out!");
		}
		mv.addObject("title", "Login");
		return mv;
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "Error Occurred!");
		mv.addObject("errorTitle", "403 - Autherization Failed!");
		mv.addObject("errorDescription", "You are not authorized to view this page!");
		return mv;
	}

	// LOGOUT
	@RequestMapping(value = "/signout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, res, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/slider", method = RequestMethod.GET)
	public ModelAndView slider() {
		ModelAndView mv = new ModelAndView("sliderDemo");
		mv.addObject("title", "Slider");
		return mv;
	}
}
