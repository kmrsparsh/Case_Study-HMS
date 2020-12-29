package com.bean.LoginDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class LoginDao 
{
	
	public static boolean check(String name ,String pass)
	{
		
		String sql = "select * from login where userName=? and userPassword=?";
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		boolean status=false;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, pass);
			ResultSet rs=st.executeQuery();  
			status = rs.next();  
			          
			}catch(Exception e){System.out.println(e);}  
			return status;  
		
	}

}
