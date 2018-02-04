package org.learn.spring.ecommerce_backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.learn.spring.ecommerce_backend.dao.CartLineDAO;
import org.learn.spring.ecommerce_backend.dao.ProductDAO;
import org.learn.spring.ecommerce_backend.dao.UserDAO;
import org.learn.spring.ecommerce_backend.dto.Cart;
import org.learn.spring.ecommerce_backend.dto.CartLine;
import org.learn.spring.ecommerce_backend.dto.Product;
import org.learn.spring.ecommerce_backend.dto.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;

	private CartLine cartLine = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.learn.spring.ecommerce_backend");
		context.refresh();
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	@Test
	public void testAddCartLine() {

		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("test@gmail.com");
		Cart cart = user.getCart();

		// fetch the product
		Product product = productDAO.get(9);

		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setBuyingPrice(product.getUnitPrice());

		double oldTotal = cartLine.getTotal();

		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());

		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));

		assertEquals("Failed to add the CartLine!", true, cartLineDAO.add(cartLine));
		assertEquals("Failed to update the cart!", true, cartLineDAO.updateCart(cart));

	}

	/*@Test
	public void testUpdateCartLine() {
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("absr@gmail.com");
		Cart cart = user.getCart();
		cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		double oldTotal = cartLine.getTotal();
		cartLine.setTotal(cartLine.getProduct().getUnitPrice() * cartLine.getProductCount());
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		assertEquals("Failed to update the CartLine!", true, cartLineDAO.update(cartLine));

	}*/

}
