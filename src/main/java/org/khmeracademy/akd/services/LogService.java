package org.khmeracademy.akd.services;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Log;
public interface LogService {	
	boolean delete(int id);
	
	boolean insert(Log feed);
	
	boolean update(Log feed);
	
	ArrayList<Object> findAll();
	ArrayList<Log> findAllByUser(int userID);
	
	Log findOne(int id);
	boolean deleteAllLogByUserID(int userID);
	
}
