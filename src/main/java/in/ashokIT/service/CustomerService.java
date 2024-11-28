package in.ashokIT.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.ashokIT.entity.Customer;
import in.ashokIT.repo.CustomerRepo;

@Service
public class CustomerService implements UserDetailsService {

	
	
	@Autowired
	private CustomerRepo repo;
	
	//load the user details in database
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Customer c = repo.findByEmail(email);
		return new User(c.getEmail(), c.getPwd(), Collections.emptyList());
	}

	
	
}
