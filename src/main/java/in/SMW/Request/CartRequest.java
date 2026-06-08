package in.SMW.Request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartRequest {

	@NotNull
	private Integer productId;

	@NotNull
	private Integer quantity;

}