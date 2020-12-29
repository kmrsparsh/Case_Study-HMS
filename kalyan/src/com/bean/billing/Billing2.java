package com.bean.billing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Billing2")
public class Billing2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String pssnid = request.getParameter("ssnid");
		//System.out.println(pssnid);
	
		String url ="jdbc:mysql://127.0.0.1:3306/login_details";
		String username = "root";
		String password = "Mehar@144";
	
		String discharge ="discharge";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String sql = "UPDATE patient SET pstatus = ? WHERE pSSNID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,discharge);
			st.setString(2,pssnid);
			int insertedRowCount = st.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String message = "Patient Successfully Discharged";
		request.setAttribute("message", message);
		request.getRequestDispatcher("billing.jsp").forward(request, response);
		
	}
	
	
	
	}


