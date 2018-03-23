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
public class NoteReplayOtherController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/replay2.do")
	@ResponseBody
	public NoteResult<Note> execute(String noteId,String bookId){
		NoteResult<Note> result=noteService.updateBookId(bookId, noteId);
		noteService.updateStatusRecycle(noteId);
		return result;
	}

}
