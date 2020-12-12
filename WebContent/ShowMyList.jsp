<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
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
	    	<h4 > Watched Movies </h4> <br/>
    		<div class="container"  style = "color:#2C3E50 !important; font-size: 1.3em;">
				<%
					ArrayList<String> movie_rate_list = new ArrayList<String>();
					movie_rate_list = AccountService.check_my_ratinglist((String)session.getAttribute("User_id"));
					System.out.println(movie_rate_list.size());
				
					if (movie_rate_list.size() > 0) {
							for(String s :movie_rate_list) {
						%>
								<div><%=s%></div>
						<%
							}
					} else {
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('Haven't watched any movie')");
						script.println("location.href = 'Profile.jsp'");
						script.println("</script>");		
					}
				%>
			</div>
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