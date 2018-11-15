package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Announcement;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDBConnector;

public class AnnouncementsService {
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper; 
	

	public AnnouncementsService() {
		dynamoDB = new DynamoDBConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());
	}
	
	// Getting a list of all Announcement 
	// GET "..webapi/Announcements"
	public List<Announcement> getAllAnnouncements() {			
		return mapper.scan(Announcement.class, new DynamoDBScanExpression());
	}
	
	
	public Announcement addAnnouncement(Announcement a) {	
		mapper.save(a);
		return a;
	}
	
	// Getting One Announcement
	public Announcement getAnnouncement(String bId, String aId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(aId));
		eav.put(":v2",  new AttributeValue().withS(bId));
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
		    .withIndexName("boardIdIndex")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("boardId = :v2 and begins_with(announcementId, :v1)")
		    .withExpressionAttributeValues(eav);

		List<Announcement> res = mapper.query(Announcement.class, queryExpression);
		if (res.isEmpty()) return new Announcement();
		return res.get(0);
		
	}
	
	// Deleting a Announcement
	public Announcement deleteAnnouncement(String bId, String aId) {
		
		AnnouncementsService as = new AnnouncementsService();
		Announcement a = as.getAnnouncement(bId, aId);
		mapper.delete(a);		
		return a;
	}
	
	// Updating Announcement Info
	public Announcement updateAnnouncementInformation(String bId, String aId, Announcement announcement) {	
		AnnouncementsService as = new AnnouncementsService();
		Announcement a = as.getAnnouncement(bId, aId);
		if (announcement.getBoardId() != null)
			a.setBoardId(announcement.getBoardId());
		if (announcement.getAnnouncementText() != null)
			a.setAnnouncementText(announcement.getAnnouncementText());
		mapper.save(a);
		return a;
	}
	
	
	public List<Announcement> getAnnouncementsByBoardId(String bId) {
		System.out.println(bId);
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(bId));
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
		    .withIndexName("boardIdIndex")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("boardId = :v1")
		    .withExpressionAttributeValues(eav);


		return mapper.query(Announcement.class, queryExpression);
	}
}

