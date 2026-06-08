package in.SMW.Security;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.SMW.Entity.User;

public class CustomUserDetails
		implements UserDetails {

	private User user;

	private Integer userId;

	private String email;

	private String password;

	public CustomUserDetails(
			User user) {

		this.user = user;
		this.userId = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

//	@Override
//	public Collection<? extends GrantedAuthority>
//	getAuthorities() {
//
//		return user.getRoles()
//				.stream()
//				.map(role ->
//						(org.springframework.security.core.authority.SimpleGrantedAuthority)
//						() -> role.getRoleName())
//				.collect(Collectors.toList());
//	}
	@Override
	public Collection<? extends GrantedAuthority>
	getAuthorities() {

		return user.getRoles()
				.stream()
				.map(role ->
						new SimpleGrantedAuthority(
								role.getRoleName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return email;
	}

	public Integer getUserId() {

		return userId;
	}

	public User getUser() {

		return user;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return user.getEnabled();
	}
}