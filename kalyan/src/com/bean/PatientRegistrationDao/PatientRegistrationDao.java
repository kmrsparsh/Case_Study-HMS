package com.bean.PatientRegistrationDao;

import com.bean.PatientRegistrationDao.InitiatePatientPharmacy;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;


import java.sql.ResultSet;

import java.sql.SQLException;

import com.bean.Patient.Patient;


public class PatientRegistrationDao {

	public static int addPatient(Patient newPatient, String token) throws SQLException {
		
		
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
	
				String patientId = "";
				int insertedRowCount = 0;

	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,password);

		String sql = "INSERT INTO patient(pSSNID, pname, page, pdateofAddmission, typeOfBed, address, city, state, pstatus) VALUES(?,?,?,?,?,?,?,?,?)";

	PreparedStatement st = con.prepareStatement(sql);
	//System.out.println("This is dao");
	st.setString(1, newPatient.getpatientssnid());
	st.setString(2, newPatient.getpatientname());
	st.setInt(3, newPatient.getpatientage());
	st.setString(4, newPatient.getdateofadmission());
	st.setString(5, newPatient.gettypeofroom());
	st.setString(6, newPatient.getpatientaddress());
	st.setString(8, newPatient.getpatientstate());
	st.setString(7, newPatient.getpatientcity());
	st.setString(9, newPatient.getstatus());
	

	insertedRowCount = st.executeUpdate();
	
	int status = InitiatePatientPharmacy.InitiatePatientPharmacy(newPatient.getpatientssnid());
	
	
	}
		
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
		return insertedRowCount;
		
	}}


