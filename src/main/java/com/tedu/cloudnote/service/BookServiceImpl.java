package com.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("bookService")
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;
	public NoteResult<List<NoteBook>> loadUserBook(String id) {
		List<NoteBook> notebook=bookDao.findByUserId(id);
		System.out.println(notebook);
		NoteResult<List<NoteBook>> result=new NoteResult<List<NoteBook>>();
		result.setStatus(0);
		result.setData(notebook);
		result.setMsg("加载笔记本成功");
		return result;
	}
	public NoteResult<NoteBook> addBook(String userId,String bookname) {
		NoteResult<NoteBook> result=new NoteResult<NoteBook>();
		NoteBook notebook=new NoteBook();
		notebook.setCn_user_id(userId);
		Timestamp time=new Timestamp(System.currentTimeMillis());
		notebook.setCn_notebook_createtime(time);
		notebook.setCn_notebook_id(NoteUtil.createId());
		notebook.setCn_notebook_desc(null);
		notebook.setCn_notebook_name(bookname);
		notebook.setCn_notebook_type_id(null);
		int k=bookDao.addBook(notebook);
		if(k==0){
			
			result.setStatus(1);
			result.setMsg("失败");
			return result;
		}else{
			result.setData(notebook);
			result.setStatus(0);
			result.setMsg("成功");
			return result;
		}
	}

}
