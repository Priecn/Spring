package org.learn.spring.ecommerce.handler;

import org.learn.spring.ecommerce.model.RegisterModel;
import org.learn.spring.ecommerce_backend.dao.UserDAO;
import org.learn.spring.ecommerce_backend.dto.Address;
import org.learn.spring.ecommerce_backend.dto.Cart;
import org.learn.spring.ecommerce_backend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addAddress(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel registerModel) {
		String transitionValue="success";
		User user = registerModel.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//encoding password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.addUser(user);
		
		Address address = registerModel.getBilling();
		address.setUserId(user.getId());
		address.setBilling(true);
		userDAO.addAddress(address);
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password does not match confirm password!")
					.build());
			transitionValue = "failure";
		}
		
		if(userDAO.getByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email id already registered!")
					.build());
			transitionValue = "failure";
		}
		return transitionValue;
	}
}
