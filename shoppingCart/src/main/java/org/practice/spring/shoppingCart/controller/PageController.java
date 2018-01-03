package org.practice.spring.shoppingCart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value= {"/", "/home", "/index"})
public class PageController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index(@RequestParam(
			name="greeting", defaultValue="Welcome to Spring web MVC!") String greeting) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}
	
	
}
