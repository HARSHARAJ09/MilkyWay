package in.SMW.IService;


import java.util.List;

import in.SMW.DTO.ProductDTO;
import in.SMW.Request.ProductRequest;

public interface IProductService {

	ProductDTO createProduct(ProductRequest request);

	ProductDTO updateProduct(
			Integer productId,
			ProductRequest request);

	void deleteProduct(Integer productId);

	ProductDTO getProductById(Integer productId);

	List<ProductDTO> getAllProducts();

	List<ProductDTO> getProductsByCategory(
			Integer categoryId);

	List<ProductDTO> searchProducts(
			String productName);

}