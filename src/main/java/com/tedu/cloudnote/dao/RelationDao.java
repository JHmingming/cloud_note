package com.tedu.cloudnote.dao;

import java.awt.print.Book;
import java.util.List;

import com.tedu.cloudnote.entity.User;

public interface RelationDao {
	public User findUserAndBooks(String id);
	public User findUserAndBooks1(String id);
	public List<Book> findBookAndUser();
	public List<Book> findBookAndUser1();
}
