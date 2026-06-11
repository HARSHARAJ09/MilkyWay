package in.SMW.IService.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.AddressDTO;
import in.SMW.Entity.Address;
import in.SMW.Entity.User;
import in.SMW.Exception.UserException;
import in.SMW.IService.IAddressService;
import in.SMW.Repo.AddressRepo;
import in.SMW.Repo.UserRepo;
import in.SMW.Responses.AddressResponse;

@Service
public class AddressServiceImpl
		implements IAddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public AddressResponse addAddress(
			Integer userId,
			AddressDTO dto) {

		User user =
				userRepo.findById(userId)
						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		Address address =
				Address.builder()
						.addressLine1(
								dto.getAddressLine1())
						.addressLine2(
								dto.getAddressLine2())
						.city(
								dto.getCity())
						.state(
								dto.getState())
						.pincode(
								dto.getPincode())
						.landmark(
								dto.getLandmark())
						.defaultAddress(
								dto.getDefaultAddress())
						.user(user)
						.build();

		addressRepo.save(address);

		return mapToResponse(
				address);
	}

	@Override
	public List<AddressResponse>
	getUserAddresses(
			Integer userId) {

		return addressRepo
				.findByUserId(userId)
				.stream()
				.map(this::mapToResponse)
				.collect(
						Collectors.toList());
	}

	@Override
	public AddressResponse updateAddress(
			Integer addressId,
			AddressDTO dto) {

		Address address =
				addressRepo.findById(
						addressId)
						.orElseThrow(() ->
								new UserException(
										"Address Not Found",
										HttpStatus.NOT_FOUND));

		address.setAddressLine1(
				dto.getAddressLine1());

		address.setAddressLine2(
				dto.getAddressLine2());

		address.setCity(
				dto.getCity());

		address.setState(
				dto.getState());

		address.setPincode(
				dto.getPincode());

		address.setLandmark(
				dto.getLandmark());

		address.setDefaultAddress(
				dto.getDefaultAddress());

		addressRepo.save(address);

		return mapToResponse(
				address);
	}

	@Override
	public void deleteAddress(
			Integer addressId) {

		addressRepo.deleteById(
				addressId);
	}

	private AddressResponse
	mapToResponse(
			Address address) {

		AddressResponse response =
				new AddressResponse();

		response.setAddressId(
				address.getAddressId());

		response.setAddressLine1(
				address.getAddressLine1());

		response.setAddressLine2(
				address.getAddressLine2());

		response.setCity(
				address.getCity());

		response.setState(
				address.getState());

		response.setPincode(
				address.getPincode());

		response.setLandmark(
				address.getLandmark());

		response.setDefaultAddress(
				address.getDefaultAddress());

		return response;
	}
}