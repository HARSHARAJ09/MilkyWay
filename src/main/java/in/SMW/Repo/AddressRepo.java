package in.SMW.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.SMW.Entity.Address;

public interface AddressRepo extends JpaRepository<Address,Integer> {

	List<Address> findByUserId(Integer userId);
}