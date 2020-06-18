package com.flipkart.restClient;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.bean.Course;

@Path("/course")
public class CourseRestClient {
	CourseDAO courseDAO = new CourseDAO();
	
	//Getting course catalog
	@GET
	@Path("/allcourse")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> veiwAllcourse(){
		return courseDAO.listAll();
	}
	
	@GET
	@Path("/allcourse/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course veiwCourse(@PathParam("cid") int courseid){
		return courseDAO.listByID(courseid);   
	}
	
	//Posting the course
	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_PLAIN)
	public Response createTrackInJSON(Course course) {
		CourseDAO courseDAO = new CourseDAO();
		courseDAO.addCourse(course);
		String result = "Course "+course+" saved";
		return Response
				.status(201)
				.entity(result)
				.build();
	}
	
	//deleting the course 
	@DELETE
	@Path("/delete/{cid}")
	public Response deleteCourse(@PathParam("cid") int courseid) {
		CourseDAO courseDAO = new CourseDAO();
		courseDAO.resetCourse(courseid);
		return Response
				.status(200)
				.entity("Course : " + courseid + " successfully deleted")
				.build();
	}
	
	
	//Updating course professor
	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Course updateCourse(Course course) {
		CourseDAO courseDAO = new CourseDAO();
		courseDAO.addCourseProf(course.getCourseCode(), course.getProf());
		return course;
	}
}
