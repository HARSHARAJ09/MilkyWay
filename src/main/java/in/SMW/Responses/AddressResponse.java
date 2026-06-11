package in.SMW.Responses;


import lombok.Data;

@Data
public class AddressResponse {

	private Integer addressId;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String pincode;

	private String landmark;

	private Boolean defaultAddress;
}