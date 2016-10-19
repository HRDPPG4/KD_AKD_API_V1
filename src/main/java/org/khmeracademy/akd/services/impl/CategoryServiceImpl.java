package org.khmeracademy.akd.services.impl;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.repositories.CategoryRepository;
import org.khmeracademy.akd.services.CategoryService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public boolean delete(String id) {
		try{
			return categoryRepository.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(Category cat) {
		
		return categoryRepository.insert(cat);
		
	}

	@Override
	public boolean update(Category cat) {
		return categoryRepository.update(cat);
	}

	@Override
	public ArrayList findAll() {
		return categoryRepository.findAll();
	}
	
	
	@Override
	public ArrayList getAllCategoryByLimit(Paging pagination) {
		pagination.setTotalCount(categoryRepository.count());
		return categoryRepository.getAllCategoryByLimit(pagination);
	}
	
	
	@Override
	public ArrayList getCategoryByParentID(String ParentID) {
		return categoryRepository.getCategoryByParentID(ParentID);
	}
	
	/*@Override
	public ArrayList getCategoryByParentIDAndStatus(String ParentID,int Status) {
		return categoryRepository.getCategoryByParentIDAndStatus(ParentID,Status);
	}*/
	
	@Override
	public ArrayList getCategoryByParentIDAndStatusEnable(String ParentID) {
		return categoryRepository.getCategoryByParentIDAndStatusEnable(ParentID);
	}
	
	

	@Override
	public Category findOne(String id) {
		return categoryRepository.findOne(id);
	}
	
	@Override
	public int getCategoryCount() {
		return categoryRepository.getCategoryCount();
	}

	
}
