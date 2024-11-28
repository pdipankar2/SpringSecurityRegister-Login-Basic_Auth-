package in.ashokIT.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokIT.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

	public Customer findByEmail(String email);
	
}
