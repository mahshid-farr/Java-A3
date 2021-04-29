package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Db.DbConnection;
import data.Operations;

@ManagedBean
@SessionScoped
@ViewScoped
public class Students {

	
	public int SId;
	public String Fname;
	public String Lname;
	public String Email;
	public String CId;
	public String Section;
	public String Sem;
	
	public Students(){}
	public int getSId() {
		return SId;
	}
	public void setSId(int sId) {
		SId = sId;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	public String getSection() {
		return Section;
	}
	public void setSection(String section) {
		Section = section;
	}
	public String getSem() {
		return Sem;
	}
	public void setSem(String sem) {
		Sem = sem;
	}
	
	//get all students
	public ArrayList<Students> getStudents(){
		
		return Operations.findAllStudents();
	}
public ArrayList<Students> getCourses(){
		
		return Operations.takeCourse();
	}
	//Add Student
	
	public  void AddStudent() {
		DbConnection db = new DbConnection();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try{
			conn = db.getConnection();
			stmt = conn.prepareStatement("INSERT INTO STUDENTS (STUDENT_ID,FIRST_NAME,LAST_NAME,EMAIL) VALUES ('"+SId+"','"+Fname+"','"+Lname+"','"+Email+"')");
			
			
				stmt.executeUpdate();
				
		}
		catch(Exception e){System.out.println(e);}
		
		
	}
	
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
	
	//edit Student
	
	public  String editStudent(){
		DbConnection db = new DbConnection();
		
		Connection conn = null;
		Statement stmt = null;
			FacesContext fc = FacesContext.getCurrentInstance();
			
			Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
			String update = params.get("action");
		
			System.out.println(update);
			try{
				conn = db.getConnection();
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS WHERE STUDENT_ID ="+update);
				Students st = new Students();
				rs.next();
				st.setFname(rs.getString("FIRST_NAME"));
				st.setLname(rs.getString("LAST_NAME"));
				st.setEmail(rs.getString("EMAIL"));
				st.setSId(Integer.parseInt(update));
				sessionMap.put("editstudent",st);
			}
			catch(Exception e){System.out.println(e);}
			return "/EditStudent.xhtml?faces-redirect=true";
		}
	
	
	
	//Update Student
	
public  String updateStudent(){
	DbConnection db = new DbConnection();
	
	Connection conn = null;
	PreparedStatement stmt = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	      String update = params.get("student_ID");
		
	System.out.println(update);
	
		try{
			conn = db.getConnection();
			stmt = conn.prepareStatement("UPDATE STUDENTS SET FIRST_NAME=?,LAST_NAME=?,EMAIL=? WHERE STUDENT_ID=?");
			stmt.setString(1,Fname);
			stmt.setString(2, Lname);
			stmt.setString(3, Email);
			stmt.setString(4, update);
			System.out.println(stmt);
			stmt.executeUpdate();
			
			
			
		}
		catch(Exception e){System.out.println(e);}
		return "/Students.xhtml?faces-redirect=true";
	}

	//delete student

public  String deleteStudent(){
	DbConnection db = new DbConnection();
	
	Connection conn = null;
	PreparedStatement stmt = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String update = params.get("action");
	
		try{
			conn = db.getConnection();
			stmt = conn.prepareStatement("DELETE FROM STUDENTS WHERE STUDENT_ID =?");  // "DELETE FROM STUDENTS WHERE STUDENT_ID ="+update
			stmt.setString(1, update);
			stmt.executeUpdate();
		}
		catch(Exception e){System.out.println(e);}
		return "/Students.xhtml?faces-redirect=true";
	}

//addind courses



public  String addCourse(){
	DbConnection db = new DbConnection();
	
	Connection conn = null;
	PreparedStatement stmt = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	      String update = params.get("student_ID");
		
	System.out.println(update);
	int i = 0;
		try{i++;
			conn = db.getConnection();
			stmt = conn.prepareStatement("INSERT INTO COURSES (COURSE_ID,SEMESTER.SECTION,C_ID,STUDENT_ID) VALUES (?,?,?,?,?)");
			stmt.setString(1,CId);
			stmt.setString(2, Sem);
			stmt.setString(3, Section);
			stmt.setInt(4, i);
			stmt.setString(5, update);
			System.out.println(stmt);
			stmt.executeUpdate();
			
			
			
		}
		catch(Exception e){System.out.println(e);}
		return "/CourseDetail.xhtml?faces-redirect=true";
	}
//Drop Course

public  String deleteCourse(){
	DbConnection db = new DbConnection();
	
	Connection conn = null;
	PreparedStatement stmt = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String update = params.get("action5");
	
		try{
			conn = db.getConnection();
			stmt = conn.prepareStatement("DELETE FROM COURSES WHERE STUDENT_ID =?");  // "DELETE FROM STUDENTS WHERE STUDENT_ID ="+update
			stmt.setString(1, update);
			stmt.executeUpdate();
		}
		catch(Exception e){System.out.println(e);}
		return "/Students.xhtml?faces-redirect=true";
	}
}
