<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
   <head>
      <meta charset="utf-8">
      <title>iOffer</title>
      <meta name="description" content="iOffer.">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                        <a class="page-scroll" href="locationPage.html">Map</a>
                     </li>
                     <li>
                        <a class="page-scroll" href="profile.jsp">Profile</a>
                     </li>
                     <li>
                        <a class="page-scroll" href="add.jsp">testing stuff</a>
                     </li>
                     <li>
                        <a class="page-scroll" href="search.jsp">SHOP test</a>
                     </li>
                  </ul>
                  <ul class="nav navbar-nav navbar-right">
                     <li>
                        <a class="page-scroll" href="login.jsp"><span class="glyphicon glyphicon-user"></span> Register / Sign IN</a>
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
                  <form action="searchByName" method="post">
                     <div class="input-group">
                        <div class="input-group-addon">
                           <span class="glyphicon glyphicon-search"></span> 
                        </div>
                        <input class="form-control" id="searchText" name="searchText" type="text" required/>
                     </div>
                     <hr>
                     <button type="submit" name="send" class="btn btn-warning btn-xl" style="width: 25%; background-color: #FFA500;">Search</button>
                  </form>
                  <hr>
               </div>
               <!--<div>
                  <a href="*" class="btn btn-primary btn-xl floating-box">Electronics</a>
                  <a href="*" class="btn btn-primary btn-xl floating-box">Entertainment</a>
                  <a href="#" class="btn btn-primary btn-xl floating-box">Leisure</a>
                  <a href="#" class="btn btn-primary btn-xl floating-box">Automotive</a>
                  <a href="#" class="btn btn-primary btn-xl floating-box">Food</a>
                  <a href="#" class="btn btn-primary btn-xl floating-box">Other</a>
                  </div> -->
               <!-- NEW BAR-->
               <div class="container" id="selFadeIn">
                  <div class="row"  id="rowSlideLeft">
                     <a href="javascript:category('electronics');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Electronics</h3>
                              <p>Parapgraph.........to be done....</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('entertainment');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Entertainment</h3>
                              <p>Parapgraph.........to be done....</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('leisure');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Leisure</h3>
                              <p>Parapgraph.........to be done....</p>
                           </div>
                        </div>
                     </a>
                  </div>
                  <div class="row" id="rowSlideRight">
                     <a href="javascript:category('automotive');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Automotive</h3>
                              <p>Parapgraph.........to be done....</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('food');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Food</h3>
                              <p>Parapgraph.........to be done....</p>
                           </div>
                        </div>
                     </a>
                     <a href="javascript:category('other');">
                        <div class="col-sm-4">
                           <div class="tile category">
                              <h3 class="title">Other</h3>
                              <p>Parapgraph.........to be done....e</p>
                           </div>
                        </div>
                     </a>
                  </div>
               </div>
            </div>
         </header>
         <script>
	         $(document).ready(function() {
	     		// https://developer.mozilla.org/en-US/docs/Web/API/Document/cookie
	     	    var cookieEmail = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
	     	    var cookieCounty = document.cookie.replace(/(?:(?:^|.*;\s*)county\s*\=\s*([^;]*).*$)|^.*$/, "$1");
	     	    console.log(cookieEmail);
	     	    console.log(cookieCounty)
	     	});
         
            $(function(){  // $(document).ready shorthand
              $('#selFadeIn').hide().fadeIn('slow');
            });
			$(function(){
              $("#rowSlideLeft").animate({left: '0'}, "slow");
			  $("#rowSlideRight").animate({right: '0'}, "slow");
            });

         function category(x){
               window.location.href = 'search.jsp?category='+x;
            }
			
            
         </script>
   </body>
</html>