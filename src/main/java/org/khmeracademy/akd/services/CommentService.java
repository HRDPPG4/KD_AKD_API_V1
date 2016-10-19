package org.khmeracademy.akd.services;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Comment;
import org.khmeracademy.akd.utilities.Paging;
public interface CommentService {	
	boolean delete(int id);
	
	boolean insert(Comment com);
	
	boolean update(Comment com);
	
	ArrayList<Object> findAll(Paging pagination);
	
	Comment findOne(int id);
	
	ArrayList<Comment> getAllCommentByDocID(String DocID);
	
}
