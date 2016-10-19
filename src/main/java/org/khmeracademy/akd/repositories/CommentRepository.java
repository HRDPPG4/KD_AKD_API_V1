package org.khmeracademy.akd.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Comment;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {
	
	@Delete("UPDATE akd_comments SET status= 0 WHERE comment_id =#{commentID}")
	boolean delete(int id);
	
	@Update("UPDATE akd_comments SET created_date=#{createdDate},remark=#{remark},user_id=#{userID},doc_id=#{docID},status=#{status} WHERE comment_id =#{commentID}")
	boolean update(Comment com);
	
	@Insert("INSERT INTO akd_comments(created_date,remark,user_id,doc_id,status)VALUES(#{createdDate},#{remark},#{userID},#{docID},#{status})")
	boolean insert(Comment com);
	
	@Select("SELECT COUNT(comment_id) from akd_comments")
	public Long count();
	
	@Select("SELECT * from akd_comments ORDER BY comment_id ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}")
	@Results({
		@Result(property="commentID", column="comment_id"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="documents", column="doc_id", one = @One(select = "getDocument"))
	})
	ArrayList<Comment> findAll(@Param("pagination") Paging pagination);
	
	@Select("SELECT * from akd_comments WHERE comment_id=#{commentID}")
	@Results({
		@Result(property="commentID", column="comment_id"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status")
	})
	Comment findOne(int id);
	
	@Select("SELECT * from akd_comments WHERE doc_id=#{docID} ORDER BY comment_id DESC")
	@Results({
		@Result(property="commentID", column="comment_id"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser"))
			
	})
	ArrayList<Comment> getAllCommentByDocID(String DocID);
	
	@Select("SELECT * FROM akd_users WHERE user_id=#{userID}")
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name")	
	})
	ArrayList<User> getUser();
	
	@Select("SELECT * FROM akd_documents WHERE doc_id=#{docID}")
	@Results({
		@Result(property="docID", column="doc_id"),
		@Result(property="title", column="title")	
	})
	ArrayList<Document> getDocument();
	
}
