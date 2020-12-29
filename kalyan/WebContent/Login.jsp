<!DOCTYPE html>
<html>
    <head>
        <style>
.alert {
  padding: 20px;
  background-color: #f44336; /* Red */
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
        </style>
        <title>Login</title>

        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css" integrity="sha384-cg6SkqEOCV1NbJoCu11+bm0NvBRc8IYLRGXkmNrqUBfTjmMYwNKPWBTIKyw9mHNJ" crossorigin="anonymous">
    </head>

    <body>
        <div class="topnav">
            <center><h1>Hospital Management System</h1></center>
            <a class="active" href="Login.jsp">Login</a>
        </div>

        
        <div>
        <center>
            <h1><b>Welcome to the Hospital Management System</b></h1>
            <br>
            <form action="Login" method="post" class="pure-form pure-form-aligned">  
                <fieldset>
                    <div class="pure-control-group">
                        <label for="aligned-name">Username</label>
                        <input type="text" id="aligned-name" name="userName" placeholder="Username" required/>
                    </div>
                    <div class="pure-control-group">
                        <label for="aligned-password">Password</label>
                        <input type="password" id="aligned-password" name="userPassword" placeholder="Password" required/>
                    </div>
                    <div class="pure-controls">
                        <button type="submit" class="pure-button pure-button-primary">Submit</button>
                    </div>
                </fieldset>
            </form>
        </center>
        </div>
        <div style = "color: Red; font-size: 35px"><p><b> ${ message } </b></p></div>
    </body>
</html>