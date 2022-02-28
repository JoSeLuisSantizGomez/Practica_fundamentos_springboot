package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "userPass")
public class UserPass {
	
	@Id
	private Long id;

	@Column(length = 50)
	private String nombre;
	
	@Column(length = 150, unique= true)
	private String clave;
	
	@Column(length = 150)
	private String email;
	
	@Column(length = 45)
    private String resetPasswordToken;
	
	public UserPass() {
	}

	public UserPass(Long id, String nombre, String clave, String email, String resetPasswordToken ) {
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.email = email;
		this.resetPasswordToken = resetPasswordToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	

	@Override
	public String toString() {
		return "UserPass [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", email=" + email
				+ ", resetPasswordToken=" + resetPasswordToken + "]";
	}
	
}
