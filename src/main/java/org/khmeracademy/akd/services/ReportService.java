package org.khmeracademy.akd.services;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Report;
import org.khmeracademy.akd.utilities.Paging;
public interface ReportService {	
	boolean delete(int id);
	
	boolean insert(Report rep);
	
	boolean update(Report rep);
	
	ArrayList<Object> findAll(Paging pagination);
	
	Report findOne(int id);
	
}
