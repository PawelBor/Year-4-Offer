<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
   <head>
      <meta charset="utf-8">
      <title>iOffer</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Profile</title>
      <link rel="stylesheet" type="text/css" href="css/userStyles.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/homeStyles.css">
   </head>
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
                        <a class="page-scroll" id="btnLogin" href="login.jsp"><span class="glyphicon glyphicon-user"></span><p id="btnLoginText">Register / Sign IN</p></a>
                     </li>
                  </ul>
               </div>
            </div>
         </nav>
      <div class="container">
         <div class="row">
		 <div class="profile-header-content">
            <div id="userphoto"><img src="images/avatar.png" alt="default avatar"></div>
            <h1>User Profile</h1>
            <nav id="profiletabs">
               <ul class="clearfix">
                  <li><a href="#activity" class="sel">Activity</a></li>
                  <li><a href="#settings">Settings</a></li>
				  <li><a href="#advertisements">Advertisements</a></li>
               </ul>
            </nav>
			</div>
            <div id="content">
               <div id="activity" >
                  <p>Your advertisements:</p>
                  <p class="activity">Act1</p>
                  <p class="activity">Act2</p>
                  <p class="activity">Act3</p>
                  <p class="activity">Act4<strong>test</strong> test2</p>
                  <p class="activity">Act5</p>
               </div>
               <div id="settings" class="hidden">
                  <p>settings:</p>
                  <p class="setting"><span>E-mail Address <img src="images/edit.png" alt="*Edit*"></span> testuseremail@gmail.com</p>
                  <p class="setting"><span>Phone Number <img src="images/edit.png" alt="*Edit*"></span> 089-111-1111</p>
                  <p class="setting"><span>Profile Status <img src="images/edit.png" alt="*Edit*"></span> Public</p>


               </div>
			   <div id="advertisements" class="hidden">
                  <p>Your listed Ads:</p>
                  <p class="advertisement">WHeels</p>
                  <p class="advertisement">Guitar</p>
                  <p class="advertisement">4BD House</p>
                  <p class="advertisement">Audi A4</p>
                  <p class="advertisement">Iphone</p>
               </div>
            </div>
            <!-- @end #content -->
         </div>
      </div>
      <script type="text/javascript">
	      $(document).ready(function() {
	   		// https://developer.mozilla.org/en-US/docs/Web/API/Document/cookie
	   	    
	   	    // Check if cookie called "email" exists. If it does, change the reg/login button to a different one
	   	    if (document.cookie.indexOf('email') > -1 ) {
	   		  	var cookieEmail = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
	   		 	$("#btnLoginText").text(cookieEmail);
	     	    $("#btnLogin").prop("href", "profile.jsp");
	   		  	$.getJSON("webapi/user/" + cookieEmail, function (data){
	   		  		console.log(data);
	   		  		// Update html controls to reflect the user details
	   		  	});
	   		}else{
	   			window.location.assign("login.jsp");
	   		}
	   	 });
      
         $(function(){
           $('#profiletabs ul li a').on('click', function(e){
             e.preventDefault();
             var newcontent = $(this).attr('href');
             
             $('#profiletabs ul li a').removeClass('sel');
             $(this).addClass('sel');
             
             $('#content div').each(function(){
               if(!$(this).hasClass('hidden')) { $(this).addClass('hidden'); }
             });
             
             $(newcontent).removeClass('hidden');
           });
         });
      </script>
   </body>
</html>