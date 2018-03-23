package com.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//e����Ŀ�귽���׳����쳣����
@Component//ɨ�赽Spring����
@Aspect//�����ָ��Ϊ����
public class ExceptionBean {
	@AfterThrowing(throwing="e",pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e) {
		//���쳣д���ļ���
		FileWriter fw;
		try {
			fw = new FileWriter("D:\\note_error.log",true);
			PrintWriter pw=new PrintWriter(fw);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(date);
			pw.println("�쳣����"+e);
			pw.println("�쳣�¼�"+time);
			e.printStackTrace(pw);//�޲ε���Ĭ��������̨��������˺��ǽ�ջ��Ϣ���������
			pw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("��¼��Ϣʧ��");
		}
		
	}
}
