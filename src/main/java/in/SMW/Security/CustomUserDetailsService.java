package in.SMW.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.SMW.Entity.User;
import in.SMW.Repo.UserRepo;

@Service
public class CustomUserDetailsService
		implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
						//or
	
//	private final UserRepo userRepo;
//
//	public CustomUserDetailsService(
//			UserRepo userRepo) {
//
//		this.userRepo = userRepo;
//	}

//	@Override
//	public UserDetails loadUserByUsername(
//			String email)
//			throws UsernameNotFoundException {
//
//		User user = userRepo.findByEmail(email)
//				.orElseThrow(() -> new UsernameNotFoundException(
//						"User Not Found"));
//
//		Collection<? extends GrantedAuthority>
//		authorities = user.getRoles()
//				.stream()
//				.map(role -> new SimpleGrantedAuthority(
//						role.getRoleName()))
//				.collect(Collectors.toList());
//
//		return new CustomUserDetails(user);
//	}
	@Override
	public UserDetails loadUserByUsername(
			String email)
			throws UsernameNotFoundException {

		User user =
				userRepo.findByEmail(email)
						.orElseThrow(() ->
								new UsernameNotFoundException(
										"User Not Found"));

		return new CustomUserDetails(user);
	}

}