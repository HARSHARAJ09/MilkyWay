package in.SMW.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	Boolean existsByCategoryName(String categoryName);

	Category findByCategoryName(String categoryName);
}
