package in.SMW.Utils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import in.SMW.Entity.Role;
import in.SMW.Entity.User;
import in.SMW.Repo.RoleRepo;
import in.SMW.Repo.UserRepo;

@Component
public class AdminInitializer implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (!userRepo.existsByEmail("admin@milkyway.com")) {

			Role adminRole = roleRepo.findByRoleName("ROLE_ADMIN");

			if (adminRole != null) {

				Set<Role> roles = new HashSet<>();

				roles.add(adminRole);

				User admin = User.builder()

						.firstName("Milky")

						.lastName("Admin")

						.email("admin@milkyway.com")

						.phone("9449993269")

						.password(passwordEncoder.encode("Harsha@99"))

						.enabled(true)

						.createdAt(LocalDateTime.now())

						.updatedAt(LocalDateTime.now())

						.roles(roles)

						.build();

				userRepo.save(admin);

				System.out.println("Milky-Way Admin Created Successfully");
			}
		}
	}
}