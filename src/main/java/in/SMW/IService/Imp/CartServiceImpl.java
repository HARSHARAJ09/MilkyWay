package in.SMW.IService.Imp;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.CartDTO;
import in.SMW.Entity.Cart;
import in.SMW.Entity.CartItem;
import in.SMW.Entity.Product;
import in.SMW.Exception.UserException;
import in.SMW.IService.ICartService;
import in.SMW.Repo.CartItemRepo;
import in.SMW.Repo.CartRepo;
import in.SMW.Repo.ProductRepo;
import in.SMW.Request.CartRequest;

@Service
public class CartServiceImpl
		implements ICartService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private CartItemRepo cartItemRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public CartDTO addToCart(
			Integer userId,
			CartRequest request) {

		Cart cart =
				cartRepo.findByUserId(userId)
						.orElseThrow(() ->
								new UserException(
										"Cart Not Found",
										HttpStatus.NOT_FOUND));

		Product product =
				productRepo.findById(
						request.getProductId())
						.orElseThrow(() ->
								new UserException(
										"Product Not Found",
										HttpStatus.NOT_FOUND));

		CartItem cartItem =
				cartItemRepo.findByCartIdAndProductId(
						cart.getId(),
						product.getId())
						.orElse(null);

		if (cartItem == null) {

			cartItem = CartItem.builder()
					.cart(cart)
					.product(product)
					.quantity(
							request.getQuantity())
					.build();
		} else {

			cartItem.setQuantity(
					cartItem.getQuantity()
							+ request.getQuantity());
		}

		CartItem savedItem =
				cartItemRepo.save(cartItem);

		return CartDTO.builder()
				.cartItemId(
						savedItem.getId())
				.productId(
						product.getId())
				.productName(
						product.getProductName())
				.quantity(
						savedItem.getQuantity())
				.price(
						product.getPrice())
				.totalPrice(
						product.getPrice()
								.multiply(
										BigDecimal.valueOf(
												savedItem.getQuantity())))
				.build();
	}

	@Override
	public CartDTO updateCartItem(
			Integer userId,
			CartRequest request) {

		Cart cart =
				cartRepo.findByUserId(userId)
						.orElseThrow(() ->
								new UserException(
										"Cart Not Found",
										HttpStatus.NOT_FOUND));

		CartItem cartItem =
				cartItemRepo.findByCartIdAndProductId(
						cart.getId(),
						request.getProductId())
						.orElseThrow(() ->
								new UserException(
										"Cart Item Not Found",
										HttpStatus.NOT_FOUND));

		cartItem.setQuantity(
				request.getQuantity());

		CartItem updatedItem =
				cartItemRepo.save(cartItem);

		return CartDTO.builder()
				.cartItemId(updatedItem.getId())
				.productId(updatedItem.getProduct().getId())
				.productName(updatedItem.getProduct().getProductName())
				.quantity(updatedItem.getQuantity())
				.price(updatedItem.getProduct().getPrice())
				.totalPrice(
						updatedItem.getProduct().getPrice()
								.multiply(BigDecimal.valueOf(
										updatedItem.getQuantity())))
				.build();
	}

	@Override
	public void removeCartItem(
			Integer userId,
			Integer productId) {

		Cart cart =
				cartRepo.findByUserId(userId)
						.orElseThrow(() ->
								new UserException(
										"Cart Not Found",
										HttpStatus.NOT_FOUND));

		CartItem cartItem =
				cartItemRepo.findByCartIdAndProductId(
						cart.getId(),
						productId)
						.orElseThrow(() ->
								new UserException(
										"Cart Item Not Found",
										HttpStatus.NOT_FOUND));

		cartItemRepo.delete(cartItem);
	}

	@Override
	public List<CartDTO> getCartItems(
			Integer userId) {

		Cart cart =
				cartRepo.findByUserId(userId)
						.orElseThrow(() ->
								new UserException(
										"Cart Not Found",
										HttpStatus.NOT_FOUND));

		return cartItemRepo.findByCartId(
				cart.getId())
				.stream()
				.map(item ->
						CartDTO.builder()
								.cartItemId(item.getId())
								.productId(item.getProduct().getId())
								.productName(item.getProduct().getProductName())
								.quantity(item.getQuantity())
								.price(item.getProduct().getPrice())
								.totalPrice(
										item.getProduct().getPrice()
												.multiply(
														BigDecimal.valueOf(
																item.getQuantity())))
								.build())
				.collect(Collectors.toList());
	}

	@Override
	public void clearCart(
			Integer userId) {

		Cart cart =
				cartRepo.findByUserId(userId)
						.orElseThrow(() ->
								new UserException(
										"Cart Not Found",
										HttpStatus.NOT_FOUND));

		List<CartItem> cartItems =
				cartItemRepo.findByCartId(
						cart.getId());

		cartItemRepo.deleteAll(cartItems);
	}
}