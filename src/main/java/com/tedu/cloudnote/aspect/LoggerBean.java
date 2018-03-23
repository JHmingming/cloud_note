package com.tedu.cloudnote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component//��������Ա�ɨ�赽spring�����У��൱��bean
@Aspect//�ȼ���<aop:aspect>����
public class LoggerBean {
	@Before("within(com.tedu.cloudnote.controller..*)")
	public void logController(){
		System.out.println("����controller");
	}
}
