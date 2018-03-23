package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteDao {
	public int dynamicUpdate(Note note);
	public List<Note> findNotes(Map params);
	public List<Map<String,Object>> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	//public int updateNote(Note note);
	public int addNote(Note note);
    public int updateNoteStatus(String noteId);
	//public int updateBookId(Map param);//noteId  bookId
    public int deleteComplete(String noteId);
    
}
