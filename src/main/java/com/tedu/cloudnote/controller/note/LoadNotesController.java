package com.tedu.cloudnote.controller.note;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Map<String,Object>>> execute(String bookId){
		NoteResult<List<Map<String,Object>>> result=noteService.loadNotes(bookId);
		return result;
	}
}
