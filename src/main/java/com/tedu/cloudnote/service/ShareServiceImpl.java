package com.tedu.cloudnote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("shareService")
public class ShareServiceImpl implements ShareService {
	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	
	@Transactional//该标记方法会采用DataSourceTransactionManager控制事务
	public NoteResult<Object> save(String noteId) {
		String noteBody;
		String noteTitle;
		Note note=noteDao.findByNoteId(noteId);
		noteBody=note.getCn_note_body();
		noteTitle=note.getCn_note_title();
		
		note.setCn_note_type_id("2");
		
		NoteResult<Object> result=new NoteResult<Object>();
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_body(noteBody);
		share.setCn_share_title(noteTitle);
		share.setCn_share_id(NoteUtil.createId());
		//String s=null;s.length();
		int k=shareDao.save(share);
		//TODE 修改cn_note表的type_id值
		noteDao.dynamicUpdate(note);
		if(k==1){
			result.setMsg("分享成功");
			result.setStatus(0);
		}
		return result;
	}


	public NoteResult<List<Share>> search(String noteTitle,int begin) {
		String title="%";//等于null或者为空是查所有
		if(noteTitle!=null && !"".equals(noteTitle)){
			title="%"+noteTitle.trim()+"%";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("noteTitle", title);
		map.put("begin", begin);
		List<Share> list=shareDao.findLikeTitle(map);
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setData(list);
		System.out.println("list"+"+"+list);
		return result;
	}


	public NoteResult<Share> findShareById(String shareId) {
		Share share=shareDao.findShareNoteById(shareId);
		System.out.println(share);
		NoteResult<Share> result=new NoteResult<Share>();
		result.setData(share);
		result.setMsg("查看成功");
		result.setStatus(0);
		return result;
	}
	
}
