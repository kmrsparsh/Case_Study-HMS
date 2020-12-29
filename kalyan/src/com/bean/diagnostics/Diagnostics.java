package com.bean.diagnostics;

import java.util.Arrays;
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

import com.bean.PatientUpdateDao.CheckPatientUpdateDao;


@WebServlet("/Diagnostics")
public class Diagnostics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String patientid = request.getParameter("patientid");
		String dianame = "hello";
		//System.out.println(patientid);
		Diagnosticsjava[] alldiagnostics = new Diagnosticsjava[0];
		Diagnosticsjava[] search = new Diagnosticsjava[0];
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
			
			//CREATING NEW TEMPORARY TABLE
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection newtemp = DriverManager.getConnection(url,uname,password);
				String newtempsql = "TRUNCATE TABLE output";
				PreparedStatement newtempstat = newtemp.prepareStatement(newtempsql);
				
				int newtempset = newtempstat.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			// copying files from diagnostics table to temp table
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection copytemp = DriverManager.getConnection(url,uname,password);
				String copytempsql = "INSERT INTO output SELECT * FROM diagnostics";
				PreparedStatement copytempstat = copytemp.prepareStatement(copytempsql);
				
				int copytempset = copytempstat.executeUpdate();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			//try catch for storing diagnostics table values in a variable
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn = DriverManager.getConnection(url,uname,password);
				String diagnosticssql = "select * from output";
				PreparedStatement diagnosticsstat = conn.prepareStatement(diagnosticssql);
				
				ResultSet diagnosticsset = diagnosticsstat.executeQuery();
				
				while(diagnosticsset.next()) {
					
					dianame = diagnosticsset.getString("diagnostics");
					int amo = diagnosticsset.getInt("amount");
					alldiagnostics=Arrays.copyOf(alldiagnostics,alldiagnostics.length+1);
					alldiagnostics[alldiagnostics.length-1] = new Diagnosticsjava(dianame,amo);
				}
				//System.out.println(alldiagnostics[0].getDiagnostic());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
			
			
			
			
			
			//try catch for getting patient diagnostics
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn = DriverManager.getConnection(url,uname,password);
				String sql = "select * from patientdiagnostics where pId=?";
				PreparedStatement pcs = conn.prepareStatement(sql);
				pcs.setString(1, patientid);
				ResultSet srs = pcs.executeQuery();
				
				while(srs.next()) {
					
					dianame = srs.getString("diagnostic");
					int amo = srs.getInt("amount");
					search=Arrays.copyOf(search,search.length+1);
					search[search.length-1] = new Diagnosticsjava(dianame,amo);
				}
				//System.out.println(search[0].getDiagnostic());
				
				int nullverifier = 0;
				for(int i = 0;i<search.length;i++) {
					if(search[i].getDiagnostic().equals("none")) {
						nullverifier = 1;
						
					}
				}
				
				if(nullverifier==1) {
					
					//System.out.println("entered null verifier");
				}
				if(nullverifier==0) { //if block open
					
					for(int searchcount = 0;searchcount<search.length;searchcount++) {//1st for block open
						
						for(int diagnosticscount=0;diagnosticscount<alldiagnostics.length;diagnosticscount++) {//2nd for block open
						
						if(search[searchcount].getDiagnostic().equals(alldiagnostics[diagnosticscount].getDiagnostic())) {
							try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection deletecon = DriverManager.getConnection(url,uname,password);
							
							
							String delete = "DELETE FROM output WHERE diagnostics = ?";
							
							PreparedStatement deletest = deletecon.prepareStatement(delete);
							deletest.setString(1,search[searchcount].getDiagnostic());
							
							int insertedRowCount = deletest.executeUpdate();
							}catch(Exception e)
							{
								e.printStackTrace();
							}
							
						}//if block close
						
						}//2nd for block close
						
					}//1st for block close
					
				}//if block close
			
				
				
				
			}//try close
			catch(Exception e)
			{
				e.printStackTrace();
			}//catch close
				
			String message = "medicine";
			String disabled = "disabled";
			request.setAttribute("myparam", message);
			request.setAttribute("patientid", patientid);
			request.setAttribute("disabled", disabled);
			request.getRequestDispatcher("diagnostics.jsp").forward(request, response);
		}//if close
			else {//else open
				String message = "Given Patient ID is wrong ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("diagnostics.jsp").forward(request, response);
			}//else close

		}//post method close
	}//class block close
