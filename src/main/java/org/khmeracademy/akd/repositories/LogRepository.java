package org.khmeracademy.akd.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.entities.Log;
import org.khmeracademy.akd.entities.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;

@Repository
public interface LogRepository {
	
	@Delete("DELETE FROM akd_logs WHERE log_id=#{logID}")
	boolean delete(int id);
	@Update("UPDATE akd_logs SET date=#{date},remark=#{remark},user_id=#{userID},doc_id=#{docID},status=#{status} WHERE log_id=#{logID}")
	boolean update(Log feed);
	
	@Insert("INSERT INTO akd_logs(date,remark,user_id,doc_id,status) VALUES(#{date},#{remark},#{userID},#{docID},#{status})")
	boolean insert(Log feed);
	
	
	@Select("SELECT * from akd_logs ")
	@Results({
		@Result(property="logID", column="log_id"),
		@Result(property="date", column="date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="documents", column="doc_id", one = @One(select = "getDocument"))	
		
	})
	ArrayList<Log> findAll();
	/*@Select("SELECT l.log_id ,d.title,d.des,d.thumbnail_url FROM akd_logs l INNER JOIN akd_documents d ON l.doc_id = d.doc_id WHERE l.user_id =#{userID}")
	@Results({
		@Result(property="logID", column="log_id"),
		@Result(property="date", column="date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),
		@Result(property="user", column="user_id", one = @One(select = "getUser")),
		@Result(property="document", column="doc_id", one = @One(select = "getDocument"))	
		
	})*/
//      ArrayList<Log> findAllByUser(int userID);
	
	@Select("SELECT * from akd_logs WHERE log_id=#{logID}")
	@Results({
		@Result(property="logID", column="log_id"),
		@Result(property="date", column="date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status")
	})
	Log findOne(int id);
	
	@Select("SELECT * FROM akd_users WHERE user_id=#{userID}")
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name"),
		@Result(property="password", column="password"),
		@Result(property="email", column="email"),
		@Result(property="phone", column="phone"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),		
		@Result(property="role", column="role")		
	})
	  User getUser(int userID);
	
	
	@Select("SELECT * from akd_documents WHERE doc_id=#{docID}")
	@Results({
		@Result(property="docID", column="doc_id"),
		@Result(property="title", column="title"),
		@Result(property="des", column="des"),
		@Result(property="embedLink", column="embed_link"),
		@Result(property="thumbnailURL", column="thumbnail_url"),
		@Result(property="exportLink", column="export_link"),
		@Result(property="view", column="view"),
		@Result(property="share", column="share"),		
		@Result(property="createdDate", column="created_date"),
		@Result(property="docTypeNum", column="doc_type_num"),
		@Result(property="userID", column="user_id"),
		@Result(property="catID", column="cat_id"),
		@Result(property="status", column="status")		
	})
	 Document getDocument(String docID);
	
	@Select("SELEct * FROM akd_logs WHERE user_id=#{userID} AND status =1")
	@Results({
		@Result(property="logID", column="log_id"),
		@Result(property="date", column="date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),
		@Result(property="user", column="user_id", one = @One(select = "getUser")),
		@Result(property="document", column="doc_id", one = @One(select = "getDocument"))	
		
	})
	ArrayList<Log> findAllByUser(int userID);
	
	@Delete("DELETE FROM akd_logs WHERE user_id=#{userID}")
	boolean deleteAllLogByUserID(int userID);
	
	
}
