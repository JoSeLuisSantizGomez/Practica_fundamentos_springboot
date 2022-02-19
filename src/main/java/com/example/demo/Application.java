package com.example.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.example.demo.bean.MyBean;
import com.example.demo.bean.MyBeanWithDependency;
import com.example.demo.bean.MyBeanWithProperties;
import com.example.demo.component.ComponentDependency;
import com.example.demo.entity.User;
import com.example.demo.pojo.UserPojo;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private final Log LOGGER = LogFactory.getLog(Application.class);
	
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	
	public Application(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
	
	private void saveWithErrorTransactional() {
		User test1 = new User ("test1", "test1@gmail.com", LocalDate.now());
		User test2 = new User ("test2", "test2@gmail.com", LocalDate.now());
		User test3 = new User ("test3", "test1@gmail.com", LocalDate.now());
		User test4 = new User ("test4", "test4@gmail.com", LocalDate.now());
		
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransactional(users);
		}
		catch (Exception e) {
			LOGGER.error("Esto es una excepcion del metodo transaccional " + e);
		}
		
		userService.getAllUsers()
			.stream()
			.forEach(user -> LOGGER.info("Este usuario dentro del metodo transaccional " + user));
	}
	
	private void getInformationJpqlFromUser () {
		
		/*LOGGER.info("El usuario que se encontró con el método findByUserEmail " + 
		userRepository.findByUserEmail("jose@gmail.com")
		.orElseThrow(()->new RuntimeException("No se encontro el registro"))
		);
		
		userRepository.findAndSort("user", Sort.by("id").ascending())
		.stream().
		forEach(user->LOGGER.info("El usario con el metodo sort" + user));
		
		userRepository.findByName("jose")
		.stream()
		.forEach(user-> LOGGER.info("Usuario con query method" + user));
		
		LOGGER.info("Usuario con findByEmailAndName" + userRepository.findByEmailAndName("user4@gmail.com", "user4")
		.orElseThrow( ()-> new RuntimeException("Usuario no encontrado"))); */
		
		//Ejemplo de query methods más específicos
		
		 /*userRepository.findByNameLike("%j%")
		.stream()
		.forEach(user -> LOGGER.info("Usuario con Like " + user));
		
		userRepository.findByNameOrEmail("user7", null)
		.stream()
		.forEach(user -> LOGGER.info("Usuario con findByNameOrEmail " + user)); */
		
		userRepository.findByBirthDateBetween(LocalDate.of(1999, 1, 5), LocalDate.of(1999, 3, 8))
		.stream()
		.forEach(user -> LOGGER.info("Usuario con intervalo " + user));
		
		userRepository.findByNameContainingOrderByIdDesc("user")
		.stream()
		.forEach(user -> LOGGER.info("Usuario encontrado y ordenado " + user));
		
		userRepository.findByNameNotLike("%user%")
		.stream()
		.forEach(user -> LOGGER.info("Usuario con no like" + user) );
		
		LOGGER.info("El usuario encontrado en el named es" + userRepository
				.getAllByBirthDateAndEmail( LocalDate.of(1997, 04, 06) , "user3@gmail.com")
				.orElseThrow(()->new RuntimeException ("No se encontró el usuario en el named")));
	}
	
	private void saveUsersInDataBase() {
		User user1 = new User("jose","jose@gmail.com",LocalDate.of(1999, 01, 06) );
		User user2 = new User("jose","user2@gmail.com",LocalDate.of(1998, 03, 06) );
		User user3 = new User("jose","user3@gmail.com",LocalDate.of(1997, 04, 06) );
		User user4 = new User("user4","user4@gmail.com",LocalDate.of(1996, 05, 06) );
		User user5 = new User("user5","user5e@gmail.com",LocalDate.of(1999, 01, 12) );
		User user6 = new User("user6","juser6@gmail.com",LocalDate.of(1994, 07, 06) );
		User user7 = new User("user7","user7@gmail.com",LocalDate.of(1993, 01, 02) );
		User user8 = new User("user8","user8e@gmail.com",LocalDate.of(1992, 01, 01) );
		User user9 = new User("user9","juser9@gmail.com",LocalDate.of(1991, 02, 06) );
		User user10 = new User("user10","user10@gmail.com",LocalDate.of(1990, 01, 26) );
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		list.stream().forEach(userRepository::save);
	}
	
	private void ejemplosAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println( myBeanWithProperties.function() );
		System.out.println(userPojo.getEmail() + "  " + userPojo.getPassword() + " edad " + userPojo.getAge());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor es " + value);
		}catch(Exception e) {		
			LOGGER.error("Es un error de operacion " + e.getMessage());
		}
	}
}
