package com.example.demo.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {
	
	Log LOGGER = LogFactory.getLog(MyBeanWithPropertiesImplement.class);
	
	private String nombre;
	private String apellido;
	
	

	public MyBeanWithPropertiesImplement(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}



	@Override
	public String function() {
		LOGGER.debug("El nombre es " + nombre);
		return nombre + "  " + apellido;
	}

}
