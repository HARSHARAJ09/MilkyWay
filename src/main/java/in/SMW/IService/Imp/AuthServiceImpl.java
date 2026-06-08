package in.SMW.IService.Imp;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.SMW.Entity.Cart;
import in.SMW.Entity.Role;
import in.SMW.Entity.User;
import in.SMW.Exception.UserException;
import in.SMW.IService.IAuthService;
import in.SMW.Repo.CartRepo;
import in.SMW.Repo.RoleRepo;
import in.SMW.Repo.UserRepo;
import in.SMW.Request.LoginRequest;
import in.SMW.Request.RegisterRequest;
import in.SMW.Responses.LoginResponse;
import in.SMW.Security.JwtUtil;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String register(
			RegisterRequest request) {

		if (userRepo.existsByEmail(
				request.getEmail())) {

			throw new UserException(
					"Email Already Registered",
					HttpStatus.BAD_REQUEST);
		}

		if (userRepo.existsByPhone(
				request.getPhone())) {

			throw new UserException(
					"Phone Number Already Registered",
					HttpStatus.BAD_REQUEST);
		}

		Role customerRole =
				roleRepo.findByRoleName(
						"ROLE_CUSTOMER");

		if (customerRole == null) {

			throw new UserException(
					"Customer Role Not Found",
					HttpStatus.NOT_FOUND);
		}

		User user = User.builder()
				.firstName(
						request.getFirstName())
				.lastName(
						request.getLastName())
				.email(
						request.getEmail())
				.phone(
						request.getPhone())
				.password(
						passwordEncoder.encode(
								request.getPassword()))
				.enabled(true)
				.createdAt(
						LocalDateTime.now())
				.updatedAt(
						LocalDateTime.now())
				.build();

		user.getRoles()
				.add(customerRole);

		User savedUser =
				userRepo.save(user);

		Cart cart =
				Cart.builder()
						.user(savedUser)
						.build();

		cartRepo.save(cart);

		return "Registration Successful";
	}

	@Override
	public LoginResponse login(
			LoginRequest request) {

		authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(

						request.getEmail(),

						request.getPassword()));

		User user =
				userRepo.findByEmail(
						request.getEmail())

						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		Map<String, Object> claims =
				new HashMap<>();

		String role =
				user.getRoles()
						.stream()
						.findFirst()
						.get()
						.getRoleName();

		claims.put(
				"role",
				role);

		String token =
				jwtUtil.generateToken(
						user.getEmail(),
						claims);

		return LoginResponse.builder()

				.token(token)

				.email(user.getEmail())

				.role(role)

				.message(
						"Login Successful")

				.build();
	}
}