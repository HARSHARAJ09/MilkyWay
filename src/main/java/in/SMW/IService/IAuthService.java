package in.SMW.IService;

import in.SMW.Request.LoginRequest;
import in.SMW.Request.RegisterRequest;
import in.SMW.Responses.LoginResponse;

public interface IAuthService {

	LoginResponse login(LoginRequest request);

	String register(RegisterRequest request);

}