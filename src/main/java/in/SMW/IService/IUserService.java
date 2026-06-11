package in.SMW.IService;

import java.util.List;

import in.SMW.DTO.UserDTO;
import in.SMW.Responses.UserResponse;

public interface IUserService {

//	UserDTO getUserById(Integer userId);

	List<UserDTO> getAllUsers();

	void deleteUser(Integer userId);

	UserResponse getUserById(Integer userId);

	UserResponse updateUser(Integer userId, UserDTO userDTO);

	
}