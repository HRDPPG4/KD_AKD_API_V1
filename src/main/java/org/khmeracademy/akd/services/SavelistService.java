package org.khmeracademy.akd.services;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Savelist;
import org.khmeracademy.akd.utilities.Paging;
public interface SavelistService {	
	boolean updateToDisable(int id);
	
	boolean insert(Savelist list);
	boolean update(Savelist list);
	boolean insertDetail(Savelist list);
	boolean insertSavelistOnly(Savelist list);
	boolean deleteSavelistDetail(String docID);
	
	ArrayList<Object> findAll(Paging pagination);
	
	ArrayList<Savelist> findSavelistByUserID(int userID);
	ArrayList<Savelist> findSavelistMenuByUserID(int userID);
	ArrayList<Savelist> findEachSavelistByUserID(int userID,int savelistID);
	
	Savelist findOne(int id);
	boolean deleteSavelist(int id);
	
}
