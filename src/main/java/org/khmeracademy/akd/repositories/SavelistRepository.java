package org.khmeracademy.akd.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.entities.Savelist;
import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;

@Repository
public interface SavelistRepository {
	
	@Update(SAVE_LIST_SQL.UPDATE_DISABLE)
	boolean updateToDisable(int id);

	@Update(SAVE_LIST_SQL.UPDATE)
	boolean update(Savelist list);
	
	//@Insert(SAVE_LIST_SQL.INSERT)
	@Insert("INSERT INTO akd_save_lists VALUES(nextval('akd_save_lists_save_list_id_seq'),#{name},#{createdDate},#{remark},#{userID},#{status})")
	@SelectKey(before=false, keyProperty="savelistID", resultType=int.class,statement="SELECT last_value FROM akd_save_lists_save_list_id_seq")
	boolean insert(Savelist list);
	
	@Insert("INSERT INTO akd_save_list_detail VALUES (#{savelistID},#{docID},#{createdDate})")
	boolean insertDetails(Savelist list);
	
	@Insert("INSERT INTO akd_save_lists VALUES(nextval('akd_save_lists_save_list_id_seq'),#{name},#{createdDate},#{remark},#{userID},#{status})")
	boolean insertSavelistOnly(Savelist list);
	
	@Delete("DELETE FROM akd_save_list_detail WHERE doc_id = #{docID}")
	boolean deleteSaveDetail(String docID);
	
	@Select(SAVE_LIST_SQL.SELECT)
	@Results({
		@Result(property="savelistID", column="save_list_id"),
		@Result(property="name", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="status", column="status"),	
		@Result(property="users", column="user_id", one = @One(select = "getUser"))
		
	})
	ArrayList<Savelist> findAll(@Param("pagination") Paging pagination);

	@Select("SELECT * FROM akd_save_lists WHERE user_id = #{userID}")
	
	@Results({
		@Result(property="savelistID", column="save_list_id"),
		@Result(property="name", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="savelistdetail", column="save_list_id", many= @Many(select = "getSavelistDetail")),		
	})
	ArrayList<Savelist> findSavelistByUserID(int userID );
	
	
	@Select("SELECT * FROM akd_save_lists WHERE user_id = #{userID} AND save_list_id = #{savelistID}")
	
	@Results({
		@Result(property="savelistID", column="save_list_id"),
		@Result(property="name", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="savelistdetail", column="save_list_id", many= @Many(select = "getSavelistDetail")),		
	})
	ArrayList<Savelist> findEachSavelistByUserID(@Param("userID")int userID, @Param("savelistID")int savelistID );
	
	 
	
	@Select("SELECT * FROM akd_save_lists WHERE user_id = #{userID}")
	
	@Results({
		@Result(property="savelistID", column="save_list_id"),
		@Result(property="name", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),
		@Result(property="totalDocument", column="save_list_id", one = @One(select = "getTotalDocumentBySavelistID")),	// UPDATE BY CHIVORN
		@Result(property="users", column="user_id", one = @One(select = "getUser"))
				
	})
	ArrayList<Savelist> findSavelistMenuByUserID(int userID );
	
	
    
	@Select("SELECT * FROM akd_save_list_detail where save_list_id = #{savelistID}")
	
	@Results({
		@Result(property="savelistID", column="save_list_id"),
		@Result(property="name", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),		
		@Result(property="document", column="doc_id", one = @One(select = "getDocument"))
	})
	ArrayList<Savelist> getSavelistDetail();		
	
	@Select("SELECT COUNT(save_list_id) from akd_save_lists")
	public Long count();
	
	  
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
	
	@Select("SELECT * from akd_documents WHERE doc_id =#{docID} ")
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
		
	})
	ArrayList<Document> getDocument();
	
	
	@Select(SAVE_LIST_SQL.FIND_ONE)
	@Results({
		@Result(property="savelistID", column="save_list_id"),
		@Result(property="name", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
	
		@Result(property="status", column="status")	
	})
	Savelist findOne(int id);
	// UPDATE BY CHIVORN
	@Select("SELECT COUNT(*) FROM akd_save_list_detail WHERE save_list_id=#{save_list_id}")
	int getTotalDocumentBySavelistID();
	
	
	@Update("DELETE FROM akd_save_lists WHERE save_list_id=#{listID}")
	boolean deleteSavelist(int listID);
}

interface SAVE_LIST_SQL{
	String SELECT="SELECT * from akd_save_lists ORDER BY save_list_id ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}";
	
	String FIND_ONE="SELECT * from akd_save_lists WHERE save_list_id=#{savelistID}";
	 
	String UPDATE_DISABLE="UPDATE akd_save_lists SET status= 0 WHERE save_list_id=#{savelistID}";
	
	String UPDATE="UPDATE akd_save_lists SET "
			+ "name=#{name},"
			+ "created_date=#{createdDate},"
			+ "remark=#{remark},"
			+ "user_id=#{userID},"
			+ "status=#{status}"
			+ "WHERE save_list_id=#{savelistID}";
	
}





