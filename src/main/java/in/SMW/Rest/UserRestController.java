package in.SMW.Rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.SMW.DTO.UserDTO;
import in.SMW.IService.IUserService;
import in.SMW.Responses.UserResponse;
import in.SMW.Security.CustomUserDetails;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private IUserService userService;

	@GetMapping("/profile")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getProfile(
			@AuthenticationPrincipal
			CustomUserDetails userDetails) {

		UserResponse response =
				userService.getUserById(
						userDetails.getUserId());

		return ResponseEntity.ok(
				response);
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(
			@PathVariable
			Integer userId) {

		return ResponseEntity.ok(
				userService.getUserById(
						userId));
	}

	@PutMapping("/profile")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> updateProfile(
			@AuthenticationPrincipal
			CustomUserDetails userDetails,

			@RequestBody
			UserDTO userDTO) {

		return ResponseEntity.ok(
				userService.updateUser(
						userDetails.getUserId(),
						userDTO));
	}
}