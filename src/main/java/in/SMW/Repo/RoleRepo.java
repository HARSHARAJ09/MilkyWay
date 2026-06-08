package in.SMW.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	Role findByRoleName(String roleName);
}
