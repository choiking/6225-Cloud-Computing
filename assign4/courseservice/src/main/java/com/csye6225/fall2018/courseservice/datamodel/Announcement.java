package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Announcement")
public class Announcement {
	
	private String Id;
	private String announcementId;
	private String announcementText;
	private String boardId;
	private String courseId;
	
	public Announcement() {
		
	}
	public Announcement(String Id, String announcementId, String announcementText, String boardId, String courseId) {
		super();
		this.Id = Id;
		this.announcementId = announcementId;
		this.announcementText = announcementText;
		this.boardId = boardId;
		this.courseId = courseId;
	}
	
	@DynamoDBAutoGeneratedKey
	@DynamoDBHashKey(attributeName="Id")
	public String getId() {
        return Id;
	}
	public void setId(String Id) {
        this.Id = Id;
	}
	
	@DynamoDBIndexRangeKey(globalSecondaryIndexName = "boardIdIndex", attributeName="announcementId")
	public String getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}
	
	@DynamoDBAttribute(attributeName="announcementText")
	public String getAnnouncementText() {
		return announcementText;
	}
	public void setAnnouncementText(String announcementText) {
		this.announcementText = announcementText;
	}
	
	@DynamoDBIndexHashKey(globalSecondaryIndexName = "boardIdIndex", attributeName="boardId")
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	@DynamoDBAttribute(attributeName="courseId")
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	
	
	
	
	

}
