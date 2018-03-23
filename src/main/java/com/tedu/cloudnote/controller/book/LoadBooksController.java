package com.tedu.cloudnote.controller.book;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.service.BookService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/book")
public class LoadBooksController {
	@Resource
	private BookService bookService;
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult<List<NoteBook>> execute(String userId){
		
		NoteResult<List<NoteBook>> result=bookService.loadUserBook(userId);
		System.out.println("result"+result.getMsg());
		return result;
		
	}
}
