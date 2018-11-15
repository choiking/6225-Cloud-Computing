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

import com.csye6225.fall2018.courseservice.datamodel.Announcement;
import com.csye6225.fall2018.courseservice.service.AnnouncementsService;

@Path("announcements")
public class AnnouncementsResource {
	AnnouncementsService as = new AnnouncementsService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnouncementsByBoardId(
			@QueryParam("boardId") String boardId) {
		if (boardId == null) return as.getAllAnnouncements();
		return as.getAnnouncementsByBoardId(boardId);
	}
	
	
	// ... webapi/Announcement/1 
	@GET
	@Path("/{boardId_announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAnnouncement(@PathParam("boardId_announcementId") String boardId_announcementId) {
		String bId = boardId_announcementId.split("_")[0];
		String aId = boardId_announcementId.split("_")[1];
		return as.getAnnouncement(bId, aId);
	}
	
	@DELETE
	@Path("/{boardId_announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement deleteAnnouncement(@PathParam("boardId_announcementId") String boardId_announcementId) {
		String bId = boardId_announcementId.split("_")[0];
		String aId = boardId_announcementId.split("_")[1];
		return as.deleteAnnouncement(bId, aId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement addAnnouncement(Announcement Announcement) {
		return as.addAnnouncement(Announcement);
	}
	
	@PUT
	@Path("/{boardId_announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement updateAnnouncement(@PathParam("boardId_announcementId") String boardId_announcementId,   
			Announcement announcement) {
		String bId = boardId_announcementId.split("_")[0];
		String aId = boardId_announcementId.split("_")[1];
		return as.updateAnnouncementInformation(bId, aId, announcement);
	}
}
