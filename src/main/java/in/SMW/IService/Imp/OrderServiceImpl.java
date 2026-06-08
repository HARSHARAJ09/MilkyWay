package in.SMW.IService.Imp;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.OrderDTO;
import in.SMW.Entity.Cart;
import in.SMW.Entity.CartItem;
import in.SMW.Entity.OrderItem;
import in.SMW.Entity.Orders;
import in.SMW.Entity.User;
import in.SMW.Exception.UserException;
import in.SMW.IService.IOrderService;
import in.SMW.Repo.CartItemRepo;
import in.SMW.Repo.CartRepo;
import in.SMW.Repo.OrderItemRepo;
import in.SMW.Repo.OrderRepo;
import in.SMW.Repo.UserRepo;
import in.SMW.Request.CreateOrderRequest;

@Service
public class OrderServiceImpl
		implements IOrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private CartItemRepo cartItemRepo;

	@Override
	public OrderDTO createOrder(
			Integer userId,
			CreateOrderRequest request) {

		User user =
				userRepo.findById(userId)
						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		Cart cart =
				cartRepo.findByUserId(userId)
						.orElseThrow(() ->
								new UserException(
										"Cart Not Found",
										HttpStatus.NOT_FOUND));

		List<CartItem> cartItems =
				cartItemRepo.findByCartId(
						cart.getId());

		if (cartItems.isEmpty()) {

			throw new UserException(
					"Cart Is Empty",
					HttpStatus.BAD_REQUEST);
		}

		BigDecimal totalAmount =
				BigDecimal.ZERO;

		for (CartItem item : cartItems) {

			totalAmount =
					totalAmount.add(
							item.getProduct()
									.getPrice()
									.multiply(
											BigDecimal.valueOf(
													item.getQuantity())));
		}

		Orders order =
				Orders.builder()
						.user(user)
						.totalAmount(totalAmount)
						.orderStatus("PLACED")
						.paymentStatus("PENDING")
						.createdAt(LocalDateTime.now())
						.build();

		Orders savedOrder =
				orderRepo.save(order);

		for (CartItem item : cartItems) {

			OrderItem orderItem =
					OrderItem.builder()
							.orders(savedOrder)
							.product(item.getProduct())
							.quantity(item.getQuantity())
							.price(item.getProduct().getPrice())
							.build();

			orderItemRepo.save(orderItem);
		}

		cartItemRepo.deleteAll(cartItems);

		return OrderDTO.builder()
				.orderId(savedOrder.getId())
				.userId(user.getId())
				.totalAmount(savedOrder.getTotalAmount())
				.orderStatus(savedOrder.getOrderStatus())
				.paymentStatus(savedOrder.getPaymentStatus())
				.createdAt(savedOrder.getCreatedAt())
				.build();
	}

	@Override
	public OrderDTO getOrderById(
			Integer orderId) {

		Orders order =
				orderRepo.findById(orderId)
						.orElseThrow(() ->
								new UserException(
										"Order Not Found",
										HttpStatus.NOT_FOUND));

		return OrderDTO.builder()
				.orderId(order.getId())
				.userId(order.getUser().getId())
				.totalAmount(order.getTotalAmount())
				.orderStatus(order.getOrderStatus())
				.paymentStatus(order.getPaymentStatus())
				.createdAt(order.getCreatedAt())
				.build();
	}

	@Override
	public List<OrderDTO> getUserOrders(
			Integer userId) {

		return orderRepo.findByUserId(userId)
				.stream()
				.map(order ->
						OrderDTO.builder()
								.orderId(order.getId())
								.userId(order.getUser().getId())
								.totalAmount(order.getTotalAmount())
								.orderStatus(order.getOrderStatus())
								.paymentStatus(order.getPaymentStatus())
								.createdAt(order.getCreatedAt())
								.build())
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getAllOrders() {

		return orderRepo.findAll()
				.stream()
				.map(order ->
						OrderDTO.builder()
								.orderId(order.getId())
								.userId(order.getUser().getId())
								.totalAmount(order.getTotalAmount())
								.orderStatus(order.getOrderStatus())
								.paymentStatus(order.getPaymentStatus())
								.createdAt(order.getCreatedAt())
								.build())
				.collect(Collectors.toList());
	}
}