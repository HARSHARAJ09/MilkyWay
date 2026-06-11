package in.SMW.IService;


import java.util.List;

import in.SMW.DTO.AddressDTO;
import in.SMW.Responses.AddressResponse;

public interface IAddressService {

	AddressResponse addAddress(
			Integer userId,
			AddressDTO dto);

	List<AddressResponse>
	getUserAddresses(
			Integer userId);

	AddressResponse updateAddress(
			Integer addressId,
			AddressDTO dto);

	void deleteAddress(
			Integer addressId);
}