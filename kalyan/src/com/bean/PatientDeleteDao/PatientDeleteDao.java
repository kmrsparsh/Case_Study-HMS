package com.bean.PatientDeleteDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class PatientDeleteDao {

	public static int PatientDeleteDao(String patientid) throws SQLException{
		//System.out.println("in dao");
		int insertedRowCount = 0;
	    
		//details of sql db
		
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		
		
		try {
			//System.out.println("in try");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			
			String sql = "DELETE FROM patient WHERE pId = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,patientid);
			
			insertedRowCount = st.executeUpdate();
			
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return insertedRowCount;
		
		
	}
	
}
