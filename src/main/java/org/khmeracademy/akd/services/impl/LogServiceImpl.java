package org.khmeracademy.akd.services.impl;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Log;
import org.khmeracademy.akd.repositories.LogRepository;
import org.khmeracademy.akd.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService{
	
	@Autowired
	private LogRepository logRepository;
	
	
	@Override
	public boolean delete(int id) {
		try{
			return logRepository.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(Log log) {
		
		return logRepository.insert(log);
		
	}

	@Override
	public boolean update(Log log) {
		return logRepository.update(log);
	}

	@Override
	public ArrayList findAll() {
		return logRepository.findAll();
	}

	@Override
	public Log findOne(int id) {
		return logRepository.findOne(id);
	}

	@Override
	public ArrayList<Log> findAllByUser(int userID) {
		
		return logRepository.findAllByUser(userID);
	}

	@Override
	public boolean deleteAllLogByUserID(int userID) {
		return logRepository.deleteAllLogByUserID(userID);
	}

	
}
