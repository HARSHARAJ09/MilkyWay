package in.SMW.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
	List<CartItem> findByCartId(Integer cartId);

	Optional<CartItem> findByCartIdAndProductId(
			Integer cartId,
			Integer productId
	);
}
