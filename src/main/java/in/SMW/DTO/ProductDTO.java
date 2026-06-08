package in.SMW.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

	private Integer id;

	private String productName;

	private String description;

	private BigDecimal price;

	private Integer quantity;

	private String imageUrl;

	private Boolean active;

	private Integer categoryId;

	private String categoryName;

}