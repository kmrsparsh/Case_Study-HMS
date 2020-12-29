package com.bean.patientdetailsservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.cj.xdevapi.Statement;
import java.sql.ResultSet;


@WebServlet("/PatientDetailsServlet")


public class PatientDetailsServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		String url ="jdbc:mysql://127.0.0.1:3306/login_details";
		String username = "root";
		String password = "Mehar@144";
		String sql = "Select * from patient";
		
		out.print("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"    <head>\r\n" + 
				"        <style>\r\n" + 
				".alert {\r\n" + 
				"  padding: 20px;\r\n" + 
				"  background-color: green;\r\n" + 
				"  color: white;\r\n" + 
				"  margin-bottom: 15px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* The close button */\r\n" + 
				".closebtn {\r\n" + 
				"  margin-left: 15px;\r\n" + 
				"  color: white;\r\n" + 
				"  font-weight: bold;\r\n" + 
				"  float: right;\r\n" + 
				"  font-size: 22px;\r\n" + 
				"  line-height: 20px;\r\n" + 
				"  cursor: pointer;\r\n" + 
				"  transition: 0.3s;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#table_details {\r\n" + 
				"  font-family: \"Times New Roman\", Times, serif;\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"  width: 60%;\r\n" + 
				"  margin-top: 30px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#table_details td, #table_details th {\r\n" + 
				"  border: 1px solid #ddd;\r\n" + 
				"  padding: 8px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#table_details tr:nth-child(even){background-color: #f2f2f2;}\r\n" + 
				"\r\n" + 
				"#table_details tr:hover {background-color: #ddd;}\r\n" + 
				"\r\n" + 
				"#table_details th {\r\n" + 
				"  padding-top: 12px;\r\n" + 
				"  padding-bottom: 12px;\r\n" + 
				"  text-align: left;\r\n" + 
				"  background-color: rgb(69, 106, 155);\r\n" + 
				"  color: white;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* When moving the mouse over the close button */\r\n" + 
				".closebtn:hover {\r\n" + 
				"  color: black;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".error-message {\r\n" + 
				"	color: red;\r\n" + 
				"}\r\n" + 
				"/* Add a black background color to the top navigation */\r\n" + 
				"body{\r\n" + 
				"    background-color:lavenderblush;\r\n" + 
				"    overflow: auto;\r\n" + 
				"}\r\n" + 
				".topnav {\r\n" + 
				"  background-color: lavender;\r\n" + 
				"  overflow: hidden;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Style the links inside the navigation bar */\r\n" + 
				".topnav a {\r\n" + 
				"  float: left;\r\n" + 
				"  color: indigo;\r\n" + 
				"  text-align: center;\r\n" + 
				"  padding: 14px 16px;\r\n" + 
				"  text-decoration: none;\r\n" + 
				"  font-size: 17px;\r\n" + 
				"  background-color: lightsteelblue;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Change the color of links on hover */\r\n" + 
				".topnav a:hover {\r\n" + 
				"  background-color: whitesmoke;\r\n" + 
				"  color: black;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Add a color to the active/current link */\r\n" + 
				".topnav a.active {\r\n" + 
				"  background-color: slateblue;\r\n" + 
				"  color: white;\r\n" + 
				"}\r\n" + 
				"/* The dropdown container */\r\n" + 
				".dropdown {\r\n" + 
				"  float: left;\r\n" + 
				"  overflow: hidden;\r\n" + 
				"  background-color: lavenderblush;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Dropdown button */\r\n" + 
				".dropdown .dropbtn {\r\n" + 
				"  font-size: 16px;\r\n" + 
				"  border: none;\r\n" + 
				"  outline: none;\r\n" + 
				"  color: indigo;\r\n" + 
				"  padding: 14px 16px;\r\n" + 
				"  background-color: lightsteelblue;\r\n" + 
				"  font-family: inherit; /* Important for vertical align on mobile phones */\r\n" + 
				"  margin: 0; /* Important for vertical align on mobile phones */\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Add a red background color to navbar links on hover */\r\n" + 
				".navbar a:hover, .dropdown:hover .dropbtn {\r\n" + 
				"  background-color: whitesmoke;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Dropdown content (hidden by default) */\r\n" + 
				".dropdown-content {\r\n" + 
				"  display: none;\r\n" + 
				"  position: absolute;\r\n" + 
				"  background-color: #f9f9f9;\r\n" + 
				"  min-width: 160px;\r\n" + 
				"  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\r\n" + 
				"  z-index: 1;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"/* Links inside the dropdown */\r\n" + 
				".dropdown-content a {\r\n" + 
				"  float: none;\r\n" + 
				"  color: indigo;\r\n" + 
				"  padding: 12px 16px;\r\n" + 
				"  text-decoration: none;\r\n" + 
				"  display: block;\r\n" + 
				"  text-align: left;\r\n" + 
				"}\r\n" + 
				".dropdown-content a:hover {\r\n" + 
				"  background-color: #ddd;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".dropdown:hover .dropdown-content {\r\n" + 
				"  display: block;\r\n" + 
				"}\r\n" + 
				"        </style>\r\n" + 
				"        <title>HomePage</title>\r\n" + 
				"\r\n" + 
				"        <link rel=\"stylesheet\" href=\"https://unpkg.com/purecss@2.0.3/build/pure-min.css\" integrity=\"sha384-cg6SkqEOCV1NbJoCu11+bm0NvBRc8IYLRGXkmNrqUBfTjmMYwNKPWBTIKyw9mHNJ\" crossorigin=\"anonymous\">\r\n" + 
				"    </head>\r\n"  + 
				"    <body>\r\n" + 
				"        <div class=\"topnav\">\r\n" + 
				"            <center><h1>Hospital Management System</h1></center>\r\n" + 
				"            <a class=\"active\" href=\"welcome.jsp\">Home</a>\r\n" + 
				"            <div class=\"dropdown\">\r\n" + 
				"                <button class=\"dropbtn\">Patient\r\n" + 
				"                  <i class=\"fa fa-caret-down\"></i>\r\n" + 
				"                </button>\r\n" + 
				"                <div class=\"dropdown-content\">\r\n" + 
				"                  <a href=\"patientregistration.jsp\">Patient Registration</a>\r\n" + 
				"                  <a href=\"patientupdation.jsp\">Patient Updation</a>\r\n" + 
				"                  <a href=\"deletepatient.jsp\">Patient Deletion</a>\r\n" + 
				"                  <a href=\"patientview.jsp\">Patient View</a>\r\n" + 
				"                  <a href=\"PatientDetailsServlet\">View All Patients</a>\r\n" + 
				"            </div>\r\n" + 
				"            </div>\r\n" + 
				"            <a href=\"pharmacy.jsp\">Pharmacy</a>\r\n" + 
				"            <a href=\"diagnostics.jsp\">Diagnostics</a>\r\n" + 
				"            <a href=\"billing.jsp\">Patient Billing</a>\r\n" + 
				"            <a href=\"Logout\">Logout</a>\r\n" + 
				"        </div>\r\n" + 
				"\r\n" + 
				"       \r\n" + 
				"<div style = \"color: Green; font-size: 20px\"><p><b> </b></p></div>\r\n" + 
				"                        \r\n" + 
				"\r\n" + 
				"      \r\n" + 
				"        <div>\r\n" + 
				"        <center>\r\n" + 
				"            <h2><b>View Patients</b></h2>\r\n" + 
				"        </center>\r\n" + 
				"        </div>\r\n" + 
				"    </body>\r\n" + 
				"</html>");
		
		
		out.print("<center><table id='table_details' border='1'><tr><th>Patient Id</th><th>Name</th><th>Age</th><th>Address</th><th>DOJ</th><th>Type Of Room</th><tr>");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pc = con.prepareStatement(sql);
			ResultSet rs = pc.executeQuery();
			
			
			while(rs.next())
			{
				out.print("<tr><td>");
				out.println(rs.getString(2));
				out.print("</td>");
				
				out.print("<td>");
				out.println(rs.getString(3));
				out.print("</td>");
				
				out.print("<td>");
				out.println(rs.getInt(4));
				out.print("</td>");
				
				out.print("<td>");
				out.println(rs.getString(7));
				out.print("</td>");
				
				out.print("<td>");
				out.println(rs.getString(5));
				out.print("</td>");
				
				out.print("<td>");
				out.println(rs.getString(6));
				out.print("</td>");
				
				out.print("</tr>");
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		out.print("</table></center>");
		
		//out.print("<center>\r\n" + 
			//	"          <button type=\"submit\" class=\"pure-button pure-button-primary\" {{disabled}} >Issue Medicines</button>\r\n" + 
			//	"        </center>");
		
	}


}
