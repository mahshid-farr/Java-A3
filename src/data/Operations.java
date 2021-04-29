package data;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import Db.DbConnection;
import model.Students;

public class Operations {

	Students st = new Students();
	//get all students
	
	public static ArrayList<Students> findAllStudents(){
	DbConnection db = new DbConnection();
	ArrayList<Students> joblist = new ArrayList<Students>();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rtst = null;
	
	try{
		conn = db.getConnection();
		stmt = conn.createStatement();
		rtst = stmt.executeQuery("SELECT * FROM STUDENTS");
		
		while(rtst.next())
		{
			Students j = new Students();
			j.setSId(rtst.getInt(1));
			j.setFname(rtst.getString(2));
			j.setLname(rtst.getString(3));
			j.setEmail(rtst.getString(4));
			
			joblist.add(j);
			
		}
	}
	catch(Exception e){System.out.println(e);}
	return joblist;
	
}
	
	//find all courses
	
	public static ArrayList<Students> findAllCourses(){
		DbConnection db = new DbConnection();
		ArrayList<Students> joblist = new ArrayList<Students>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rtst = null;
		
		try{
			conn = db.getConnection();
			stmt = conn.createStatement();
			rtst = stmt.executeQuery("SELECT COURSE_ID,SEMESTER,SECTION FROM COURSES");
			
			while(rtst.next())
			{
				Students j = new Students();
				j.setCId(rtst.getString(1));
				j.setSem(rtst.getString(2));
				j.setSection(rtst.getString(3));
						
				joblist.add(j);
				
			}
		}
		catch(Exception e){System.out.println(e);}
		return joblist;
		
	}
	
	public  static ArrayList<Students> takeCourse(){
		DbConnection db = new DbConnection();
		
		Connection conn = null;
		Statement stmt = null;
			FacesContext fc = FacesContext.getCurrentInstance();
			ArrayList<Students> clist = new ArrayList<Students>();
			Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
			String update1 = params.get("action4");
		
			System.out.println(update1);
			try{
				conn = db.getConnection();
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COURSE_ID,SEMESTER,SECTION FROM COURSES WHERE STUDENT_ID ="+update1);
				Students st = new Students();
				while(rs.next()){
				st.setCId(rs.getString("COURSE_ID"));
				st.setSem(rs.getString("SEMESTER"));
				st.setSection(rs.getString("SECTION"));
				
				clist.add(st);}
			}
			catch(Exception e){System.out.println(e);}
			return clist;
		}
}
