package org.learn.spring.ecommerce_backend.dao;

import java.util.List;

import org.learn.spring.ecommerce_backend.dto.Cart;
import org.learn.spring.ecommerce_backend.dto.CartLine;

public interface CartLineDAO {
	public List<CartLine> list(int cartId);
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public boolean updateCart(Cart cart);
	
	public CartLine getByCartAndProduct(int cartId, int productId);
	public List<CartLine> listAvailable(int cartId);
}
