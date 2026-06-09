package in.SMW.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	private final JwtRequestFilter jwtRequestFilter;

	public SecurityConfiguration(JwtRequestFilter jwtRequestFilter) {

		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity

				.csrf(csrf -> csrf.disable())

				.cors(Customizer.withDefaults())

				.authorizeHttpRequests(request -> request

						.requestMatchers(

								"/auth/**",

								"/v3/api-docs/**",

								"/swagger-ui/**",

								"/swagger-ui.html")

						.permitAll()

						.requestMatchers("/admin/**")

						.hasRole("ADMIN")

						.requestMatchers("/cart/**", "/orders/**")

						.hasAnyRole("ADMIN", "CUSTOMER")

						.anyRequest().authenticated())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public OpenAPI customOpenAPI() {

		return new OpenAPI()

				.info(new Info()

						.title("Milky-Way Dairy API")

						.version("1.0")

						.description("Milky-Way Dairy E-Commerce Backend API"))

				.addSecurityItem(new SecurityRequirement()

						.addList("Bearer Authentication"))

				.components(

						new Components()

								.addSecuritySchemes("Bearer Authentication",

										new SecurityScheme()

												.name("Bearer Authentication")

												.type(SecurityScheme.Type.HTTP)

												.scheme("bearer")

												.bearerFormat("JWT")));
	}

}