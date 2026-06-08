package in.SMW.IService.Imp;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.CategoryDTO;
import in.SMW.Entity.Category;
import in.SMW.Exception.UserException;
import in.SMW.IService.ICategoryService;
import in.SMW.Repo.CategoryRepo;
import in.SMW.Request.CategoryRequest;

@Service
public class CategoryServiceImpl
		implements ICategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(
			CategoryRequest request) {

		if (categoryRepo.existsByCategoryName(
				request.getCategoryName())) {

			throw new UserException(
					"Category Already Exists",
					HttpStatus.BAD_REQUEST);
		}

		Category category = Category.builder()
				.categoryName(
						request.getCategoryName())
				.description(
						request.getDescription())
				.build();

		return modelMapper.map(
				categoryRepo.save(category),
				CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(
			Integer categoryId,
			CategoryRequest request) {

		Category category =
				categoryRepo.findById(categoryId)
						.orElseThrow(() ->
								new UserException(
										"Category Not Found",
										HttpStatus.NOT_FOUND));

		category.setCategoryName(
				request.getCategoryName());

		category.setDescription(
				request.getDescription());

		return modelMapper.map(
				categoryRepo.save(category),
				CategoryDTO.class);
	}

	@Override
	public void deleteCategory(
			Integer categoryId) {

		Category category =
				categoryRepo.findById(categoryId)
						.orElseThrow(() ->
								new UserException(
										"Category Not Found",
										HttpStatus.NOT_FOUND));

		categoryRepo.delete(category);
	}

	@Override
	public CategoryDTO getCategoryById(
			Integer categoryId) {

		Category category =
				categoryRepo.findById(categoryId)
						.orElseThrow(() ->
								new UserException(
										"Category Not Found",
										HttpStatus.NOT_FOUND));

		return modelMapper.map(
				category,
				CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {

		return categoryRepo.findAll()
				.stream()
				.map(category ->
						modelMapper.map(
								category,
								CategoryDTO.class))
				.collect(Collectors.toList());
	}
}