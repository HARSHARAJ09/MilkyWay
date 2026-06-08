package in.SMW.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
	List<OrderItem> findByOrdersId(Integer orderId);
}
