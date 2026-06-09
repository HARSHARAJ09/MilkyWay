package in.SMW.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findByActiveTrue();

	List<Product> findByCategoryId(Integer categoryId);

	List<Product> findByProductNameContainingIgnoreCase(String productName);

	Boolean existsByProductName(String productName);
}
