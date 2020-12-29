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
        </style>
        <title>DeletePatient</title>

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
            <a href="welcome.jsp">Home</a>
            <div class="dropdown">
                <button class="dropbtn">Patient
                  <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                  <a href="patientregistration.jsp">Patient Registration</a>
                  <a href="patientupdation.jsp">Patient Updation</a>
                  <a class="active" href="deletepatient.jsp">Patient Deletion</a>
                  <a href="patientview.jsp">Patient View</a>
                  <a href="PatientDetailsServlet">View All Patients</a>
            </div>
            </div>
           <a href="pharmacy.jsp">Pharmacy</a>
            <a href="diagnostics.jsp">Diagnostics</a>
            <a href="billing.jsp">Patient Billing</a>
            <a href="Logout">Logout</a>
        </div>

<div style = "color: Red;"><p><b> ${ message } </b></p></div>
      
        <div>
        <center>
            <br>
            <h2>Delete Patient</h2>
            <form action="PatientReDeleteServlet" method="POST" class="pure-form pure-form-aligned">  
                <fieldset>
                    <div class="pure-control-group">
                        <label for="aligned-name">Patient Id</label>
                        <input type="number" id="aligned-name" name="patientid" placeholder= "Patient Id" value=${ pid } required onkeypress="return false;"/>
                    </div>
                        <button type="submit" class="pure-button pure-button-primary">Submit</button>
                    </div>
                </fieldset>
            </form>
        </center>
        </div>
        <div>
        <center>
            <form method = "POST" action = "PatientReDeleteServlet" class="pure-form pure-form-aligned">  
                <fieldset>
                	<div class="pure-control-group" style="display: none;">
                      <label for="aligned-name">Patient Id</label>
                      <input type="text" id="aligned-name" name="patientid" placeholder= "Patient Id" readonly required onkeypress="return false;" value=${ pid }>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-name">Patient Name</label>
                        <input type="text" id="aligned-name" name="patientname" placeholder="Patient Name" readonly required onkeypress="return false;" value=${ pname }  >
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-name">Patient Age</label>
                        <input type="number" id="aligned-name" name="patientage" readonly pattern="[0-9]{3}" placeholder="Patient Age" required onkeypress="return false;" value=${ page } >
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-date">Date of Admission</label>
                        <input style = "width: 14%;" type="text" name="dateofadmission" readonly id = "aligned-date" required onkeypress="return false;" value=${ pdateofadmission } >
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-admissiontype">Type of Bed</label>
                        <input type="text" id="aligned-admissiontype" name="typeofroom" readonly placeholder="Type of Bed" required onkeypress="return false;" value=${ ptypeofroom } >
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-address">Patient Address</label>
                        <input type="text" id="aligned-address" name="patientaddress" readonly placeholder="Patient Address" required onkeypress="return false;" value= ${ paddress } >
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-state">Patient State</label>
                        <input type="text" id="aligned-state" name="patientstate" readonly placeholder="Patient State" required onkeypress="return false;" value=${ pstate } >
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-city">Patient City</label>
                         <input type="text" id="aligned-city" name="patientcity" readonly placeholder="Patient City" required onkeypress="return false;" value=${ pcity } >
                    </div>
                    <div class="pure-controls">
                        <button type="submit" class="pure-button pure-button-primary"  style="margin-right: 175px;">Delete</button>
                    </div>
                </fieldset>
            </form>
        </center>
        </div>
        <div style = "color: Red; font-size: 20px"><p><b> ${ message } </b></p></div>
    </body>
</html>