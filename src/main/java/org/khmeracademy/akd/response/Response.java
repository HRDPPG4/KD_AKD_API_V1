package org.khmeracademy.akd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	@JsonProperty("CODE")
	public String code=ResponseCode.FAIL;
	
	@JsonProperty("COUNT")
	private long count=0;
	
	

	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code=code;
	}
	
	
	
	@JsonProperty("MESSAGE")
	public String message;
	

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage()
	{
		if(code==ResponseCode.FAIL){
			this.message="FAILED!";
		}
		else if(code==ResponseCode.SUCCESS){
			this.message="SUCCESSFUL!";
		}
		else if(code==ResponseCode.INSERT_FAIL){
			this.message="INSERT FAILED!";
		}
		else if(code==ResponseCode.INSERT_SUCCESS){
			this.message="INSERT SUCCESSFUL!";
		}
		else if(code==ResponseCode.UPDATE_FAIL){
			this.message="UPDATE FAILED!";
		}
		else if(code==ResponseCode.UPDATE_SUCCESS){
			this.message="UPDATE SUCCESSFUL!";
		}
		else if(code==ResponseCode.DELETE_FAIL){
			this.message="DELETE FAILED!";
		}
		else if(code==ResponseCode.DELETE_SUCCESS){
			this.message="DELETE SUCCESSFUL!";
		}
		else if(code==ResponseCode.RECORD_NOT_FOUND){
			this.message="RECORD NOT FOUND!";
		}
		else if(code==ResponseCode.RECORD_FOUND){
			this.message="RECORD FOUND!";
		}
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}	
}
