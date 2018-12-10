package com.csye6225.fall2018.courseservice.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.service.BoardsService;

@Path("boards")
public class BoardsResource {
	BoardsService bs = new BoardsService();
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getCoursesByProfessorId(
			@QueryParam("courseId") String courseId) {
		
		if (courseId == null) {
			return bs.getAllBoards();
		}
		return bs.getBoardsByCourseId(courseId);
		
	}
	
	// ... webapi/Board/1 
	@GET
	@Path("/{BoardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("BoardId") String BoardId) {
		return bs.getBoard(BoardId);
	}
	
	@DELETE
	@Path("/{BoardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("BoardId") String BoardId) {
		return bs.deleteBoard(BoardId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board Board) {
		return bs.addBoard(Board);
	}
	
	@PUT
	@Path("/{BoardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("BoardId") String boardId,   
			Board board) {
		return bs.updateBoardInformation(boardId, board);
	}
}
	
