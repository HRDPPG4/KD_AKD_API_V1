package org.khmeracademy.akd.services;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.utilities.Paging;
public interface CategoryService {	
	boolean delete(String id);
	
	boolean insert(Category cat);
	
	boolean update(Category cat);
	
	Category findOne(String id);
	
	ArrayList<Object> findAll();
	
	ArrayList<Object> getAllCategoryByLimit(Paging pagination);
	
	ArrayList<Object> getCategoryByParentID(String ParentID);
	
	/*ArrayList<Category> getCategoryByParentIDAndStatus(String ParentID,int Status);*/
	
	ArrayList<Category> getCategoryByParentIDAndStatusEnable(String ParentID);
	
	int getCategoryCount();
	
}
