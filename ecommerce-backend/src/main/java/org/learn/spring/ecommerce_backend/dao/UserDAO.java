package org.learn.spring.ecommerce_backend.dao;

import java.util.List;

import org.learn.spring.ecommerce_backend.dto.Address;
import org.learn.spring.ecommerce_backend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	User getByEmail(String email);
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listOfShippingAddresses(User user);
}
