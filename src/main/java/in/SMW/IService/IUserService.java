package in.SMW.IService;

import java.util.List;

import in.SMW.DTO.UserDTO;

public interface IUserService {

	UserDTO getUserById(Integer userId);

	List<UserDTO> getAllUsers();

	void deleteUser(Integer userId);

}