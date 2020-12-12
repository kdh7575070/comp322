<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	    	<h4 > Search Movies to rate </h4> <br/>
		    <form action ="Searchresponse.jsp" method = "POST">
    		<div class="container"  style = "color:#2C3E50 !important; font-size: 1.3em;">
	    		<h4 > Search by title </h4> <br/>
		       Title: <input type = "text" name = "title" />
		       <br /> <br />
		    	<button class="btn btn-primary btn-xl" id="sendMessageButton" type="submit">Submit</button>
		    </form>
		    	<br /> <br /> <br />
		    <form action ="SearchresponseC.jsp" method = "POST">
		    	<h4 > Search by options </h4> <br/>
				<select name="type">
			       	<option value = "" selected>select option</option> 	
			    		<option value = "KnuMovieDB Original">KnuMovieDB Original</option>
			    		<option value = "Movie">Movie</option>
			    		<option value = "Series">Series</option>
			    </select>
		    	<select name="genre">
		    		<option value = "" selected>select option</option>
		    		<option value = "Action">Action</option>
		    		<option value = "Comedy">Comedy</option>
		    		<option value = "Drama">Drama</option>
		    		<option value = "Horror">Horror</option>
		    		<option value = "Romance">Romance</option>
		    		<option value = "Sci-fi">Sci-fi</option>
		    	</select>
		    	<select name="version">
		    		<option value = "" selected>select option</option>
		    		<option value = "RU">RU</option>
		    		<option value = "KR">KR</option>
		    		<option value = "JP">JP</option>
		    		<option value = "GE">GE</option>
		    		<option value = "EG">EG</option>
		    		<option value = "FI">FI</option>
		    		<option value = "AL">AL</option>
		    		<option value = "CO">CO-fi</option>
		    		<option value = "US">US</option>
		    		<option value = "CN">CN</option>
		    		<option value = "SE">SE</option>
		    		<option value = "PH">PH</option>
		    		<option value = "ID">ID</option>
		    		<option value = "AF">AF</option>
		    		<option value = "PT">PT</option>
		    	</select>
		    	<br /> <br />
		    	<button class="btn btn-primary btn-xl" id="sendMessageButton" type="submit">Submit</button>
		    </div>
		</form>
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