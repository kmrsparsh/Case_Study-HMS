<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
    <head>
        <style>
.alert {
  padding: 20px;
  background-color: red;
  color: white;
  margin-bottom: 15px;
}

/* The close button */
.closebtn {
  margin-left: 15px;
  color: white;
  font-weight: bold;
  float: right;
  font-size: 22px;
  line-height: 20px;
  cursor: pointer;
  transition: 0.3s;
}

/* When moving the mouse over the close button */
.closebtn:hover {
  color: black;
}

.error-message {
	color: red;
}
/* Add a black background color to the top navigation */
body{
    background-color:lavenderblush;
    overflow: auto;
}
.topnav {
  background-color: lavender;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: indigo;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
  background-color: lightsteelblue;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: whitesmoke;
  color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
  background-color: slateblue;
  color: white;
}
/* The dropdown container */
.dropdown {
  float: left;
  overflow: hidden;
  background-color: lavenderblush;
}

/* Dropdown button */
.dropdown .dropbtn {
  font-size: 16px;
  border: none;
  outline: none;
  color: indigo;
  padding: 14px 16px;
  background-color: lightsteelblue;
  font-family: inherit; /* Important for vertical align on mobile phones */
  margin: 0; /* Important for vertical align on mobile phones */
}

/* Add a red background color to navbar links on hover */
.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: whitesmoke;
}

/* Dropdown content (hidden by default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  float: none;
  color: indigo;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}
.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}

#table_details {
  font-family: "Times New Roman", Times, serif;
  border-collapse: collapse;
  width: 60%;
  margin-top: 30px;
}

#table_details td, #table_details th {
  border: 1px solid #ddd;
  padding: 8px;
}

#table_details tr:nth-child(even){background-color: #f2f2f2;}

#table_details tr:hover {background-color: #ddd;}

#table_details th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: rgb(69, 106, 155);
  color: white;
}

        </style>
        <title>Diagnostics</title>

        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css" integrity="sha384-cg6SkqEOCV1NbJoCu11+bm0NvBRc8IYLRGXkmNrqUBfTjmMYwNKPWBTIKyw9mHNJ" crossorigin="anonymous">

        
    </head>
     <%
     
     if(session.getAttribute("username")==null){
    	 response.sendRedirect("Login.jsp");
     }   
   
    
 %>
    <body>
      <div>
        <div class="topnav">
            <center><h1>Hospital Management System</h1></center>
            <a href="welcome.jsp">Home</a>
            <div class="dropdown">
                <button class="dropbtn">Patient
                  <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                  <a href="patientregistration.jsp">Patient Registration</a>
                  <a href="patientupdation.jsp">Patient Updation</a>
                  <a href="deletepatient.jsp">Patient Deletion</a>
                  <a href="patientview.jsp">Patient View</a>
                  <a href="PatientDetailsServlet">View All</a>
            </div>
            </div>
            <a href="pharmacy.jsp">Pharmacy</a>
            <a class = active href="diagnostics.jsp">Diagnostics</a>
            <a href="billing.jsp">Patient Billing</a>
            <a href="Logout">Logout</a>
        </div>
        
       <div style = "color: Red;"><p><b> ${ error } </b></p></div>
      
        <center>
        <h2>Diagnostics</h2>
        </center>
        <form  action="Diagnostics" method="POST">
        <div>
          <center>
          <table id="table_details">
            <thead>
            <tr>
              <th>Patient ID</th>
              <th>Name</th>
              <th>Age</th>
              <th>Address</th>
              <th>DOJ</th>
              <th>Type of Room</th>
            </tr>
            </thead>
          
            <tbody>

            <tr>
            <td><input name="patientid" type="number" min="0" required value=${ pid }></td>
            <td><input name="patientname" type="text" onkeypress="return false;" value=${ pname }></td>
            <td><input name="patientage" type="number" onkeypress="return false;" value=${ page }></td>
            <td><input name="patientadress" type="text" onkeypress="return false;" value=${ paddress } ></td>
            <td><input name="patientdateofadmission" type="text" onkeypress="return false;" value=${ pdateofadmission }></td>
            <td><input name="patienttypeofbed" type="text" onkeypress="return false;" value=${ ptypeofroom }></td>
            </tr>
            </tbody>
          </table>
        </center>
          <button type="submit" class="pure-button pure-button-primary" style="margin-left: 175px;">Search</button>
        </div>   <% 
        if("medicine".equals(request.getAttribute("myparam"))){
     
    	
        Object patientId =request.getAttribute("patientid");
        String patientIds = patientId.toString();
		String url ="jdbc:mysql://127.0.0.1:3306/login_details";
			String username = "root";
			String password = "Mehar@144";
        Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement pc = connection.prepareStatement("Select * from patientdiagnostics where pId=?") ;
        pc.setString(1, patientIds);
        ResultSet resultset = 
        		pc.executeQuery();
		
        //out.println("resultset");
        //System.out.println(resultset);
       // String id = resultset.getString(2);
    	
         out.println("<div>");
        
			
            out.println("<center>");
            out.println("<h2>Diagnostics Completed</h2>");
            out.println("<table id='table_details'>");
            out.println("<thead>");
            out.println("<tr>");
             out.println("<th>Diagnostics</th>");
              out.println("<th>Amount</th>");  
           out.println(" </tr>");
            out.println("</thead>");
            out.println("<tbody>");
            %>
            
            <% while(resultset.next()){ %>
         
              <% out.println(" <tr>"); %>
              
                <td><%= resultset.getString(3) %></td>
                <td><%= resultset.getInt(4) %></td>
               <%
               out.println(" </tr>");%>
               
               <% } %> 
            </tbody>
         
        </table>
        </div>
      </form>
    </div>
   <br>
      <div>
       <center>
          <form method='POST' action = 'Diagnostics2'><input style = 'display: none' type='text' id='patid' name='patid'  value=${ pid }><button type='submit'name='issuemedicine' class='pure-button pure-button-primary' >Issue Medicines</button></form>
        </center>
      </div>
     
}
     <% } %>
          <% 
          if("medicine".equals(request.getAttribute("myparam"))){
            String urls ="jdbc:mysql://127.0.0.1:3306/login_details";
  			String usname = "root";
  			String pass = "Mehar@144";
            Connection conn = DriverManager.getConnection(urls,usname,pass);

            Statement st = conn.createStatement() ;
            ResultSet rs = 
            		st.executeQuery("select * from output") ; 
        %>
    <div>
        <center>
        <h2>Diagnostics Available</h2>
          <table id="table_details">
            <thead>
            <tr>
              <th>Diagnostic</th>
              <th>Amount</th>
              <th>Add Diagnostics</th>
            </tr>
            </thead>
              <tbody>
            <% while(rs.next()){ %>
                <tr>
                    <td><%= rs.getString(2) %></td>
                    <td><%= rs.getInt(3) %></td>
                 
                    <td><form method="POST" action = "Diagnostics3">
                <input type="number" id="id" name="id" value=${ pid }			 readonly style="display: none;" >
                <input type="number" id="amount" name="amount" value = <%= rs.getInt(3) %> readonly style="display: none;" >
               
                <button type="submit" id="diagnosticname" name="diagnosticname" style="width: 100%;" ${ disabled } value=<%= rs.getString(2) %>>Add</button>
              </form></td>
                </tr>
     		<% } %>
            </tbody>
        </table>
        <form method="POST" action = "diagnostics.jsp">
        <button type="submit" id="complete" name="complete" class="pure-button pure-button-primary">Update</button>
        </form>
        </center>
    </div>
   <% } %> 
          
    </body>
</html>