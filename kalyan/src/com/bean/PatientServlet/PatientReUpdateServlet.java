package com.bean.PatientServlet;

import com.bean.Patient.PatientUpdate;
import com.bean.service.PatientRegistrationService;

import com.bean.PatientUpdateDao.PatientUpdateDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PatientReUpdateServlet")


public class PatientReUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//Getting values from web page
		String patientid = request.getParameter("patientid");
		String patientname=request.getParameter("patientname");
		Integer patientage = Integer.parseInt(request.getParameter("patientage"));
		String dateofadmission = request.getParameter("dateofadmission");
		String typeofroom = request.getParameter("typeofroom");
		String patientaddress=request.getParameter("patientaddress");
		String patientstate = request.getParameter("patientstate");
		String patientcity = request.getParameter("patientcity");
		
		//creating patient update object
		
		PatientUpdate newupdate = new PatientUpdate(patientname,patientage,dateofadmission,typeofroom,patientaddress,patientstate,patientcity);
		
		
		
		try 
		{
			
			String count = PatientUpdateDao.PatientUpdateDao(newupdate,patientid);
			
			if (count=="" || count.isEmpty())
			{
				String message = "Sorry Patient Details Not Updated Please Try Again";
				request.setAttribute("message", message);
				request.getRequestDispatcher("patientreupdation.jsp").forward(request, response);
			}
			else
			{
				String message = "Patient Details Successfully Updated";
				request.setAttribute("message", message);
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
