package com.tedu.cloudnote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component//组件，可以被扫描到spring容器中，相当于bean
@Aspect//等价于<aop:aspect>定义
public class LoggerBean {
	@Before("within(com.tedu.cloudnote.controller..*)")
	public void logController(){
		System.out.println("进入controller");
	}
}
