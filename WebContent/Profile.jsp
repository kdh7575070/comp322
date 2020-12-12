<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.AccountService" %>
<%@ page import="java.io.PrintWriter" %>

<html lang="en">
<head>
    <meta charset="EUC-KR">
    <title>COMP322 : Introduction to Databases</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" />
</head>
<body id="page-top">

    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="Main.jsp">KNU MOVIE DB</a>
            <button class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars"></i>
            </button>
        </div>
    </nav>
    
	<!-- Main area -->
    <header class="masthead bg-primary">
    	<div class="container d-flex flex-column font-weight-bold bg-primary"  >
    	<h4> Your profile </h4> <br/>
	    <form action ="Updateresponse.jsp" method = "POST">
		    <h5> Unfilled information will remain null</h5> 
		    <h5 style="color:red;"> (You should fill your name and phone number) </h5> <br />
		    <div class="container"  style = "color:#2C3E50 !important; font-size: 1.3em;">
		    	First Name: <input type = "text" name = "fn" />
		    	<br /> <br />
		    	Last Name: <input type = "text" name = "ln" />
		    	<br /> <br />
		    	Phone Number(01012345678): <input type = "text" name = "pn" />
		    	<br /> <br />
		    	Birth date(2000-01-01): <input type = "text" name = "bd" />
		    	<br /> <br />
		    	Sex:
		    	<select name="sx">
		    		<option value = "" selected>select option</option>
		    		<option value = "F">Female</option>
		    		<option value = "M">Male</option>
		    	</select>
		    	<br /> <br />
		    	Address: <input type = "text" name = "ad" />
		    	<br /> <br />
		    	Job: 
		    	<select name="jb">
		       		<option value = "" selected>select option</option> 	
		    		<option value = "1">academic/educator</option>
		    		<option value = "2">artist</option>
		    		<option value = "3">clerical/admin</option>
		    		<option value = "4">college/grad student</option>
		    		<option value = "5">customer service</option>
		    		<option value = "6">doctor/health care</option>
		    		<option value = "7">executive/managerial</option>
		    		<option value = "8">farmer</option>
		    		<option value = "9">homemaker</option>
		    		<option value = "10">K-12 student</option>
		    		<option value = "11">lawyer</option>
		    		<option value = "12">programmer</option>
		    		<option value = "13">retired</option>
		    		<option value = "14">sales/marketing</option>
		    		<option value = "15">scientist</option>
		    		<option value = "16">self-employed</option>
		    		<option value = "17">technician/engineer</option>
		    		<option value = "18">tradesman/craftsman</option>
		    		<option value = "19">unemployed</option>
		    		<option value = "20">writer</option>
		    	</select>
		    	<br/> <br />
			<button class="btn btn-primary btn-xl" id="sendMessageButton" type="submit">Submit</button>
			</div>
		</form>
		<br /> <br />
		
		<h4> Change Password </h4> <br/>
		<form action ="ChangePassword.jsp" method = "POST">
		    <div class="container"  style = "color:#2C3E50 !important; font-size: 1.3em;">
		    	New password: <input type = "text" name = "pw" />
		    	<br/> <br />
				<button class="btn btn-primary btn-xl" id="sendMessageButton" type="submit">Submit</button>
		    </div>
		</form>
		<br /> <br />
		
		
		<h4> Watched Movies (Rated only) </h4> <br/>
		<form action ="ShowMyList.jsp" value="Submit">
		    <div class="container"  style = "color:#2C3E50 !important; font-size: 1.3em;">
			<button class="btn btn-primary btn-xl" id="sendMessageButton" type="submit">Show</button>
			</div>
		</form>
		<br /> <br />
		
		<h4> Delete Account </h4> <br/>
		<form action ="DeleteAccount.jsp" value="Submit">
			 <div class="container"  style = "color:#2C3E50 !important; font-size: 1.3em;">
			<button class="btn btn-primary btn-xl" style = "color:red;" id="sendMessageButton" type="submit">DELETE ACCOUNT</button>
			</div>
		</form>
		<br /> <br />
		<% 
			boolean is_admin = false;
			is_admin = AccountService.Is_admin((String)session.getAttribute("User_id"));
			if (is_admin) {
		%>
			<div class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" style="font-size: 1.5em;" href="Admin.jsp">Admin page</a></div>
		<%
			}
		%>
		</div>
	</header>
	
	<!-- Footer -->
	<footer class="footer text-center">
        <div class="container">
            <div class="row">
                <!-- Footer Location-->
                <div class="col-lg-4 mb-5 mb-lg-0">
                    <h4 class="text-uppercase mb-4">KNU, Daegu <br /> South Korea</h4>
                    <p class="lead mb-0">
			
                    </p>
                </div>
                <!-- Footer Social Icons-->
                <div class="col-lg-4 mb-5 mb-lg-0">
                    <h4 class="text-uppercase mb-4">Around the Web</h4>
                    <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-facebook-f"></i></a>
                    <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-twitter"></i></a>
                    <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-linkedin-in"></i></a>
                    <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-dribbble"></i></a>
                </div>
                <!-- Footer About Text-->
                <div class="col-lg-4">
                    <h4 class="text-uppercase mb-4">About US</h4>
                    <p class="lead mb-0">
                        WOO Jongbin / KANG Taeha <br />
                        From KNU EE <br />
                        <a href="https://github.com/kdh7575070/comp322">GIT US</a>
                        .
                    </p>
                </div>
            </div>
        </div>
    </footer>
    
    <!-- Copyright Section-->
    <div class="copyright py-4 text-center text-white">
        <div class="container"><small>Copyright Â© team14 2020</small></div>
    </div>
    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes)-->
    <div class="scroll-to-top d-lg-none position-fixed">
        <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top"><i class="fa fa-chevron-up"></i></a>
    </div>
</body>
</html>