<!DOCTYPE html>
<html>
    <head>
        <style>
.alert {
  padding: 20px;
  background-color: green;
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
        </style>
        <title>HomePage</title>

        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css" integrity="sha384-cg6SkqEOCV1NbJoCu11+bm0NvBRc8IYLRGXkmNrqUBfTjmMYwNKPWBTIKyw9mHNJ" crossorigin="anonymous">
    </head>
     <%
     
     if(session.getAttribute("username")==null){
    	 response.sendRedirect("Login.jsp");
     }   
   
    
 %>
    <body>
        <div class="topnav">
            <center><h1>Hospital Management System</h1></center>
            <a class="active" href="welcome.jsp">Home</a>
            <div class="dropdown">
                <button class="dropbtn">Patient
                  <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                  <a href="patientregistration.jsp">Patient Registration</a>
                  <a href="patientupdation.jsp">Patient Updation</a>
                  <a href="deletepatient.jsp">Patient Deletion</a>
                  <a href="patientview.jsp">Patient View</a>
                  <a href="PatientDetailsServlet">View All Patients</a>
            </div>
            </div>
            <a href="pharmacy.jsp">Pharmacy</a>
            <a href="diagnostics.jsp">Diagnostics</a>
            <a href="billing.jsp">Patient Billing</a>
            <a href="Logout">Logout</a>
        </div>

       
<div style = "color: Green; font-size: 20px"><p><b> ${ message } </b></p></div>
                        

      
        <div>
        <center>
            <h1><b>Welcome to the Hospital Management System</b></h1>
        </center>
        </div>
    </body>
</html>