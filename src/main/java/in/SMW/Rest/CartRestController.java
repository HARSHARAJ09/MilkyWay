package in.SMW.Rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import in.SMW.IService.ICartService;
import in.SMW.Request.CartRequest;
import in.SMW.Security.CustomUserDetails;

@RestController
@RequestMapping("/cart")
@PreAuthorize("hasRole('CUSTOMER')")
public class CartRestController {

	@Autowired
	private ICartService cartService;

	
	@PostMapping
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> addToCart(
			@AuthenticationPrincipal
			CustomUserDetails userDetails,

			@RequestBody CartRequest request) {

		return ResponseEntity.ok(
				cartService.addToCart(
						userDetails.getUserId(),
						request));
	}

	
	@PutMapping
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> updateCartItem(
			@AuthenticationPrincipal
			CustomUserDetails userDetails,

			@RequestBody CartRequest request) {

		return ResponseEntity.ok(
				cartService.updateCartItem(
						userDetails.getUserId(),
						request));
	}

	
	@DeleteMapping("/{productId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> removeCartItem(
			@AuthenticationPrincipal
			CustomUserDetails userDetails,

			@PathVariable Integer productId) {

		cartService.removeCartItem(
				userDetails.getUserId(),
				productId);

		return ResponseEntity.ok(
				"Cart Item Removed Successfully");
	}

	@GetMapping
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getCartItems(
			@AuthenticationPrincipal
			CustomUserDetails userDetails) {

		return ResponseEntity.ok(
				cartService.getCartItems(
						userDetails.getUserId()));
	}

	
	@DeleteMapping("/clear")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> clearCart(
			@AuthenticationPrincipal
			CustomUserDetails userDetails) {

		cartService.clearCart(
				userDetails.getUserId());

		return ResponseEntity.ok(
				"Cart Cleared Successfully");
	}
}