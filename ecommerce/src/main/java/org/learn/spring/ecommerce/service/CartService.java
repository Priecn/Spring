package org.learn.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.learn.spring.ecommerce.model.UserModel;
import org.learn.spring.ecommerce_backend.dao.CartLineDAO;
import org.learn.spring.ecommerce_backend.dao.ProductDAO;
import org.learn.spring.ecommerce_backend.dto.Cart;
import org.learn.spring.ecommerce_backend.dto.CartLine;
import org.learn.spring.ecommerce_backend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartService {
	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private HttpSession session;

	private Cart getCart() {
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		return userModel.getCart();
	}

	public List<CartLine> getCartLines() {
		return cartLineDAO.list(getCart().getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		// fetch cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine != null) {
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			
			if (product.getQuantity() < count) {
				return "result=notAvailable";
			}
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(count);
			cartLine.setTotal(product.getUnitPrice() * count);

			cartLineDAO.update(cartLine);
			Cart cart = getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());

			cartLineDAO.updateCart(cart);

			return "result=updated";
		}
		return "result=error";
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine != null) {
			Cart cart = getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);

			cartLineDAO.delete(cartLine);
			return "result=deleted";
		}
		return "result=error";
	}

	public String addCartLine(int productId) {
		Cart cart = getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if (cartLine == null) {
			Product product = productDAO.get(productId);
			cartLine = new CartLine();
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setCartId(cart.getId());
			cartLine.setProductCount(1);
			cartLine.setProduct(product);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=added";
		} else {
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(cartLine.getProductCount() + 1);
			cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());

			cartLineDAO.update(cartLine);
			
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
	}
}
