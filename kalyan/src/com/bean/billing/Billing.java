package com.bean.billing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.PatientUpdateDao.CheckPatientUpdateDao;


@WebServlet("/Billing")
public class Billing extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
			
			String patientid = request.getParameter("patientid");
			//System.out.println(patientid);
			int pidi = Integer.parseInt(patientid); 
			String pdateofadmission=null;
			String typeofroom = null;
			String url ="jdbc:mysql://127.0.0.1:3306/login_details";
			String username = "root";
			String password = "Mehar@144";
			String patientssnid = null;
			
			if(CheckPatientUpdateDao.check(patientid)) {
				
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(url,username,password);
					
					String sql = "select * from patient where pId=?";
					PreparedStatement pc = con.prepareStatement(sql);
					pc.setString(1, patientid);
					ResultSet sr = pc.executeQuery();
					if (sr.next()) {
						
						patientssnid = sr.getString("pSSNID");
						String patientname = sr.getString("pname");
						int age = sr.getInt("page");
						String date = sr.getString("pdateofAddmission");
						typeofroom = sr.getString("typeOfBed");
						String address = sr.getString("address");
						String state = sr.getString("state");
						String city = sr.getString("city");
						String status = sr.getString("pstatus");
						
						//System.out.println(city);
					
						//setting messages
						
						if(status.equals("discharge")) {
							String error = "Patient already discharged";
							request.setAttribute("error", error);
							request.getRequestDispatcher("billing.jsp").forward(request, response);
						}
						else {
						String pid = patientid;
						request.setAttribute("pid", pid);
						String pname = patientname;
						request.setAttribute("pname", pname);
						int page = age;
						request.setAttribute("page", page);
						pdateofadmission = date;
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

				}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
				
				
				
				
				
	
				
				
				
				
			//for date
				
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			//System.out.println("Current Date : " + dateFormat.format(date));
			
			String pdateofdischarge = dateFormat.format(date);
			    
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    String doj = pdateofadmission;

			    //convert String to LocalDate
			    LocalDate localDate1 = LocalDate.parse(doj, formatter);
			    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			   
			    //convert String to LocalDate
			    LocalDate localDate2 = LocalDate.parse(pdateofdischarge, format);
			    
			    Period period = Period.between(localDate1, localDate2);
			    
			  int  noofdays = period.getDays() + 1;
			  
			  
			  //for room bill
			  
			  int roomcost = 0;
			  if(typeofroom.equals("General Ward")){
				  
				  roomcost = 2000;
				  
			  }
			  else if(typeofroom.equals("Semi Sharing")) {
				  roomcost = 4000;
			  }
			  else {
				 roomcost = 8000;
			  }
			  
			  // pharmacy bill
			  
			  Connection connection;
			  int pharmacybilling = 0;
			try {
				connection = DriverManager.getConnection(url,username,password);
				PreparedStatement pc = connection.prepareStatement("Select * from patient_pharmacy where pId=?") ;
			    pc.setString(1, patientid);
			    ResultSet resultset = pc.executeQuery();
			    while(resultset.next()) {
			    	pharmacybilling = pharmacybilling + resultset.getInt(5);
			    }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		     
			
			// diagnosticbilling
			
			int diagnosticbilling = 0;
			try {
			Connection conn = DriverManager.getConnection(url,username,password);
			PreparedStatement pcs = conn.prepareStatement("select * from patientdiagnostics where pId=?") ;
			pcs.setInt(1, pidi);
         ResultSet rs = pcs.executeQuery();
         
         while(rs.next()){
         	diagnosticbilling = diagnosticbilling + rs.getInt(4);
         }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		     
			
			// total cost
			
			int totalcost = (noofdays*roomcost)+ pharmacybilling + diagnosticbilling;
			  
			request.setAttribute("pid", patientid); 
			request.setAttribute("totalcost", totalcost); 
			request.setAttribute("pssnid", patientssnid);
			request.setAttribute("diagnosticbilling", diagnosticbilling);
			request.setAttribute("pharmacybilling", pharmacybilling);
			request.setAttribute("roomcost", roomcost);
			request.setAttribute("pdateofdischarge", pdateofdischarge);
			request.setAttribute("totaldays", noofdays);
			String message = "medicine";
			String disabled = "disabled";
			request.setAttribute("myparam", message);
			request.setAttribute("patientid", patientid);
			request.setAttribute("disabled", disabled);
			request.getRequestDispatcher("billing.jsp").forward(request, response);
		}
			else {
				String message = "Given Patient ID is wrong ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("billing.jsp").forward(request, response);
			}

	}
	}


