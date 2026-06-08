package in.SMW.Request;


import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

	@NotBlank
	private String productName;

	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Integer quantity;

	private String imageUrl;

	private Boolean active;

	@NotNull
	private Integer categoryId;

}