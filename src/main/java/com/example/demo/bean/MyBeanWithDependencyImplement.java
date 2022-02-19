package com.example.demo.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {
	
	Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
	
	private MyOperation myoperation;
	
	public MyBeanWithDependencyImplement(MyOperation myoperation) {
		this.myoperation = myoperation;
	}

	@Override
	public void printWithDependency() {
		LOGGER.info("Hemos ingresado al método printWithDependency ");
		int numero =1;
		LOGGER.debug("El numero enviado es " + numero);
		System.out.println(myoperation.sum(numero));
		System.out.println("Hola desde mi bean con dependencia con la implementacion");
	}

}
