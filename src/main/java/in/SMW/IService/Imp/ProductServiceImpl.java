package in.SMW.IService.Imp;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.ProductDTO;
import in.SMW.Entity.Category;
import in.SMW.Entity.Product;
import in.SMW.Exception.UserException;
import in.SMW.IService.IProductService;
import in.SMW.Repo.CategoryRepo;
import in.SMW.Repo.ProductRepo;
import in.SMW.Request.ProductRequest;

@Service
public class ProductServiceImpl
		implements IProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO createProduct(
			ProductRequest request) {

		Category category =
				categoryRepo.findById(
						request.getCategoryId())
						.orElseThrow(() ->
								new UserException(
										"Category Not Found",
										HttpStatus.NOT_FOUND));

		Product product =
				Product.builder()
						.productName(
								request.getProductName())
						.description(
								request.getDescription())
						.price(
								request.getPrice())
						.quantity(
								request.getQuantity())
						.imageUrl(
								request.getImageUrl())
						.active(
								request.getActive())
						.category(category)
						.build();

		Product savedProduct =
				productRepo.save(product);

		ProductDTO dto =
				modelMapper.map(
						savedProduct,
						ProductDTO.class);

		dto.setCategoryId(
				category.getId());

		dto.setCategoryName(
				category.getCategoryName());

		return dto;
	}

	@Override
	public ProductDTO updateProduct(
			Integer productId,
			ProductRequest request) {

		Product product =
				productRepo.findById(productId)
						.orElseThrow(() ->
								new UserException(
										"Product Not Found",
										HttpStatus.NOT_FOUND));

		Category category =
				categoryRepo.findById(
						request.getCategoryId())
						.orElseThrow(() ->
								new UserException(
										"Category Not Found",
										HttpStatus.NOT_FOUND));

		product.setProductName(
				request.getProductName());

		product.setDescription(
				request.getDescription());

		product.setPrice(
				request.getPrice());

		product.setQuantity(
				request.getQuantity());

		product.setImageUrl(
				request.getImageUrl());

		product.setActive(
				request.getActive());

		product.setCategory(category);

		Product updatedProduct =
				productRepo.save(product);

		ProductDTO dto =
				modelMapper.map(
						updatedProduct,
						ProductDTO.class);

		dto.setCategoryId(
				category.getId());

		dto.setCategoryName(
				category.getCategoryName());

		return dto;
	}

	@Override
	public void deleteProduct(
			Integer productId) {

		Product product =
				productRepo.findById(productId)
						.orElseThrow(() ->
								new UserException(
										"Product Not Found",
										HttpStatus.NOT_FOUND));

		productRepo.delete(product);
	}

	@Override
	public ProductDTO getProductById(
			Integer productId) {

		Product product =
				productRepo.findById(productId)
						.orElseThrow(() ->
								new UserException(
										"Product Not Found",
										HttpStatus.NOT_FOUND));

		ProductDTO dto =
				modelMapper.map(
						product,
						ProductDTO.class);

		dto.setCategoryId(
				product.getCategory().getId());

		dto.setCategoryName(
				product.getCategory().getCategoryName());

		return dto;
	}

	@Override
	public List<ProductDTO> getAllProducts() {

		return productRepo.findByActiveTrue()
				.stream()
				.map(product -> {

					ProductDTO dto =
							modelMapper.map(
									product,
									ProductDTO.class);

					dto.setCategoryId(
							product.getCategory().getId());

					dto.setCategoryName(
							product.getCategory()
									.getCategoryName());

					return dto;

				})
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getProductsByCategory(
			Integer categoryId) {

		return productRepo.findByCategoryId(
				categoryId)
				.stream()
				.map(product -> {

					ProductDTO dto =
							modelMapper.map(
									product,
									ProductDTO.class);

					dto.setCategoryId(
							product.getCategory().getId());

					dto.setCategoryName(
							product.getCategory()
									.getCategoryName());

					return dto;

				})
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> searchProducts(
			String productName) {

		return productRepo
				.findByProductNameContainingIgnoreCase(
						productName)
				.stream()
				.map(product -> {

					ProductDTO dto =
							modelMapper.map(
									product,
									ProductDTO.class);

					dto.setCategoryId(
							product.getCategory().getId());

					dto.setCategoryName(
							product.getCategory()
									.getCategoryName());

					return dto;

				})
				.collect(Collectors.toList());
	}
}