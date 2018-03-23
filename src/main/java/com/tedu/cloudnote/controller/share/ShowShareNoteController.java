package com.tedu.cloudnote.controller.share;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.service.ShareService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShowShareNoteController {
	@Resource
	private ShareService shareService;
@RequestMapping("/show.do")
@ResponseBody
public NoteResult<Share> execute(String shareId){
	
	NoteResult<Share> result=shareService.findShareById(shareId);
	return result;
}
}
