package in.SMW.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByUserId(Integer userId);
}
