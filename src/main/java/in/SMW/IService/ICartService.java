package in.SMW.IService;


import java.util.List;

import in.SMW.DTO.CartDTO;
import in.SMW.Request.CartRequest;

public interface ICartService {

	CartDTO addToCart(
			Integer userId,
			CartRequest request);

	CartDTO updateCartItem(
			Integer userId,
			CartRequest request);

	void removeCartItem(
			Integer userId,
			Integer productId);

	List<CartDTO> getCartItems(
			Integer userId);

	void clearCart(
			Integer userId);

}