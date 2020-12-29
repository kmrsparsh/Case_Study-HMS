package com.bean.PatientUpdateDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckPatientUpdateDao {

	
	public static boolean check(String patientid) {
		String sql = "select * from patient where pId=?";
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		boolean status=false;
		int patientId = Integer.parseInt(patientid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, patientId);
			
			ResultSet rs=st.executeQuery();  
			status = rs.next(); 
			System.out.println(status);
			    
			}catch(Exception e){System.out.println(e);}  
			return status;  
		
	}
}
