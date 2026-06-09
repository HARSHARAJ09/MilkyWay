package in.SMW.Rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import in.SMW.IService.ICategoryService;
import in.SMW.Request.CategoryRequest;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

	@Autowired
	private ICategoryService categoryService;

	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createCategory(
			@RequestBody CategoryRequest request) {

		return new ResponseEntity<>(
				categoryService.createCategory(request),
				HttpStatus.CREATED);
	}

	
	@PutMapping("/{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateCategory(
			@PathVariable Integer categoryId,
			@RequestBody CategoryRequest request) {

		return ResponseEntity.ok(
				categoryService.updateCategory(
						categoryId,
						request));
	}

	
	@DeleteMapping("/{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCategory(
			@PathVariable Integer categoryId) {

		categoryService.deleteCategory(categoryId);

		return ResponseEntity.ok(
				"Category Deleted Successfully");
	}

	
	@GetMapping("/{categoryId}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> getCategoryById(
			@PathVariable Integer categoryId) {

		return ResponseEntity.ok(
				categoryService.getCategoryById(
						categoryId));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<?> getAllCategories() {

		return ResponseEntity.ok(
				categoryService.getAllCategories());
	}
}