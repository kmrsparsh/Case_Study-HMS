package com.bean.PatientRegistrationDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckPatientRegistrationDao {

	public static boolean check(String patientssnid) {
		String sql = "select * from patient where pSSNID=?";
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		boolean status=false;
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, patientssnid);
			
			ResultSet rs=st.executeQuery();  
			status = rs.next();  
			          
			}catch(Exception e){}  
			return status;  
		
	}
	
}
