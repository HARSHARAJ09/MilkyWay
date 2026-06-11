package in.SMW.Rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import in.SMW.IService.IOrderService;
import in.SMW.Request.CreateOrderRequest;
import in.SMW.Security.CustomUserDetails;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

	@Autowired
	private IOrderService orderService;

	
	@PostMapping("/place")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> createOrder(
			@AuthenticationPrincipal
			CustomUserDetails userDetails,

			@RequestBody CreateOrderRequest request) {

//		return ResponseEntity.ok(
//				orderService.createOrder(
//						userDetails.getUserId(),
//						request));
//	}
		try {

			return ResponseEntity.ok(
					orderService.createOrder(
							userDetails.getUserId(),
							request));

		} catch (Exception e) {

			e.printStackTrace();

			throw e;
		}
	}

	@GetMapping("/{orderId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> getOrderById(
			@PathVariable Integer orderId) {

		return ResponseEntity.ok(
				orderService.getOrderById(
						orderId));
	}

	
	@GetMapping("/my-orders")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getUserOrders(
			@AuthenticationPrincipal
			CustomUserDetails userDetails) {

		return ResponseEntity.ok(
				orderService.getUserOrders(
						userDetails.getUserId()));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getAllOrders() {

		return ResponseEntity.ok(
				orderService.getAllOrders());
	}
}
