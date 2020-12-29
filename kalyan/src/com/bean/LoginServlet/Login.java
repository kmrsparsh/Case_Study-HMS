package com.bean.LoginServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.Cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.bean.LoginDao.LoginDao;
import com.bean.LoginDao.AddDateTime;

@WebServlet("/Login")
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		String name = request.getParameter("userName").toString();
		String pass = request.getParameter("userPassword").toString();
		 
	
		
		if(LoginDao.check(name, pass))
		{	
			HttpSession  session=request.getSession();
			session.setAttribute("username", name);
			String message = "Successfully Logged In";
			request.setAttribute("message", message);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			
			
			
			try {
				AddDateTime date = new AddDateTime(name);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		else
		{
			HttpSession  session=request.getSession(true);
			String message = "Error!!! Wrong Login Credentials Entered";
			request.setAttribute("message", message);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			
		}
		
		
		
		
	}

	
}
