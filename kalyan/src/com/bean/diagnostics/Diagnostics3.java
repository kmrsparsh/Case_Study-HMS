package com.bean.diagnostics;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Diagnostics3")
public class Diagnostics3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String pId = request.getParameter("id");
		int patientid = Integer.valueOf(pId);
		Integer amount = Integer.parseInt(request.getParameter("amount"));
		String diagnosticname = request.getParameter("diagnosticname");
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		String dianame = "hello";
		//System.out.println(patientid);
		Diagnosticsjava[] alldiagnostics = new Diagnosticsjava[0];
		Diagnosticsjava[] search = new Diagnosticsjava[0];
		Diagnosticsjava[] search2 = new Diagnosticsjava[0];
		int testnone = 0;
		int updateresultset=0;
		String none = "none";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url,uname,password);
			String sql = "select * from patientdiagnostics where pId=?";
			PreparedStatement pcs = conn.prepareStatement(sql);
			pcs.setInt(1,patientid);
			ResultSet srs = pcs.executeQuery();

			while(srs.next()) {
				
				dianame = srs.getString("diagnostic");
				int amo = srs.getInt("amount");
				search=Arrays.copyOf(search,search.length+1);
				search[search.length-1] = new Diagnosticsjava(dianame,amo);
			}
			//System.out.println(search[0].getDiagnostic());
			
			for(int i = 0;i<search.length;i++) {
				
				if(search[i].getDiagnostic().equals("none")) {
					testnone = 1;
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch close
			try {
			if(testnone==1) {
				Class.forName("com.mysql.jdbc.Driver");
				
				//System.out.println("update");
				Connection updateconnection = DriverManager.getConnection(url,uname,password);
				String update = "UPDATE patientdiagnostics SET diagnostic = ?,amount = ? WHERE pId = ? AND diagnostic = ?";
				
				PreparedStatement updatestatement = updateconnection.prepareStatement(update);
				
				updatestatement.setString(1,diagnosticname);
				updatestatement.setInt(2, amount);
				updatestatement.setInt(3, patientid);
				updatestatement.setString(4,none);
				updateresultset = updatestatement.executeUpdate();
				//System.out.println(updateresultset);
			}
			}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}//catch close
			
			try {
			if(testnone==0) {
				//System.out.println("INSERT");
				Connection insertconnection = DriverManager.getConnection(url,uname,password);
				String insert = "INSERT INTO patientdiagnostics(pId,diagnostic, amount) VALUES(?,?,?)";
				
				PreparedStatement insertstatement = insertconnection.prepareStatement(insert);
				insertstatement.setInt(1, patientid);
				insertstatement.setString(2, diagnosticname);
				insertstatement.setInt(3, amount);
				int insertresultset = insertstatement.executeUpdate();
				
			}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}//catch close
			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			String sql = "select * from patient where pId=?";
			PreparedStatement pc = con.prepareStatement(sql);
			pc.setString(1, pId);
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
				
				
				String pid = pId;
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
			//	System.out.println("entered");
		
		
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
			pcs.setInt(1, patientid);
			ResultSet srs = pcs.executeQuery();
			
			while(srs.next()) {
				
				dianame = srs.getString("diagnostic");
				int amo = srs.getInt("amount");
				search2=Arrays.copyOf(search2,search2.length+1);
				search2[search2.length-1] = new Diagnosticsjava(dianame,amo);
			}
			//System.out.println(search[0].getDiagnostic());
			
			for(int searchcount = 0;searchcount<search2.length;searchcount++) {
				
				for(int diagnosticscount=0;diagnosticscount<alldiagnostics.length;diagnosticscount++) {
					
					if(search2[searchcount].getDiagnostic().equals(alldiagnostics[diagnosticscount].getDiagnostic())) {
						
							Class.forName("com.mysql.jdbc.Driver");
							Connection deletecon = DriverManager.getConnection(url,uname,password);
							
							
							String delete = "DELETE FROM output WHERE diagnostics = ?";
							
							PreparedStatement deletest = deletecon.prepareStatement(delete);
							deletest.setString(1,search2[searchcount].getDiagnostic());
							
							int insertedRowCount = deletest.executeUpdate();
							
							
					}
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch close
		
		String message = "medicine";
		
		request.setAttribute("myparam", message);
		request.setAttribute("patientid", patientid);
		request.getRequestDispatcher("diagnostics.jsp").forward(request, response);
		
	}
}