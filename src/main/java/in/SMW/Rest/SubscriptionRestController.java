package in.SMW.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import in.SMW.DTO.SubscriptionDTO;
import in.SMW.IService.ISubscriptionService;
import in.SMW.Security.CustomUserDetails;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionRestController {

	@Autowired
	private ISubscriptionService subscriptionService;

	@PostMapping
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> createSubscription(@AuthenticationPrincipal CustomUserDetails userDetails,

			@RequestBody SubscriptionDTO dto) {

		return ResponseEntity.ok(subscriptionService.createSubscription(userDetails.getUserId(), dto));
	}

	@GetMapping("/my")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getMySubscriptions(@AuthenticationPrincipal CustomUserDetails userDetails) {

		return ResponseEntity.ok(subscriptionService.getMySubscriptions(userDetails.getUserId()));
	}

	@PutMapping("/pause/{subscriptionId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> pause(@PathVariable Integer id) {

		return ResponseEntity.ok(subscriptionService.pauseSubscription(id));
	}

	@PutMapping("/resume/{subscriptionId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> resume(@PathVariable Integer id) {

		return ResponseEntity.ok(subscriptionService.resumeSubscription(id));
	}

	@PutMapping("/cancel/{subscriptionId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> cancel(@PathVariable Integer id) {

		return ResponseEntity.ok(subscriptionService.cancelSubscription(id));
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllSubscriptions() {

		return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
	}
}