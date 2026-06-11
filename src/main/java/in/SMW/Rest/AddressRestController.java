package in.SMW.Rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import in.SMW.DTO.AddressDTO;
import in.SMW.IService.IAddressService;
import in.SMW.Security.CustomUserDetails;

@RestController
@RequestMapping("/addresses")
public class AddressRestController {

	@Autowired
	private IAddressService addressService;

	@PostMapping
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> addAddress(
			@AuthenticationPrincipal
			CustomUserDetails userDetails,

			@RequestBody
			AddressDTO dto) {

		return ResponseEntity.ok(
				addressService.addAddress(
						userDetails.getUserId(),
						dto));
	}

	@GetMapping
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getAddresses(
			@AuthenticationPrincipal
			CustomUserDetails userDetails) {

		return ResponseEntity.ok(
				addressService.getUserAddresses(
						userDetails.getUserId()));
	}

	@PutMapping("/{addressId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> updateAddress(
			@PathVariable
			Integer addressId,

			@RequestBody
			AddressDTO dto) {

		return ResponseEntity.ok(
				addressService.updateAddress(
						addressId,
						dto));
	}

	@DeleteMapping("/{addressId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> deleteAddress(
			@PathVariable
			Integer addressId) {

		addressService.deleteAddress(
				addressId);

		return ResponseEntity.ok(
				"Address Deleted Successfully");
	}
}
