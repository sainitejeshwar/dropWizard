package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;

import com.flipkart.utils.DBUtils;
/*
 * CLASS DESCRIPTION
 * 
 * For performing the Studuent DAO operations
 * 
 */
public class StudentDAO{
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(StudentDAO.class);

	//Add student in Student Table
	public void addStudent(User user, Student student) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_INSERT);
			stmt.setString(1, user.getEmailID());
			stmt.setString(2, user.getName());
			stmt.setInt(3, student.getSemester());
			stmt.setString(4, student.getBranch());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	// Listing all the students
	public ArrayList<Student> listAll() {
		ArrayList<Student> students = new ArrayList<Student>();
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_SELECT_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				student.setStudentID(rs.getInt("StudentID"));
				student.setName(rs.getString("Name"));
				student.setBranch(rs.getString("Branch"));
				student.setSemester(rs.getInt("Semester"));
				student.setStudentCourses(rs.getInt("Course1"));
				student.setStudentCourses(rs.getInt("Course2"));
				student.setStudentCourses(rs.getInt("Course3"));
				student.setStudentCourses(rs.getInt("Course4"));
				student.setRegistrationNumber(rs.getInt("RegNo"));
				students.add(student);
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return students;
	}
	
	//Listing student by ID
	public Student listByID(String emailid) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Student student = new Student();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_SELECT_BY_ID);
			stmt.setString(1, emailid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				student.setStudentID(rs.getInt("StudentID"));
				student.setName(rs.getString("Name"));
				student.setBranch(rs.getString("Branch"));
				student.setSemester(rs.getInt("Semester"));
				student.setStudentCourses(rs.getInt("Course1"));
				student.setStudentCourses(rs.getInt("Course2"));
				student.setStudentCourses(rs.getInt("Course3"));
				student.setStudentCourses(rs.getInt("Course4"));
				student.setRegistrationNumber(rs.getInt("RegNo"));
			}
			rs.close();
			return student;
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}
	
	//Update Student courses when registration Completes
	public void UpdateStudentRegistration(ArrayList<Course> courses , Student student) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_UPDATE_REGISTRATION);
			stmt.setInt(1, courses.get(0).getCourseCode());
			stmt.setInt(2, courses.get(1).getCourseCode());
			stmt.setInt(3, courses.get(2).getCourseCode());
			stmt.setInt(4, courses.get(3).getCourseCode());
			stmt.setInt(5, student.getRegistrationNumber());
			stmt.setInt(6, student.getStudentID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
}
