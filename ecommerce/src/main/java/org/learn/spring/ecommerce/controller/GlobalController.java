package org.learn.spring.ecommerce.controller;

import javax.servlet.http.HttpSession;

import org.learn.spring.ecommerce.model.UserModel;
import org.learn.spring.ecommerce_backend.dao.UserDAO;
import org.learn.spring.ecommerce_backend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		if(session.getAttribute("userModel") == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.getByEmail(authentication.getName());
			if(user != null) {
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());
				if(user.getRole().equals("USER"))
					userModel.setCart(user.getCart());
				userModel.setRole(user.getRole());
				
				session.setAttribute("userModel", userModel);
			}
		}
		return (UserModel)session.getAttribute("userModel");
	}
}
