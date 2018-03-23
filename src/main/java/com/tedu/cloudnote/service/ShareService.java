package com.tedu.cloudnote.service;

import java.util.List;

import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;

public interface ShareService {
	public NoteResult<Object> save(String noteId);
	public NoteResult<List<Share>> search(String noteTitle,int begin);
	public NoteResult<Share> findShareById(String shareId);
}
