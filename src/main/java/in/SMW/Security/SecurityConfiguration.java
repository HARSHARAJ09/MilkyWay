package in.SMW.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	private final JwtRequestFilter jwtRequestFilter;

	public SecurityConfiguration(
			JwtRequestFilter jwtRequestFilter) {

		this.jwtRequestFilter =
				jwtRequestFilter;
	}

	@Bean
	public SecurityFilterChain
	securityFilterChain(
			HttpSecurity httpSecurity)
			throws Exception {

		httpSecurity

				.csrf(csrf -> csrf.disable())

				.cors(Customizer.withDefaults())

				.authorizeHttpRequests(
						request -> request

								.requestMatchers(

										"/auth/**",

										"/v3/api-docs/**",

										"/swagger-ui/**",

										"/swagger-ui.html")

								.permitAll()

								.requestMatchers(
										"/admin/**")

								.hasRole("ADMIN")

								.requestMatchers(
										"/cart/**",
										"/orders/**")

								.hasAnyRole(
										"ADMIN",
										"CUSTOMER")

								.anyRequest()
								.authenticated())

				.sessionManagement(
						session -> session
								.sessionCreationPolicy(
										SessionCreationPolicy.STATELESS));

		httpSecurity.addFilterBefore(
				jwtRequestFilter,
				UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

}