package org.khmeracademy.akd.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Savelist {
	@JsonProperty("LIST_ID")
	private int savelistID;
	
	@JsonProperty("LIST_NAME")
	private String name;
	
	@JsonProperty("CREATED_DATE")
	private String createdDate;
	
	@JsonProperty("REMARK")
	private String remark;
	
	@JsonProperty("USER_ID")
	private int userID;
	
	@JsonProperty("DOC_ID")
	private String docID;
	
	@JsonProperty("STATUS")
	private int status;
	@JsonProperty("SAVELISTDETAIL")
	private List<Savelist>  savelistdetail;

	public List<Savelist> getSavelistdetail() {
		return savelistdetail;
	}
	public void setSavelistdetail(List<Savelist> savelistdetail) {
		this.savelistdetail = savelistdetail;
	}
	@JsonProperty("USERS")
	private List<User> users;
	
	/*@JsonProperty("USER")
	private User user;*/
	
	
	@JsonProperty("DOCUMENT")
	private List<Document> document;
	
	@JsonProperty("TOTAL_DOCUMENT")
	private int totalDocument;
	
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	
	public List<Document> getDocument() {
		return document;
	}
	public void setDocument(List<Document> document) {
		this.document = document;
	}
	public int getSavelistID() {
		return savelistID;
	}
	public void setSavelistID(int savelistID) {
		this.savelistID = savelistID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getTotalDocument() {
		return totalDocument;
	}
	public void setTotalDocument(int totalDocument) {
		this.totalDocument = totalDocument;
	}

	
	
}
