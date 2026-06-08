package in.SMW.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

	@NotBlank
	private String categoryName;

	private String description;

}