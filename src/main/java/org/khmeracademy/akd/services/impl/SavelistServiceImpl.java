package org.khmeracademy.akd.services.impl;

import java.util.ArrayList;

import org.apache.felix.bundlerepository.impl.SystemRepositoryImpl;
import org.khmeracademy.akd.entities.Savelist;
import org.khmeracademy.akd.repositories.SavelistRepository;
import org.khmeracademy.akd.services.SavelistService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SavelistServiceImpl implements SavelistService{
	
	@Autowired
	private SavelistRepository savelistRepository;
	
	
	@Override
	public boolean updateToDisable(int id) {
		try{
			return savelistRepository.updateToDisable(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	
	public boolean insert(Savelist list) {
		
		savelistRepository.insert(list);
		
		return savelistRepository.insertDetails(list);
	}

	@Override
	public boolean update(Savelist list) {
		return savelistRepository.update(list);
	}

	@Override
	public ArrayList findAll(Paging pagination) {
		pagination.setTotalCount(savelistRepository.count());
		return savelistRepository.findAll(pagination);
	}

	@Override
	public Savelist findOne(int id) {
		return savelistRepository.findOne(id);
	}

	@Override
	public ArrayList<Savelist> findSavelistByUserID(int userID) {
		return savelistRepository.findSavelistByUserID(userID);
	
	
	}

	@Override
	public boolean insertDetail(Savelist list) {
		return savelistRepository.insertDetails(list);
	}

	@Override
	public boolean insertSavelistOnly(Savelist list) {
		
		return savelistRepository.insertSavelistOnly( list );
	}

	@Override
	public ArrayList<Savelist> findSavelistMenuByUserID(int userID) {
		// TODO Auto-generated method stub
		return savelistRepository.findSavelistMenuByUserID(userID);
	}

	@Override
	public ArrayList<Savelist> findEachSavelistByUserID(int userID,
			int savelistID) {
		
		return savelistRepository.findEachSavelistByUserID(userID,savelistID);
	}

	@Override
	public boolean deleteSavelistDetail(String docID) {
		// TODO Auto-generated method stub
		return savelistRepository.deleteSaveDetail(docID);
	}

	@Override
	public boolean deleteSavelist(int listID) {
		return savelistRepository.deleteSavelist(listID);
	}

	


	
}
