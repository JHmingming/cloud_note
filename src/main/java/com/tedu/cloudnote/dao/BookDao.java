package com.tedu.cloudnote.dao;

import java.util.List;

import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.util.NoteResult;

public interface BookDao {
	public List<NoteBook> findByUserId(String id);
	public int addBook(NoteBook notebook);
}
