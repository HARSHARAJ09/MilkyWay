package in.SMW.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer> {
	List<Orders> findByUserId(Integer userId);

	List<Orders> findByOrderStatus(String orderStatus);
}
