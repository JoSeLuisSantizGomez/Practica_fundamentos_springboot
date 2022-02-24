package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.UserPass;
import com.example.demo.repository.UserPassRepository;

@SpringBootTest
class ApplicationTests {
	
	private BCryptPasswordEncoder bcryptpass;
	
	public ApplicationTests(BCryptPasswordEncoder bcryptpass) {
		this.bcryptpass = bcryptpass;
	}

	@Autowired
	private UserPassRepository userPassRepository;

	@Test
	void crearUsuario() {
		UserPass userPass = new UserPass();
		userPass.setId((long) 3);
		userPass.setNombre("juan");
		userPass.setClave(bcryptpass.encode("perez"));
		UserPass retorno = userPassRepository.save(userPass);
		
		assertTrue(retorno.getClave().equalsIgnoreCase(userPass.getClave()) );
	}

}
