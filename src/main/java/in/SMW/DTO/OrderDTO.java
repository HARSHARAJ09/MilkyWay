package in.SMW.DTO;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

	private Integer orderId;

	private Integer userId;

	private BigDecimal totalAmount;

	private String orderStatus;

	private String paymentStatus;

	private LocalDateTime createdAt;

}