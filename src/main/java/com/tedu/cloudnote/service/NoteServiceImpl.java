package com.tedu.cloudnote.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDao noteDao;
	public NoteResult<List<Map<String,Object>>> loadNotes(String bookId) {
		
		List<Map<String,Object>> list=noteDao.findByBookId(bookId);
		NoteResult<List<Map<String,Object>>> result= new NoteResult<List<Map<String,Object>>>();
		result.setData(list);
		result.setMsg("");
		result.setStatus(0);
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note=noteDao.findByNoteId(noteId);
		
		if(note==null){
			result.setMsg("");
			result.setStatus(1);
		}else{
			
		result.setData(note);
		result.setMsg("加载笔记成功");
		result.setStatus(0);
		}
		return result;
	}
	public NoteResult<Object> updateNote(String title,String body,String id) {
		Note note=new Note();
		note.setCn_note_body(body);
		note.setCn_note_id(id);
		note.setCn_note_title(title);
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		int row=noteDao.dynamicUpdate(note);
		NoteResult<Object> result=new NoteResult<Object>();
		if(row==1){
			result.setStatus(0);
			result.setMsg("更新成功");
		
		}else{
			result.setStatus(1);
			result.setMsg("更新失败");
		}
		
		return result;
	}
	public NoteResult<Note> addNote(String title, String userId, String bookId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note =new Note();
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");//״̬��1��normal 2��recycle����
		note.setCn_note_title(title);
		note.setCn_note_type_id("1");
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		int k=noteDao.addNote(note);
		if(k==0){
			result.setStatus(1);
			result.setMsg("增加失败");
			return result;
		}else{
			result.setData(note);
			result.setStatus(0);
			result.setMsg("增加成功");
			return result;
		}
		
		
	}
	public NoteResult<Note> updateNoteStatusId(String noteId) {
		Note note1=new Note();
		note1.setCn_note_id(noteId);
		note1.setCn_note_status_id("2");
		
		int k=noteDao.dynamicUpdate(note1);
		Note note=noteDao.findByNoteId(noteId);
		NoteResult<Note> result=new NoteResult<Note>();
		if(k==0){
			result.setMsg("删除失败");
			result.setStatus(1);
			return result;
		}else{
			result.setData(note);
			result.setStatus(0);
			result.setMsg("删除成功，你可以在回收站查看");
			return result;
		}
		
	}
	public NoteResult<Note> updateStatusRecycle(String noteId){
		Note note1=new Note();
		note1.setCn_note_id(noteId);
		note1.setCn_note_status_id("1");
		int k=noteDao.dynamicUpdate(note1);
		Note note=noteDao.findByNoteId(noteId);
		NoteResult<Note> result=new NoteResult<Note>();
		if(k==0){
			result.setMsg("恢复失败");
			result.setStatus(1);
			return result;
		}else{
			result.setData(note);
			result.setStatus(0);
			result.setMsg("恢复成功");
			return result;
		}
		
	}
	public NoteResult<Note> updateBookId(String bookId,String noteId) {
//		Map<String,Object> param=new HashMap<String,Object>();
//		param.put("bookId", bookId);
//		param.put("noteId", noteId);
	NoteResult<Note> result=new NoteResult<Note>();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		int k=noteDao.dynamicUpdate(note);
		if(k==0){
			result.setMsg("转移失败");
			result.setStatus(1);
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("转移成功");
			return result;
		}
		
	}
	public NoteResult<List<Note>> loadManager(String title, String status, 
			String begin, String end,String userId) {
		Map<String,Object> params=new HashMap<String, Object>();
		System.out.println("userId是"+userId);
		params.put("userId", userId);
		//必须得判断，否则有可能将""空字符串穿进去，就会出错，不传是null
		if(title!=null&&!"".equals(title)){
			params.put("title", "%"+title+"%");
		}
		if(status!=null&&!"0".equals(status)){
			params.put("status", status);
		}
		if(begin!=null&&!"".equals(begin)){
			Date beginDate=Date.valueOf(begin);
			params.put("begin", beginDate.getTime());
		}
		if(end!=null&&!"".equals(end)){
			Date endDate=Date.valueOf(end);
			params.put("end", endDate.getTime());
		}
		List<Note> list=noteDao.findNotes(params);
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		result.setData(list);
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		
		return result;
	}
	public NoteResult<Object> deleteComplete(String noteId) {
		int k=noteDao.deleteComplete(noteId);
		NoteResult<Object> result=new NoteResult<Object>();
		if(k==1){
			result.setMsg("删除成功");
			result.setStatus(0);
		}
		
		return result;
	}


}
