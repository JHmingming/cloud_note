package com.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class UpdateController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult<Object> update(String title,String body,String id){
		
		NoteResult<Object> result=noteService.updateNote(title,body,id);
		return result;
	}
}
