package com.bean.pharmacy;

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

import com.bean.Patient.Medicine;

@WebServlet("/Pharmacy3")
public class Pharmacy3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values from web page
		String pId = request.getParameter("id");
		int patientid = Integer.valueOf(pId);
		Integer rate = Integer.parseInt(request.getParameter("rate"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		String medname = request.getParameter("medicinename");
		int nullverifier = 0;
		
		String url = "jdbc:mysql://127.0.0.1:3306/login_details";
		String uname ="root";
		String password = "Mehar@144";
		
		if(quantity>0) {
			
			//System.out.println("read quantity value");
			
			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			System.out.println(con);
			String patientpharmacy = "select * from patient_pharmacy where pId=?";
			PreparedStatement pc = con.prepareStatement(patientpharmacy);
			pc.setString(1, pId);
			ResultSet sr = pc.executeQuery();
			Medicine[] patient_medicines = new Medicine[0];
			while(sr.next()) {
				System.out.println("while loop");
				String Medicine_name = sr.getString("Medicine_name");
				int Usedquantity = sr.getInt("Usedquantity");
				int RatePerMed = sr.getInt("RatePerMed");
				int Totalcost = sr.getInt("Totalcost");
				patient_medicines=Arrays.copyOf(patient_medicines,patient_medicines.length+1);
				patient_medicines[patient_medicines.length-1] = new Medicine(Medicine_name,Usedquantity,RatePerMed,Totalcost);
			}
			//System.out.println(patient_medicines[0].getMedicine_name());
			for(int i = 0;i<patient_medicines.length;i++) {
				if(patient_medicines[i].getMedicine_name().equals("none")) {
					nullverifier = 1;
					
				}
			}
			//System.out.println(nullverifier);
			//this if success
			if(nullverifier==1) {
				String update = "UPDATE patient_pharmacy SET Medicine_name = ?,Usedquantity = ?,RatePerMed = ?,Totalcost = ? WHERE pId = ?";
				Connection updateconnection = DriverManager.getConnection(url,uname,password);
				PreparedStatement updatestatement = updateconnection.prepareStatement(update);
				updatestatement.setString(1, medname);
				updatestatement.setInt(2, 1);
				updatestatement.setInt(3, rate);
				updatestatement.setInt(4, rate);
				
				updatestatement.setString(5, pId);
				int updateresultset = updatestatement.executeUpdate();
			}
			if(nullverifier ==0) {
				int y = 0;
				int equal = 0;
				for(int i = 0;i<patient_medicines.length;i++) {
					//System.out.println(patient_medicines[i].getTotalcost());
					if(patient_medicines[i].getMedicine_name().equals(medname)) {
						equal = 1;
						y=i;
					}
				}
				if(equal==1) {
						int Total_cost = patient_medicines[y].getTotalcost() + rate;
						patient_medicines[y].setTotalcost(Total_cost);
						int updatedquantity = patient_medicines[y].getUsedquantity() + 1;
						patient_medicines[y].setUsedquantity(updatedquantity);
						
						String update = "UPDATE patient_pharmacy SET Usedquantity = ?,RatePerMed = ?,Totalcost = ? WHERE pId = ? AND Medicine_name=?";
						Connection updateconnection = DriverManager.getConnection(url,uname,password);
						PreparedStatement updatestatement = updateconnection.prepareStatement(update);
						
						updatestatement.setInt(1, patient_medicines[y].getUsedquantity());
						updatestatement.setInt(2, patient_medicines[y].getRatePerMed());
						updatestatement.setInt(3, patient_medicines[y].getTotalcost());
						
						updatestatement.setString(4, pId);
						updatestatement.setNString(5, medname);
						int updateresultset = updatestatement.executeUpdate();
						
					}
					
				if(equal==0) {
						String add = "INSERT INTO patient_pharmacy(pId,Medicine_name, Usedquantity, RatePerMed, Totalcost) VALUES(?,?,?,?,?)";
						Connection updateconnection = DriverManager.getConnection(url,uname,password);
						PreparedStatement addstatement = updateconnection.prepareStatement(add);
						addstatement.setString(1, pId);
						addstatement.setString(2, medname);
						addstatement.setInt(3, 1);
						addstatement.setInt(4, rate);
						addstatement.setInt(5, rate);
						int updateresultset = addstatement.executeUpdate();
					

				}
			}
				
			
			
			
				int tquantity = quantity-1;
			String updatepharmacy = "UPDATE pharmacy SET Total_quantity = ? WHERE Medicine_name = ?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection updateconnectionph = DriverManager.getConnection(url,uname,password);
			PreparedStatement updatestatements = updateconnectionph.prepareStatement(updatepharmacy);
			updatestatements.setInt(1, tquantity);
			updatestatements.setString(2, medname);
			int updateresultsets = updatestatements.executeUpdate();
			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cons = DriverManager.getConnection(url,uname,password);
				
				String sql = "select * from patient where pId=?";
				PreparedStatement pcs = cons.prepareStatement(sql);
				pcs.setInt(1, patientid);
				ResultSet srs = pcs.executeQuery();
				if (srs.next()) {
					
					String patientssnid = srs.getString("pSSNID");
					String patientname = srs.getString("pname");
					int age = srs.getInt("page");
					String date = srs.getString("pdateofAddmission");
					String typeofroom = srs.getString("typeOfBed");
					String address = srs.getString("address");
					String state = srs.getString("state");
					String city = srs.getString("city");
					
					//System.out.println(city);
				
					//setting messages
					
					
					int pid = patientid;
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
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else {
			String error = "Sorry, Quantity not available";
			request.setAttribute("error", error);
			
		
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,uname,password);
				
				String sql = "select * from patient where pId=?";
				PreparedStatement pc = con.prepareStatement(sql);
				pc.setInt(1, patientid);
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
					
					
					int pid = patientid;
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
			
					
			String message = "medicine";
			request.setAttribute("myparam", message);
			request.setAttribute("patientid", patientid);
			
		}
		
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,password);
			
			String sql = "select * from patient where pId=?";
			PreparedStatement pc = con.prepareStatement(sql);
			pc.setInt(1, patientid);
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
				
				
				int pid = patientid;
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
		
				
		String message = "medicine";
		request.setAttribute("myparam", message);
		request.setAttribute("patientid", patientid);
		
	
		
		request.getRequestDispatcher("pharmacy.jsp").forward(request, response);
		
		
		
	}

}
