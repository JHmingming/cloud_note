package com.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Note>> loadManager(String title,String status,
			String begin,String end,String userId);
	public NoteResult<List<Map<String,Object>>> loadNotes(String bookId);
	public NoteResult<Note> loadNote(String noteId);
	public NoteResult<Object> updateNote(String title,String body,String id);
	public NoteResult<Note> addNote(String title,String userId,String bookId);
	public NoteResult<Note> updateNoteStatusId(String noteId);
	public NoteResult<Note> updateBookId(String bookId,String noteId);
	public NoteResult<Note> updateStatusRecycle(String noteId);
	public NoteResult<Object> deleteComplete(String noteId);
	}
