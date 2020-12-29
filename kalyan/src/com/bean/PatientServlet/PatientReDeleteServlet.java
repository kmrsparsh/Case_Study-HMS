package com.bean.PatientServlet;



import com.bean.PatientDeleteDao.PatientDeleteDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/PatientReDeleteServlet")

public class PatientReDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
		
		//Getting values from web page
		String patientid = request.getParameter("patientid");
		
		
		try 
		{
			
			int count = PatientDeleteDao.PatientDeleteDao(patientid);
			
			if (count==0)
			{
				String message = "Sorry Patient Details Are Not Deleted Please Try Again";
				request.setAttribute("message", message);
				request.getRequestDispatcher("redeletepatient.jsp").forward(request, response);
			}
			else
			{
				String message = "Patient Details Successfully Deleted";
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
