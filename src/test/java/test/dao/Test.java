package test.dao;

import org.junit.Before;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.dao.EmpDao;
import com.tedu.cloudnote.dao.RelationDao;
import com.tedu.cloudnote.entity.Emp;
import com.tedu.cloudnote.service.UserService;

public class Test {
	private EmpDao empDao;
	private RelationDao relationdao;
	@Before
	public void init(){
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		relationdao=ac.getBean("relationDao",RelationDao.class);
		empDao=ac.getBean("empDao",EmpDao.class);
	}
	@org.junit.Test//用例-1，预期：
	public void test1(){
		System.out.println(relationdao.findUserAndBooks("ea09d9b1-ede7-4bd8-b43d-a546680df00b"));
		
	}
	@org.junit.Test//用例-2，预期：
	public void test2(){
		System.out.println(relationdao.findUserAndBooks1("ea09d9b1-ede7-4bd8-b43d-a546680df00b").getCn_user_name());
		
	}
	@org.junit.Test//用例-3，预期：
	public void test3(){
		System.out.println(relationdao.findBookAndUser());
		
	}
	@org.junit.Test//用例-3，预期：
	public void test4(){
		Emp emp=new Emp();
		emp.setAge(20);
		emp.setName("sdasf");
		empDao.save(emp);
		//获取emp记录刚插入的id值
		System.out.println(emp.getId());
		
	}
}
