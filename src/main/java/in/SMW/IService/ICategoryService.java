package in.SMW.IService;

import java.util.List;

import in.SMW.DTO.CategoryDTO;
import in.SMW.Request.CategoryRequest;

public interface ICategoryService {

	CategoryDTO createCategory(CategoryRequest request);

	CategoryDTO updateCategory(
			Integer categoryId,
			CategoryRequest request);

	void deleteCategory(Integer categoryId);

	CategoryDTO getCategoryById(Integer categoryId);

	List<CategoryDTO> getAllCategories();

}