package org.khmeracademy.akd.services.impl;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Report;
import org.khmeracademy.akd.repositories.ReportRepository;
import org.khmeracademy.akd.services.ReportService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportRepository reportRepository;
	
	
	@Override
	public boolean delete(int id) {
		try{
			return reportRepository.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(Report rep) {
		
		return reportRepository.insert(rep);
		
	}

	@Override
	public boolean update(Report rep) {
		return reportRepository.update(rep);
	}

	@Override
	public ArrayList findAll(Paging pagination) {
		pagination.setTotalCount(reportRepository.count());
		return reportRepository.findAll(pagination);
	}

	@Override
	public Report findOne(int id) {
		return reportRepository.findOne(id);
	}

	
}
