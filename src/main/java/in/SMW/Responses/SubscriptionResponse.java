package in.SMW.Responses;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SubscriptionResponse {

	private Integer subscriptionId;

	private Integer productId;

	private String productName;

	private Integer quantity;

	private String frequency;

	private LocalDate startDate;

	private LocalDate endDate;

	private String status;
}