package in.SMW.IService;


import java.util.List;

import in.SMW.DTO.OrderDTO;
import in.SMW.Request.CreateOrderRequest;

public interface IOrderService {

	OrderDTO createOrder(
			Integer userId,
			CreateOrderRequest request);

	OrderDTO getOrderById(
			Integer orderId);

	List<OrderDTO> getUserOrders(
			Integer userId);

	List<OrderDTO> getAllOrders();

}