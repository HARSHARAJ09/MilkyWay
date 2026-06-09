package in.SMW.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import in.SMW.IService.IAuthService;
import in.SMW.Request.LoginRequest;
import in.SMW.Request.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

	@Autowired
	private IAuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(
			@RequestBody @Validated RegisterRequest request) {

		return new ResponseEntity<>(
				authService.register(request),
				HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(
			@RequestBody @Validated LoginRequest request) {

		return ResponseEntity.ok(
				authService.login(request));
	}
}