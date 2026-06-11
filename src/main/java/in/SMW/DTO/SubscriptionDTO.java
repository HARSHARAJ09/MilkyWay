package in.SMW.DTO;

import java.time.LocalDate;

import in.SMW.Entity.Frequency;

import lombok.Data;

@Data
public class SubscriptionDTO {

	private Integer productId;

	private Integer quantity;

	private Frequency frequency;

	private LocalDate startDate;

	private LocalDate endDate;
}