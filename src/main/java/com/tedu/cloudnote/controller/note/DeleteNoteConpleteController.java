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
public class DeleteNoteConpleteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/deletecomplete.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result=new NoteResult<Object>();
		result=noteService.deleteComplete(noteId);
		return result;
	}
}
