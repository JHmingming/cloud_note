package com.tedu.cloudnote.service;

import java.util.List;

import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.util.NoteResult;

public interface BookService {
	public NoteResult<List<NoteBook>> loadUserBook(String id);
	public NoteResult<NoteBook> addBook(String userId,String bookname);
}
