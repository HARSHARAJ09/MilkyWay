package in.SMW.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	Boolean existsByPhone(String phone);
}
