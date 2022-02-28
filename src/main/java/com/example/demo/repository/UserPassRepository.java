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
import com.example.demo.entity.UserPass;

@Repository
public interface UserPassRepository extends JpaRepository<UserPass, Long> {
	@Query("Select up from UserPass up WHERE up.nombre = ?1")
	Optional<UserPass> findByUserPassNombre(String nombre);
	
	@Query("Select up from UserPass up WHERE up.email = ?1")
	public UserPass findByUserPassEmail(String email);
	
	UserPass findByNombre(String nombre);
	
	public UserPass findByResetPasswordToken(String token);

}
