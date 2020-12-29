package com.bean.PatientUpdateDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bean.Patient.PatientUpdate;

public class PatientUpdateDao {
	
	public static String PatientUpdateDao(PatientUpdate newupdate,String patientid) throws SQLException{
		String status = "";
		
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			String sql = "UPDATE patient SET pname = ?,page = ?,pdateofAddmission = ?,typeofbed = ?,address = ?,city = ?,state = ? WHERE pId = ?"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,newupdate.getpatientname());
			st.setInt(2,newupdate.getpatientage());
			st.setString(3,newupdate.getdateofadmission());
			st.setString(4,newupdate.gettypeofroom());
			st.setString(5,newupdate.getpatientaddress());
			st.setString(6,newupdate.getpatientcity());
			st.setString(7,newupdate.getpatientstate());
			
			st.setString(8,patientid);
			int insertedRowCount = st.executeUpdate();
			if(insertedRowCount>0) {
				status = "success";
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}

}
