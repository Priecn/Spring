package org.learn.spring.ecommerce_backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.learn.spring.ecommerce_backend.dao.UserDAO;
import org.learn.spring.ecommerce_backend.dto.Address;
import org.learn.spring.ecommerce_backend.dto.Cart;
import org.learn.spring.ecommerce_backend.dto.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.learn.spring.ecommerce_backend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test public void testAddUser() {
	 * 
	 * user = new User(); user.setFirstName("Hrithik"); user.setLastName("Roshan");
	 * user.setEmail("hr@gmail.com"); user.setContactNumber("1234512345");
	 * user.setRole("USER"); // user.setEnabled(true); user.setPassword("12345");
	 * 
	 * 
	 * assertEquals("Failed to add user!", true, userDAO.addUser(user));
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
	 * address.setAddressLineTwo("Near Kaabil Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); address.setBilling(true); //link user with
	 * address using user id address.setUserId(user.getId());
	 * 
	 * assertEquals("Failed to add address!", true, userDAO.addAddress(address));
	 * 
	 * if (user.getRole().equals("USER")) { cart = new Cart(); cart.setUser(user);
	 * 
	 * // add cart with user user.setCart(cart); //
	 * assertEquals("Failed to add cart!", true, userDAO.addCart(cart));
	 * 
	 * // add the shipping address
	 * 
	 * address = new Address();
	 * address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
	 * address.setAddressLineTwo("Near Kudrat Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); address.setShipping(true);
	 * address.setUserId(user.getId());
	 * 
	 * assertEquals("Failed to add the user!", true, userDAO.addUser(user)); }
	 */
	/*
	 * 
	 * // linked the address with the user address.setUser(user);
	 * 
	 * // linked the cart with the user cart.setUser(user); // link the user with
	 * the cart user.setCart(cart);
	 * 
	 * // add the user assertEquals("Failed to add the user!", true,
	 * userDAO.add(user)); // add the address
	 * assertEquals("Failed to add the billing address!", true,
	 * userDAO.addAddress(address));
	 * 
	 * 
	 * 
	 * }
	 */

	// working for uni-directional

	/*@Test
	public void testAddAddress() {
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("12345");

		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);

		address.setUser(user); // add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));

		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		address.setUser(user);
		assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
	}*/

	/*
	 * @Test public void testUpdateCart() { user =
	 * userDAO.getByEmail("hr@gmail.com"); cart = user.getCart();
	 * cart.setGrandTotal(10000); cart.setCartLines(1);
	 * assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart)); }
	 */
	
	/*@Test
	public void testAddAddress() {
		user = userDAO.getByEmail("hr@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("E city");
		address.setAddressLineTwo("Near Infosys");
		address.setCity("bagalore");
		address.setState("karnataka");
		address.setCountry("India");
		address.setPostalCode("560100");
		address.setShipping(true);
		address.setUser(user);
		assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
	}*/
	
	@Test
	public void testGetAddresses() {
		user = userDAO.getByEmail("hr@gmail.com");
		
		assertEquals("Failed to fetch list of addresses!", 2, userDAO.listOfShippingAddresses(user).size());
		assertEquals("Failed to fetch billing addresses!", "Mumbai", userDAO.getBillingAddress(user).getCity());
	}

}
