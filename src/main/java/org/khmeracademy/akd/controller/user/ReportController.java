package org.khmeracademy.akd.controller.user;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Report;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.ReportService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value="/report",method=RequestMethod.GET)
	public ResponseList<Report> findAll(Paging pagination)
	{
		ArrayList<Object> rep=reportService.findAll(pagination);
		ResponseList<Report> res=new ResponseList<Report>();
		
		if(reportService.findAll(pagination)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(rep);
			res.setPaging(pagination);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	
	@RequestMapping(value="/report/{id}",method=RequestMethod.GET)
	public ResponseObject<Report> fineOne(@PathVariable("id") int id)
	{
		Report rep=reportService.findOne(id);
		ResponseObject<Report> res=new ResponseObject<Report>();
		if(reportService.findOne(id)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(rep);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	@RequestMapping(value="/report/{id}",method=RequestMethod.PUT)
	public Response delete(@PathVariable("id") int id)
	{
	
		boolean status=reportService.delete(id);
		Response res=new Response();
		if(status){
			res.setCode(ResponseCode.DELETE_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.DELETE_FAIL);
			res.setMessage();
		}
		return res;
	}
	
	
	
	@RequestMapping(value="/report",method=RequestMethod.POST)
	public Response insert(@RequestBody Report rep)
	{
		Response res=new Response();
		if(reportService.insert(rep)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}
		
		return res;
		
	}

	
	
	@RequestMapping(value="/report",method=RequestMethod.PUT)
	public Response update(@RequestBody Report rep)
	{
		Response res=new Response();
		
		if(reportService.update(rep))
		{
			res.setCode(ResponseCode.UPDATE_SUCCESS);
			res.setMessage();
		}
		else
		{
			res.setCode(ResponseCode.UPDATE_FAIL);
			res.setMessage();
		}
	
		return res;
	}
	
}
