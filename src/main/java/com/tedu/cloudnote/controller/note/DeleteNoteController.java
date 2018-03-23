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
public class DeleteNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult<Note> execute(String noteId){
		System.out.println("delete");
		NoteResult<Note> result=noteService.updateNoteStatusId(noteId);
		return result;
	}

}
