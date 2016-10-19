package org.khmeracademy.akd.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {
	@JsonProperty("FEEDBACK_ID")
	private int feedbackID;
	
	@JsonProperty("CREATED_DATE")
	private String date;
	
	@JsonProperty("DES")
	private String des;
	
	@JsonProperty("STATUS")
	private int status;
	
	
	public int getFeedbackID() {
		return feedbackID;
	}
	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
