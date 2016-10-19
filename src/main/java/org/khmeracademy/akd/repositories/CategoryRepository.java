package org.khmeracademy.akd.repositories;

import java.util.ArrayList;







import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
	
	@Delete("UPDATE akd_categories SET status = 0 WHERE cat_id=#{id}")
	boolean delete(String id);
	
	@Update("UPDATE akd_categories SET name=#{catName},created_date=#{createdDate},remark=#{remark},parent_id=#{parentID},status=#{status},icon=#{icon} WHERE cat_id=#{catID}")
	boolean update(Category cat);
	
	
	@Insert("INSERT INTO akd_categories (cat_id,name,created_date,remark ,parent_id,status,icon,rang_order,level) VALUES(#{catID},#{catName},#{createdDate},#{remark},#{parentID},#{status},#{icon},#{order},#{level})")
	boolean insert(Category cat);
	
	@Select("SELECT COUNT(cat_id) from akd_categories")
	public Long count();
	
	@Select("SELECT * FROM akd_categories ORDER BY cat_id ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon"),
		@Result(property="order", column="rang_order"),
		/*@Result(property="totalDoc", column="total_doc"),*/
		@Result(property="totalDoc", column="cat_id", one = @One(select = "getTotalDocByCatID")),
		@Result(property="subCategories", column="cat_id"  
			, many = @Many(select = "getCategoryByParentIDAndStatusEnable")
		),
		@Result(property="parentName", column="parent_id", one = @One(select = "getParentName"))
		
	})
	ArrayList<Category>getAllCategoryByLimit(@Param("pagination") Paging pagination);
	
	
	@Select("SELECT * FROM akd_categories WHERE cat_id NOT IN ('0B4RhbtI4DXY_QWVOWkFiSTlRY1E') ORDER BY name ASC ")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon"),
		@Result(property="order", column="rang_order"),
		/*@Result(property="totalDoc", column="total_doc"),*/
		@Result(property="totalDoc", column="cat_id", one = @One(select = "getTotalDocByCatID")),
		@Result(property="level", column="level")
	})
	ArrayList<Category>findAll();
	
	@Select("SELECT * from akd_categories WHERE cat_id=#{catID}")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon"),
		@Result(property="order", column="rang_order"),
		/*@Result(property="totalDoc", column="total_doc")*/
		@Result(property="totalDoc", column="cat_id", one = @One(select = "getTotalDocByCatID")),
			
	})
	Category findOne(String id);
	
	@Select("SELECT * FROM akd_categories WHERE parent_id=#{parentID} AND status=1 ORDER BY rang_order ASC")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon"),
		@Result(property="order", column="rang_order"),
		/*@Result(property="totalDoc", column="total_doc"),*/
		@Result(property="totalDoc", column="cat_id", one = @One(select = "getTotalDocByCatID")),
		@Result(property="subCategories", column="cat_id"  
			, many = @Many(select = "getCategoryByParentIDAndStatusEnable")
		)
	})
	ArrayList<Category>getCategoryByParentID(String ParentID);
	
	/*@Select("SELECT * FROM akd_categories WHERE parent_id=#{parent_id} AND status=1 ")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon"),
		@Result(property="order", column="rang_order"),
		@Result(property="totalDoc", column="total_doc")
			
	})
	ArrayList<Category>getCategoryByParentIDAndStatus(@Param("parent_id")String ParentID, @Param("status")int Status);*/
	
	@Select("SELECT * FROM akd_categories WHERE parent_id=#{parentID} AND status=1")
	@Results({
		@Result(property="catID", column="cat_id"),
		@Result(property="catName", column="name"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="parentID", column="parent_id"),
		@Result(property="status", column="status"),
		@Result(property="icon", column="icon"),
		@Result(property="order", column="rang_order"),
		/*@Result(property="totalDoc", column="total_doc")*/
		@Result(property="totalDoc", column="cat_id", one = @One(select = "getTotalDocByCatID"))
			
	})
	ArrayList<Category>getCategoryByParentIDAndStatusEnable(String ParentID);
	
	@Select("SELECT COUNT(*) FROM akd_categories")
	@Results({
		@Result(property="catID", column="count"),
	})
	int getCategoryCount();
	
	@Select("SELECT name FROM akd_categories WHERE cat_id=#{catID}")
	@Results({
		@Result(property="catName", column="name")	
	})
	String getParentName(String catID);
	
	
	
	
	
	@Select("SELECT COUNT(*) FROM akd_documents WHERE cat_id=#{catID} AND status=1")	
	@Results({
		@Result(property="totalDoc", column="count"),
	})
	//	NOW WE GET BY STATUS ENDABLE
	int getTotalDocByCatID(String catID);
	
	
	
}
