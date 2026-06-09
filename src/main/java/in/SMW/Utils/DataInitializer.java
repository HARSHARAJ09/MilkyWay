package in.SMW.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.SMW.Entity.Role;
import in.SMW.Repo.RoleRepo;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		if (roleRepo.findByRoleName("ROLE_ADMIN") == null) {

			Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();

			roleRepo.save(adminRole);
		}

		if (roleRepo.findByRoleName("ROLE_CUSTOMER") == null) {

			Role customerRole = Role.builder().roleName("ROLE_CUSTOMER").build();

			roleRepo.save(customerRole);
		}

		System.out.println("Milky-Way Roles Initialized Successfully");
	}
}