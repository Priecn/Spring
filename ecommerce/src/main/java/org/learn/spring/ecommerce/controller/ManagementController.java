package org.learn.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.learn.spring.ecommerce.util.FileUploadUtility;
import org.learn.spring.ecommerce.validator.ProductValidator;
import org.learn.spring.ecommerce_backend.dao.CategoryDAO;
import org.learn.spring.ecommerce_backend.dao.ProductDAO;
import org.learn.spring.ecommerce_backend.dto.Category;
import org.learn.spring.ecommerce_backend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	//private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Product");
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		//nProduct.setUnitPrice(1000);
		mv.addObject("product", nProduct);
		if(operation != null) {
			if(operation.equals("product"))
				mv.addObject("message", "Product Submitted Successfully!");
			else if(operation.equals("productDeleted"))
				mv.addObject("message", "Product Deleted Successfully!");
			else if(operation.equals("category"))
				mv.addObject("message", "Category added Successfully!");
		}
		return mv;
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@ModelAttribute("product") @Valid Product mProduct, Errors errors, Model model, HttpServletRequest request) {
		//check if there are errors
		if(mProduct.getId() == 0)
			new ProductValidator().validate(mProduct, errors);
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, errors);
			}
		}
		if(errors.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Product");
			model.addAttribute("message", "Validation failed for product Submission!");
			return "page";
		}
		//logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0)
			productDAO.add(mProduct); //for adding new product
		else
			productDAO.update(mProduct); //for updating the product
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		if(isActive)
			productDAO.deActivate(product);
		else
			productDAO.activate(product);
		return (isActive)?"Deactivated product with id: "+product.getId(): "Activated product with id: "+product.getId();
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Product");
		//fetch the product from database
		Product nProduct = productDAO.get(id);
		mv.addObject("product", nProduct);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}/product", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int id){
		Product product = productDAO.get(id);
		productDAO.delete(product);
		return "redirect:/manage/products?operation=productDeleted";
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String addCategory(@ModelAttribute Category category) {
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	//categories for all requests
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.getListOfCategory();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
}
