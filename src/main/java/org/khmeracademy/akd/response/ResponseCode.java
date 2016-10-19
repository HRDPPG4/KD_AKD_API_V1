package org.khmeracademy.akd.response;

public interface ResponseCode {
	String FAIL="000";
	String SUCCESS="900";
	
	String INSERT_SUCCESS="100";
	String INSERT_FAIL="101";
	
	String DELETE_SUCCESS="200";
	String DELETE_FAIL="201";
	
	String UPDATE_SUCCESS="300";
	String UPDATE_FAIL="301";
	
	String RECORD_FOUND="400";
	String RECORD_NOT_FOUND="401";
	
}
