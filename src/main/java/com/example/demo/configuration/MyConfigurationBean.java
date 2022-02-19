package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.bean.MyBean;
import com.example.demo.bean.MyBeanImplement;
import com.example.demo.bean.MyBeanImplement2;
import com.example.demo.bean.MyBeanWithDependency;
import com.example.demo.bean.MyBeanWithDependencyImplement;
import com.example.demo.bean.MyOperation;
import com.example.demo.bean.MyOperationImplement;

@Configuration
public class MyConfigurationBean {
	@Bean
	public MyBean beanOperation() {
		return new MyBeanImplement2();
	}
	
	@Bean
	public MyOperation beanOperationOperation() {
		return new MyOperationImplement();
	}
	
	@Bean
	public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation) {
		return new MyBeanWithDependencyImplement(myOperation);
	}

}
