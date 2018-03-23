package com.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//e就是目标方法抛出的异常对象
@Component//扫描到Spring容器
@Aspect//将组件指定为切面
public class ExceptionBean {
	@AfterThrowing(throwing="e",pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e) {
		//将异常写入文件中
		FileWriter fw;
		try {
			fw = new FileWriter("D:\\note_error.log",true);
			PrintWriter pw=new PrintWriter(fw);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(date);
			pw.println("异常类型"+e);
			pw.println("异常事件"+time);
			e.printStackTrace(pw);//无参的是默认往控制台输出，改了后是将栈信息往流中输出
			pw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("记录信息失败");
		}
		
	}
}
