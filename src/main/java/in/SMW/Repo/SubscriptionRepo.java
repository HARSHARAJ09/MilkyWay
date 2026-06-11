package in.SMW.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Integer> {

	List<Subscription> findByUserId(Integer userId);
}