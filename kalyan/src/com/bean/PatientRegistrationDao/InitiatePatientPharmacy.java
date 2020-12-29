package com.bean.PatientRegistrationDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InitiatePatientPharmacy {
	
	public static int InitiatePatientPharmacy(String pSSNID) {
		
	
		

		int i = 0;
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			String sql = "select * from patient where pSSNID=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pSSNID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
			int pId = rs.getInt("pId");
			String pid = Integer.toString(pId);
			String medname = "none";
			//System.out.println("entered initiate pharmacy");
			
			String count = "INSERT INTO patient_pharmacy(pId, Medicine_name, Usedquantity, RatePerMed,Totalcost) VALUES(?,?,?,?,?)";
			PreparedStatement stat = con.prepareStatement(count);
			stat.setInt(1, pId);
			stat.setString(2,medname);
			stat.setInt(3, 0);
			stat.setInt(4, 0);
			stat.setInt(5, 0);
			i = stat.executeUpdate();
			
		
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,uname,password);
			String sql = "select * from patient where pSSNID=?";
			
			PreparedStatement sts = conn.prepareStatement(sql);
			sts.setString(1, pSSNID);
			ResultSet rst = sts.executeQuery();
			if (rst.next()) {
			int pId = rst.getInt("pId");
			String pid = Integer.toString(pId);
			String dianame = "none";
			String counts = "INSERT INTO patientdiagnostics(pId, diagnostic, amount) VALUES(?,?,?)";
			PreparedStatement stats = conn.prepareStatement(counts);
			stats.setInt(1, pId);
			stats.setString(2,dianame);
			stats.setInt(3, 0);
			int y = stats.executeUpdate();

			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return i;
		
		
	}

}
