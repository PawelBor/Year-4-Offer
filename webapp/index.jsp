<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
   <head>
      <meta charset="utf-8">
      <title>iOffer</title>
      <meta name="description" content="iOffer.">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	  <script type='text/javascript' src='js/jquery-3.1.1.min.js'></script>
      <link rel="stylesheet" href="css/homeStyles.css">
   </head>
   <body background="images/blurBCK.jpg">
      <body>
         <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="index.jsp"><i class="ion-ios-analytics-outline"></i><span class="glyphicon glyphicon-home"></span> iOffer</a>
            </div>
            <div class="navbar-collapse collapse" id="bs-navbar">
                <ul class="nav navbar-nav">
                    <li>
                        <a class="page-scroll" href="location.jsp">Map</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="search.jsp">Search Product</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" id="btnLogin" href="login.jsp">
                            <p id="btnLoginText"><span class="glyphicon glyphicon-user"></span> Register / Sign IN</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
         <header id="first">
            <div class="header-content">
               <div class="inner">
                  <h1 class="L4G-Big-Text"><span class="highlight"><span class="iHighlight">i</span>OFFER</span></h1>
                  <h4>Anything can be found.</h4>
                  <div>
                     <div class="input-group">
                        <div class="input-group-addon">
                           <span class="glyphicon glyphicon-search"></span> 
                        </div>
                        <input class="form-control" id="searchText" name="searchText" type="text" required/>
                     </div>
                     <hr>
                     <button id="searchbtn" class="btn btn-warning btn-xl" style="width: 25%; background-color: #FFA500;">Search</button>
                  </div>
                  <hr>
               </div>
               <!-- Categories-->
               <div class="container" id="selFadeIn">
                  <div class="row"  id="rowSlideLeft">
                     <a href="javascript:category('electronics');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Electronics</h3>
                              <p>Computers, Tablets, Mobile Phones & Communication...</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('entertainment');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Entertainment</h3>
                              <p>Concert & Festival Tickets, Movies, Games & Musical Instruments...</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('leisure');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Leisure</h3>
                              <p>Sport Related Equipment & Any non-Compulsory Activities...</p>
                           </div>
                        </div>
                     </a>
                  </div>
                  <div class="row" id="rowSlideRight">
                     <a href="javascript:category('automotive');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Automotive</h3>
                              <p>Anything Related to or concerned with Motor Vehicles...</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('food');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Food</h3>
                              <p>Any possible deals that may be offered...</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('other');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Other</h3>
                              <p>Anything that may derive from standard categories...</p>
                           </div>
                        </div>
                     </a>
                  </div>
               </div>
            </div>
         </header>
         <div class="container">
         <hr>
         <!-- Footer -->
         <footer>
            <div class="row">
               <div class="col-lg-12">
                  <p style="float: left;">Copyright &copy; iOffer 2017</p>
               </div>
               <div class="contact">
                  <a href="http://www.github.com/PawelBor/Year-4-Offer"><img src="./images/git.png" height="50" width="50">   Contact us</a>
               </div>
            </div>
         </footer>
      </div>
         <script>
	        $(document).ready(function() {
	     		// https://developer.mozilla.org/en-US/docs/Web/API/Document/cookie
	     	    
	     	    // Check if cookie called "email" exists. If it does, change the reg/login button to a different one
	     	    if (document.cookie.indexOf('email') > -1 ) {
	     		    var cookieEmail = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
	     			// Update the time before the user is logged out
	     			var date = new Date();
		   		 	var cEmail = "email=" + cookieEmail + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
		   		 	document.cookie = cEmail;
		     	    // Change button value/href
		     	    $("#btnLoginText").text(cookieEmail);
		     	    $("#btnLogin").prop("href", "profile.jsp");
	     		}
	     	});
	        
	        document.onkeydown = function(e){
	        	if (e.keyCode == '13') {
	        		if($("#searchText").val().length > 0){
	        			$("#searchbtn").click();
	        		}
	        	}
	        };
         
            $(function(){  // $(document).ready shorthand
              $('#selFadeIn').hide().fadeIn('slow');
            });
			$(function(){
              $("#rowSlideLeft").animate({left: '0'}, "slow");
			  $("#rowSlideRight").animate({right: '0'}, "slow");
            });

         	function category(category){
               window.location.href = 'search.jsp?param='+category;
            }
         	
         	$("#searchbtn").click(function (e) {
         		window.location.href = 'search.jsp?param='+ $("#searchText").val();
         	});
         </script>
   </body>
</html>