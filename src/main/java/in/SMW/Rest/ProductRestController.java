package in.SMW.Rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import in.SMW.IService.IProductService;
import in.SMW.Request.ProductRequest;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	private IProductService productService;

	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createProduct(
			@RequestBody ProductRequest request) {

		return new ResponseEntity<>(
				productService.createProduct(request),
				HttpStatus.CREATED);
	}

	
	@PutMapping("/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateProduct(
			@PathVariable Integer productId,
			@RequestBody ProductRequest request) {

		return ResponseEntity.ok(
				productService.updateProduct(
						productId,
						request));
	}

	
	@DeleteMapping("/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProduct(
			@PathVariable Integer productId) {

		productService.deleteProduct(productId);

		return ResponseEntity.ok(
				"Product Deleted Successfully");
	}

	
	@GetMapping("/{productId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> getProductById(
			@PathVariable Integer productId) {

		return ResponseEntity.ok(
				productService.getProductById(
						productId));
	}

	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> getAllProducts() {

		return ResponseEntity.ok(
				productService.getAllProducts());
	}

	
	@GetMapping("/category/{categoryId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> getProductsByCategory(
			@PathVariable Integer categoryId) {

		return ResponseEntity.ok(
				productService.getProductsByCategory(
						categoryId));
	}

	
	@GetMapping("/search")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> searchProducts(
			@RequestParam String keyword) {

		return ResponseEntity.ok(
				productService.searchProducts(
						keyword));
	}
}