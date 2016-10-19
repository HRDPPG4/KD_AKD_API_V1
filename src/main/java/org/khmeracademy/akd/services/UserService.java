package org.khmeracademy.akd.services;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.khmeracademy.akd.utilities.UserFilter;
import org.khmeracademy.akd.entities.forms.UserLogin;

public interface UserService {	
	
	boolean delete(int id);
	
	boolean insert(User user);
	
	boolean update(User user);
	
	ArrayList<Object> findAll(Paging pagiination);
	
	User findOne(int id);
	
	int getUserCount();
	
	User findUserByEmail(UserLogin userlogin);
	
	User findUserByUserHash(String userHash);
	
	
	
}
