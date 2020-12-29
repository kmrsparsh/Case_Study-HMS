package com.bean.pharmacy;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bean.Patient.Medicine;
import antlr.collections.List;
import java.util.ArrayList;

import com.bean.PatientRegistrationDao.CheckPatientRegistrationDao;
import com.bean.PatientUpdateDao.CheckPatientUpdateDao;
import com.bean.Patient.Medicine;

@WebServlet("/Pharmacy")

public class Pharmacy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession();
		
		String patientid = request.getParameter("patientid");
		System.out.println(patientid);
		int Usedquantity = 0;
		int RatePerMed = 0;
		int Totalcost =0;
		
		
		if(CheckPatientUpdateDao.check(patientid)) {
			
			String url = "jdbc:mysql://127.0.0.1:3306/login_details";
			String uname ="root";
			String password = "Mehar@144";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,uname,password);
				
				String sql = "select * from patient where pId=?";
				PreparedStatement pc = con.prepareStatement(sql);
				pc.setString(1, patientid);
				ResultSet sr = pc.executeQuery();
				if (sr.next()) {
					
					String patientssnid = sr.getString("pSSNID");
					String patientname = sr.getString("pname");
					int age = sr.getInt("page");
					String date = sr.getString("pdateofAddmission");
					String typeofroom = sr.getString("typeOfBed");
					String address = sr.getString("address");
					String state = sr.getString("state");
					String city = sr.getString("city");
					
					//System.out.println(city);
				
					//setting messages
					
					
					String pid = patientid;
					request.setAttribute("pid", pid);
					String pname = patientname;
					request.setAttribute("pname", pname);
					int page = age;
					request.setAttribute("page", page);
					String pdateofadmission = date;
					request.setAttribute("pdateofadmission", pdateofadmission);
					String ptypeofroom = typeofroom;
					request.setAttribute("ptypeofroom", ptypeofroom);
					String paddress = address;
					request.setAttribute("paddress", paddress);
					String pstate = state;
					request.setAttribute("pstate", pstate);
					String pcity = city;
					request.setAttribute("pcity", pcity);
					//System.out.println("entered");
			
			
		}

			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
				
			
			
		//for loop
		String message = "medicine";
		String disabled = "disabled";
		request.setAttribute("myparam", message);
		request.setAttribute("patientid", patientid);
		request.setAttribute("disabled", disabled);
		request.getRequestDispatcher("pharmacy.jsp").forward(request, response);
	}
		else {
			String message = "Given Patient ID is wrong ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("pharmacy.jsp").forward(request, response);
		}

}
}
