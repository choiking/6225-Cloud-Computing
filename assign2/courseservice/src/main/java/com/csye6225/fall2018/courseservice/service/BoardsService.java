package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDBConnector;

public class BoardsService {
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper; 
	

	public BoardsService() {
		dynamoDB = new DynamoDBConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());
	}
	
	// Getting a list of all Board 
	// GET "..webapi/boards"
	public List<Board> getAllBoards() {			
		return mapper.scan(Board.class, new DynamoDBScanExpression());
	}
	
	
	public Board addBoard(Board b) {	
		mapper.save(b);
		return b;
	}
	
	// Getting Board by boardId
	public Board getBoard(String boardId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(boardId));
		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board> ()
				.withIndexName("boardIdIndex")
				.withConsistentRead(false)
			    .withKeyConditionExpression("boardId = :v1")
			    .withExpressionAttributeValues(eav);
		List<Board> res = mapper.query(Board.class, queryExpression);
		if (res.isEmpty()) return new Board();
		return res.get(0);
		
	}
	
	// Deleting a Board
	public Board deleteBoard(String boardId) {
		
		BoardsService bs = new BoardsService();
		Board b = bs.getBoard(boardId);
		mapper.delete(b);		
		return b;
	}
	
	// Updating Board Info
	public Board updateBoardInformation(String boardId, Board board) {	
		BoardsService bs = new BoardsService();
		Board b = bs.getBoard(boardId);
		if (board.getCourseId() != null)
			b.setCourseId(board.getCourseId());
		mapper.save(b);
		return b;
	}
	
	public List<Board> getBoardsByCourseId(String courseId) {
			
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("begins_with(courseId,:v1)")
			    .withExpressionAttributeValues(eav);	
		return mapper.scan(Board.class, scanExpression);
	}
	
	
}
