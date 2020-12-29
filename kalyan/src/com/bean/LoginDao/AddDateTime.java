package com.bean.LoginDao;

import java.sql.SQLException;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class AddDateTime {

    public AddDateTime(String username) throws SQLException{
		String message = "Successfully logged in";
		LocalDateTime datetime = java.time.LocalDateTime.now();
		String currentdate = datetime.toString();
		
		
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
   
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			
			String sql = "INSERT INTO datetime_details VALUES(?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, currentdate);
			st.setString(3, message);
			st.executeUpdate();
			
			//System.out.println(i);
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
}
}

	

