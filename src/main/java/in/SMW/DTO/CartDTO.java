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
public class CartDTO {

	private Integer cartItemId;

	private Integer productId;

	private String productName;

	private Integer quantity;

	private BigDecimal price;

	private BigDecimal totalPrice;

}
