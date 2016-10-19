package org.khmeracademy.akd.services;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Feedback;
import org.khmeracademy.akd.utilities.Paging;
public interface FeedbackService {	
	boolean delete(int id);
	
	boolean insert(Feedback feed);
	
	boolean update(Feedback feed);
	
	ArrayList<Object> findAll(Paging pagination);
	
	Feedback findOne(int id);
	
}
