package org.learn.spring.ecommerce.controller;

import org.learn.spring.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(required = false) String result) {
		ModelAndView mv = new ModelAndView("page");
		
		if(result != null) {
			switch(result) {
			case "updated":
				mv.addObject("message", "Product count has been updated successfully!");
				break;
			case "added":
				mv.addObject("message", "Product added to your cart!");
				break;
			case "deleted":
				mv.addObject("message", "Product removed from cart!");
				break;
			case "error":
				mv.addObject("message", "Error occurred while updating cart!");
				break;
			case "notAvailable":
				mv.addObject("message", "Error occurred while updating cart, Requested amount is not present!");
				break;
			}
		}
		
		mv.addObject("title", "Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateProductCount(@PathVariable int cartLineId, @RequestParam int count){
		String response = cartService.updateCartLine(cartLineId, count);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteProduct(@PathVariable int cartLineId){
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/add/{productId}/product")
	public String addProduct(@PathVariable int productId){
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
}
