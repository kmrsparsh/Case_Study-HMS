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
        <title>PatientRegister</title>

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
                  <a class="active" href="patientregistration.jsp">Patient Registration</a>
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

        
        
        <div>
        <center>
            <br>
            <h2>Patient Register</h2>
            <form action="PatientRegistration" method="POST" class="pure-form pure-form-aligned">  
                <fieldset>
                    <div class="pure-control-group">
                        <label for="aligned-name">Patient SSNID</label>
                        <input type="text" id="aligned-name" name="patientssnid" title="9 Digit Number only" placeholder="Patient SSNID" required
                        pattern="[0-9]{9}"/>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-name">Patient Name</label>
                        <input type="text" id="aligned-name" name="patientname" placeholder="Patient Name" pattern="[A-Za-z]{}" title="Alphabets only" required/>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-name">Patient Age</label>
                        <input type="number" style="width: 14%;" id="aligned-name" name="patientage" placeholder="Patient Age" min="0" max="999" required/>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-date">Date of Admission</label>
                        <input style = "width: 14%;" type="date" name="dateofadmission" id = "aligned-date" placeholder="dd-mm-yyyy" value="" min="1997-01-01" max="2030-12-31">
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-admissiontype">Type of Bed</label>
                        <select id="aligned-admissiontype" name="typeofroom" style="width: 14%;" required>
                            <option>General Ward</option>
                            <option>Semi Sharing</option>
                            <option>Single Room</option>
                        </select>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-address">Patient Address</label>
                        <input type="text" id="aligned-address" name="patientaddress" placeholder="Patient Address" required/>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-state">Patient State</label>
                        <select id="aligned-state" name="patientstate" style="width: 14%;" required>
                            <option>Andhra Pradesh</option>
                            <option>Kerala</option>
                            <option>Tamil Nadu</option>
                        </select>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-city">Patient City</label>
                        <select id="aligned-city" name="patientcity" style="width: 14%;" required>
                            <option>Vizag</option>
                            <option>Coimbatore</option>
                            <option>Chennai</option>
                        </select>
                    </div>
                    <div class="pure-controls">
                        <button type="submit" class="pure-button pure-button-primary">Submit</button> <button type="reset" class="pure-button pure-button-primary">Reset</button>
                    </div>
                </fieldset>
            </form>
        </center>
        </div>
        <div style = "color: Red; font-size: 20px"><p><b> ${ message } </b></p></div>
    </body>
</html>