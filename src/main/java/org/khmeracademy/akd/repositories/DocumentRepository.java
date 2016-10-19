package org.khmeracademy.akd.repositories;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.entities.Comment;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.entities.Log;
import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;

@Repository
public interface DocumentRepository {
	
//	@Delete("DELETE FROM akd_documents WHERE doc_id=#{docID}")
	@Delete("UPDATE akd_documents SET status= 0 WHERE doc_id=#{docID}")
	boolean delete(String id);
	
	@Update("UPDATE akd_documents SET title=#{title},des=#{des},embed_link=#{embedLink},thumbnail_url=#{thumbnailURL},export_link=#{exportLink},view=#{view},share=#{share},created_date=#{createdDate},doc_type_num=#{docTypeNum},user_id=#{userID},cat_id=#{catID},status=#{status} WHERE doc_id=#{docID}")
	boolean update(Document doc);
	
	//@SelectKey(before= false, keyProperty="view",resultType= int.class,statement="SELECT view From akd_documents WHERE doc_id = #{docID}")
	@Update("UPDATE akd_documents SET view =(SELECT view FROM akd_documents WHERE doc_id =#{docID})+1 WHERE doc_id= #{docID}")
   	boolean countView(String docID);
	
	@Insert("INSERT INTO akd_documents VALUES(#{docID},#{title},#{des},#{embedLink},#{thumbnailURL},#{exportLink},#{view},#{share},#{docTypeNum},#{userID},#{catID},#{status},#{createdDate})")
	boolean insert(Document doc);
	
	@Select("SELECT COUNT(doc_id) from	akd_documents")
	public Long count();
	
	
	/*@Select("SELECT * from akd_documents")
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
	ArrayList<Document> findAll();*/
	
	@Select("SELECT * from akd_documents WHERE cat_id=#{catID} AND status=1")
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
	})
	ArrayList<Document> getDocumentByCatID(String CatID);
	
	
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
		@Result(property="status", column="status"),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
		
	})
	Document findOne(String id);
	
	@Select("SELECT * from akd_documents ORDER BY title ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}")
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="category", column="cat_id", one = @One(select = "getCategory")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
	})
	ArrayList<Document> getDocumentAndUserAndCategory(@Param("pagination") Paging pagination);
	
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="category", column="cat_id", one = @One(select = "getCategory")),
		@Result(property="comment", column="doc_id", many = @Many(select = "getComments")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
	})
	ArrayList<Document> getDocumentAndUserAndCategoryAndComment(String DocID);
	
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
	ArrayList<User> getUser();
	
	@Select("SELECT * FROM akd_categories WHERE cat_id=#{catID}")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon")
	})
	ArrayList<Category>getCategory();
	
	@Select("SELECT * from akd_comments WHERE doc_id=#{docID}")
	@Results({
		@Result(property="commentID", column="comment_id"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status")
			
	})
	ArrayList<Comment>getComments();
	
	
	@Select("SELECT * FROM akd_documents WHERE user_id= #{userID} AND doc_type_num= #{docTypeNum} AND status=1")
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
		@Result(property="status", column="status"),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
	})
	// WE GET BY STATUS ENABLE ONLY
	ArrayList<Document>getDocByUser(@Param("userID")int userID, @Param("docTypeNum")int docTypeNum);
	
	
	
	@Select("SELECT * FROM akd_documents ORDER BY view DESC LIMIT #{pagination.limit} OFFSET #{pagination.offset}")
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
	})
	ArrayList<Document> getDocumentByPopular(@Param("pagination") Paging pagination);
	
	
	
	@Select("SELECT DISTINCT doc.doc_id,doc.title,doc.des,doc.embed_link,doc.thumbnail_url,doc.view,doc.share,doc.user_id FROM akd_documents doc INNER JOIN akd_logs lg ON doc.doc_id=lg.doc_id WHERE doc.cat_id IN(SELECT dd.cat_id FROM akd_documents dd INNER JOIN akd_logs ll ON dd.doc_id=ll.doc_id WHERE ll.user_id=#{userID} ORDER BY ll.date DESC)")
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
		
	})
	ArrayList<Document> getDocumentByRecommended(int userID);
	
	@Select("SELECT * FROM akd_documents ORDER BY to_timestamp(created_date, 'dd-MM-yyyy HH24:MI:SS') DESC")
	
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
		
	})
	ArrayList<Document> getDocumentByNewPost();
	
	
	@Select("SELECT COUNT(*) FROM akd_documents")
	
	@Results({
		@Result(property="docID", column="count"),
	})
	int getDocumentCount();
	
@Select("SELECT * FROM akd_documents WHERE title LIKE '%' || #{title} || '%' AND status=1")
	
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
		
	})
	ArrayList<Document> getDocumentByLikeTitle(String title);
	
	
	@Update("UPDATE akd_documents SET thumbnail_url=#{thumbnailURL} WHERE doc_id=#{docID}")
	boolean uploadDocThumbnail(@Param("thumbnailURL") String thumbnailURL,@Param("docID") String docID);
	
	/*@Select("SELECT COUNT(*) FROM akd_documents WHERE cat_id=#{catID}")
	
	@Results({
		@Result(property="catID", column="count"),
	})
	int getDocumentCountByCatID(String catID);*/
	
	@Update("UPDATE akd_categories SET total_doc=(SELECT count(*) FROM akd_documents WHERE cat_id=#{catID}) WHERE cat_id=#{catID}")
	boolean updateTotalDocByCatID(String catID);
	
	
	@Select("SELECT COUNT(*) FROM akd_documents WHERE user_id=#{userID} AND status=1")	
	@Results({
		@Result(property="docID", column="count"),
	})
	//	NOW WE GET BY STATUS ENDABLE
	int countTotalDocByUserID(int userID);
	
	@Update("UPDATE akd_documents SET share = (SELECT share FROM akd_documents WHERE doc_id=#{docID}) + 1 WHERE doc_id=#{docID}")
	boolean updateShareAmount(String docID);
	
	//	USE TO UPDATE ALL STATUS NUMBER SO WE CAN DELETE OLD FUNCTION
	@Update("UPDATE akd_documents SET status=#{status} WHERE doc_id=#{docID}")
	boolean updateDocumentStatus(@Param("docID")String docID,@Param("status")int status);
	
	@Select("SELECT name FROM akd_categories WHERE cat_id=#{catID}")
	@Results({
		@Result(property="catName", column="name")	
	})
	String getCategoryNameByCatID(String catID);
	
	@Select("SELECT * from akd_documents WHERE status=#{status} ORDER BY title ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}")
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
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="category", column="cat_id", one = @One(select = "getCategory")),
		@Result(property="catName", column="cat_id", one = @One(select = "getCategoryNameByCatID"))
	})
	ArrayList<Document> getAllDocumentByStatus(@Param("status") int status,@Param("pagination") Paging pagination);
	
	@Select("SELECT COUNT(doc_id) from	akd_documents WHERE status=#{status}")
	public Long countTotalDocByStatus(@Param("status") int status);
		
}

