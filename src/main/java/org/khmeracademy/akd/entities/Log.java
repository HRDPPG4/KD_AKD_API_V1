package org.khmeracademy.akd.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Log {
	@JsonProperty("LOG_ID")
	private int logID;
	
	@JsonProperty("CREATED_DATE")
	private String date;
	
	@JsonProperty("REMARK")
	private String remark;
	
	@JsonProperty("USER_ID")
	private int userID;
	
	@JsonProperty("DOC_ID")
	private String docID;
	
	@JsonProperty("STATUS")
	private int status;

	@JsonProperty("USER")
	 private User user;
	@JsonProperty("DOCUMENT")
	private Document document;
	@JsonProperty("DOCUMENTS")
	private List<Document> documents;
	@JsonProperty("USERS")
	private List<User> users;
	
	
	public int getLogID() {
		return logID;
	}
	public void setLogID(int logID) {
		this.logID = logID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getDocID() {
		return docID;
	}
	public void setDocID(String docID) {
		this.docID = docID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}

	
	
	
}
