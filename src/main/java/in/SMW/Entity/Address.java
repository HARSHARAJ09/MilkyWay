package in.SMW.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

	@Id
	@GeneratedValue(strategy =
			GenerationType.IDENTITY)
	private Integer addressId;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String pincode;

	private String landmark;

	private Boolean defaultAddress;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}