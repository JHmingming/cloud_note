package com.tedu.cloudnote.controller.note;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class FindRecycleController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/recycle.do")
	@ResponseBody
	public NoteResult<List<Note>> execute(String userId){
		NoteResult<List<Note>> result=noteService.loadManager(null, "2", null, null, userId);
		
		return result;
	}

}
