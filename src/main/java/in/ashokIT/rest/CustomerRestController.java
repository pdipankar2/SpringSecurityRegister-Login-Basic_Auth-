package in.ashokIT.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokIT.entity.Customer;
import in.ashokIT.repo.CustomerRepo;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private PasswordEncoder pwEncoder;

	// verify the login details is valid or not

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {

		String encode = pwEncoder.encode(customer.getPwd());
		customer.setPwd(encode);

		customerRepo.save(customer);

		return new ResponseEntity<String>("Customern Register", HttpStatus.CREATED);

	}

	@GetMapping("/login")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer customer) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer.getEmail(),
				customer.getPwd());


		try {
			Authentication authenticate = authenticationManager.authenticate(token);

		if (authenticate.isAuthenticated()) {
			return new ResponseEntity<String>("Login  Successfull", HttpStatus.OK);
		}
		}catch (Exception e) {
             e.printStackTrace();

		}

			return new ResponseEntity<String>("Invalid Credintial", HttpStatus.BAD_REQUEST);
		
	}
}
