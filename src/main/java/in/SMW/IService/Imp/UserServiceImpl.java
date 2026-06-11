package in.SMW.IService.Imp;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.UserDTO;
import in.SMW.Entity.User;
import in.SMW.Exception.UserException;
import in.SMW.IService.IUserService;
import in.SMW.Repo.UserRepo;
import in.SMW.Responses.UserResponse;

@Service
public class UserServiceImpl
		implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

//	@Override
//	public UserDTO getUserById(
//			Integer userId) {
//
//		User user =
//				userRepo.findById(userId)
//						.orElseThrow(() ->
//								new UserException(
//										"User Not Found",
//										HttpStatus.NOT_FOUND));
//
//		return modelMapper.map(
//				user,
//				UserDTO.class);
//	}
	
	@Override
	public UserResponse getUserById(
			Integer userId) {

		User user =
				userRepo.findById(userId)
						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		UserResponse response =
				new UserResponse();

		response.setId(
				user.getId());

		response.setFirstName(
				user.getFirstName());

		response.setLastName(
				user.getLastName());

		response.setEmail(
				user.getEmail());

		response.setPhone(
				user.getPhone());

		response.setEnabled(
				user.getEnabled());

		return response;
	}

	@Override
	public List<UserDTO> getAllUsers() {

		return userRepo.findAll()
				.stream()
				.map(user ->
						modelMapper.map(
								user,
								UserDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteUser(
			Integer userId) {

		User user =
				userRepo.findById(userId)
						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		userRepo.delete(user);
	}
	
	@Override
	public UserResponse updateUser(
			Integer userId,
			UserDTO userDTO) {

		User user =
				userRepo.findById(userId)
						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		user.setFirstName(
				userDTO.getFirstName());

		user.setLastName(
				userDTO.getLastName());

		user.setPhone(
				userDTO.getPhone());

		userRepo.save(
				user);

		return getUserById(
				userId);
	}
	
}