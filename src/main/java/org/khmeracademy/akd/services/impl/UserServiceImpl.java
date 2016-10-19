package org.khmeracademy.akd.services.impl;
import java.util.ArrayList;

import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.entities.forms.UserLogin;
import org.khmeracademy.akd.repositories.UserRepository;
import org.khmeracademy.akd.services.UserService;
import org.khmeracademy.akd.utilities.Paging;
import org.khmeracademy.akd.utilities.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public boolean delete(int id) {
		try{
			return userRepository.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(User user) {
		
		return userRepository.insert(user);
		
	}

	@Override
	public boolean update(User user) {
		return userRepository.update(user);
	}

	@Override
	public ArrayList findAll(Paging pagination) {
		pagination.setTotalCount(userRepository.count());
		return userRepository.findAll(pagination);
	}

	@Override
	public User findOne(int id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public int getUserCount() {
		return userRepository.getUserCount();
	}
	
	@Override
	public User findUserByEmail(UserLogin userlogin) {		
		return userRepository.findUserByEmail(userlogin);
	}

	@Override
	public User findUserByUserHash(String userHash) {
		return userRepository.findUserByUserHash(userHash);
	}

	
	
}
