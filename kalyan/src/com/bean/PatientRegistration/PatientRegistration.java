package com.bean.PatientRegistration;

import java.io.IOException;

import com.bean.service.PatientRegistrationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Patient.Patient;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;

import com.bean.PatientRegistrationDao.CheckPatientRegistrationDao;
import com.bean.PatientRegistrationDao.PatientRegistrationDao;
@WebServlet("/PatientRegistration")


public class PatientRegistration extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
			
			String token = (String) session.getAttribute("TOKEN");
			String patientssnid = request.getParameter("patientssnid");
			String patientname=request.getParameter("patientname");
			Integer patientage = Integer.parseInt(request.getParameter("patientage"));
			String dateofadmission = request.getParameter("dateofadmission");
			String typeofroom = request.getParameter("typeofroom");
			String patientaddress=request.getParameter("patientaddress");
			String patientstate = request.getParameter("patientstate");
			String patientcity = request.getParameter("patientcity");
			String status = "Active";
			
			if(CheckPatientRegistrationDao.check(patientssnid)){
				String message = "Errorrrr!!!!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("patientregistration.jsp").forward(request, response);
			}
			else {
			Patient newPatient = new Patient(patientssnid, patientname,patientage, dateofadmission, typeofroom,patientaddress, patientstate, patientcity,status);
		
			
			try 
			{
				
				int count = PatientRegistrationService.addPatient(newPatient,token);
				
				if (count==0)
				{
					String message = "Sorry customer not created please try again";
					request.setAttribute("message", message);
					request.getRequestDispatcher("patientregistration.jsp").forward(request, response);
				}
				else
				{
					String message = "Successfully Registered Patient";
					request.setAttribute("message", message);
					request.getRequestDispatcher("welcome.jsp").forward(request, response);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			
		}
	}
}


