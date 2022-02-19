package com.example.demo.repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	@Query("Select u from User u where u.email=?1 ")
	Optional<User> findByUserEmail(String email);
	
	@Query("Select u from User u where u.name like ?1%")
	List<User> findAndSort(String name, Sort sort);
	
	List<User> findByName(String name);
	
	Optional<User> findByEmailAndName(String email, String name);
	
	//Ejemplo de query methods más específicos
	
	List<User> findByNameLike(String name);
	
	List<User> findByNameOrEmail(String name, String email);
	
	List<User> findByBirthDateBetween(LocalDate begin , LocalDate end);
	
	List<User> findByNameLikeOrderByIdDesc(String name);
	
	List<User> findByNameContainingOrderByIdDesc(String name);
	
	List<User> findByNameNotLike(String name);
	
	
	//uso de jpql con named parameters
	@Query("Select new com.example.demo.dto.UserDto(u.id, u.name, u.birthDate)" +
	" FROM User u " +
	" WHERE u.birthDate =:parametroFecha" +
	" AND u.email =:parametroEmail")
	Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date, 
												@Param("parametroEmail") String email);
	
	List<User> findAll();
}
