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

import com.bean.PatientUpdateDao.CheckPatientUpdateDao;

@WebServlet("/Pharmacy2")
public class Pharmacy2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
    		String uname ="root";
    		String password = "Mehar@144";
    		HttpSession session = request.getSession();
    		
    		String patientid = request.getParameter("patid");
    		int pId = Integer.valueOf(patientid);
    		//System.out.println(patientid);

    			
    			try {
    				Class.forName("com.mysql.jdbc.Driver");
    				Connection con = DriverManager.getConnection(url,uname,password);
    				
    				String sql = "select * from patient where pId=?";
    				PreparedStatement pc = con.prepareStatement(sql);
    				pc.setInt(1, pId);
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
    					
    				//	System.out.println(city);
    				
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
    	
    	request.setAttribute("myparam", message);
    	request.setAttribute("patientid", patientid);
    	
    	request.getRequestDispatcher("pharmacy.jsp").forward(request, response);
    }
    	

    	}


