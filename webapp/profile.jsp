<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
   <head>
      <meta charset="utf-8">
      <title>iOffer</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>iOffer</title>
	  <link rel="stylesheet" href="css/userStyles.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/homeStyles.css">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9/crypto-js.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/sha256.js"></script>
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
      <div class="container">
         <div class="row" style="margin-top:60px;">
            <h1>User Profile</h1>
            
            <nav id="selectOpt">
               <ul>
                  <li><a href="#settings">Settings</a></li>
				  <li><a href="#advertisements">Advertisements</a></li>
				  <li><a href="#password">Change Password</a></li>
               </ul>
               <a class="btn btn-lg btn-warning pull-right" id="btnLogout" style="margin-left: 6px">Log out</a>
               <a class="btn btn-lg btn-warning pull-right" href="add.jsp">Post Item</a>
            </nav>
            		

            <div id="content">
               
               <div id="settings">
                  <p>Information:</p>
                  <p class="setting" id="useremail"><span>E-mail Address <img src="images/edit.png" alt="*Edit*"></span></p>
                  <p class="setting" id="username"><span>Name <img src="images/edit.png" alt="*Edit*"></span></p>
                  <p class="setting"><span>Profile Status <img src="images/edit.png" alt="*Edit*"></span> Public</p>
               </div>
			   <div id="advertisements" class="hidden">
                  <p>Your listed Ads:</p>
               </div>
               <div id="password" class="hidden">
                 
                 <input id="oldPass" type="text"/>
                 <p>Old Password</p>
                 
                 <input id="newPass" type="text"/>
                 <p>New Password</p>
                 
                 <input id="newPassConfirm" type="text"/>
                 <p>Confirm Password</p>
                 <p id="error" style="color:red"></p>
                 <a class="btn btn-lg btn-warning pull-right" id="btnchangePass">Change Password</a>
               </div>
            </div>
            <!-- @end #content -->
         </div>
      </div>
      
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
      <script type="text/javascript">
      var cookieEmail;
	  $(document).ready(function() {
	   		// https://developer.mozilla.org/en-US/docs/Web/API/Document/cookie
	   	    
	   	    // Check if cookie called "email" exists. If it does, change the reg/login button to a different one
	   	    if (document.cookie.indexOf('email') > -1 ) {
	   		  	cookieEmail = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
	   			// Update the time before the user is logged out
	   			var date = new Date();
	   		 	var cEmail = "email=" + cookieEmail + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	   		 	document.cookie = cEmail;
	   		  	
	   		 	$("#btnLoginText").text(cookieEmail);
	     	    $("#btnLogin").prop("href", "profile.jsp");
	   		  	$.getJSON("webapi/user/" + cookieEmail, function (data){
	   		  		// Update html controls to reflect the user details
	   		  		$("#useremail").append(data.email);
	   		  		$("#username").append(data.name);
	   		  	});
	   		  	
	   		 	$.getJSON("webapi/product/author/"+cookieEmail, function (data){
	   		 		console.log(data);
	   		 		for(i = 0; i<data.length; i++){
	   		 			$("#advertisements").append('<hr><p style="font-size: 1.5em; color: black;">'+ data[i].name +'<a href="item.jsp?id=' + data[i].productId + '" class="btn btn-lg btn-success pull-right">More Info..</a></p>');
	   		 		}
		        });
	   		}else{
	   			window.location.assign("login.jsp");
	   		}
	   	 });
	  
	  $("#btnLogout").click(function (e){
		  // Delete cookie
		  document.cookie = 'email' + 
    '=; expires=Thu, 01-Jan-70 00:00:01 GMT; path=/';
		  window.location.assign("index.jsp");
	  });
	  
	  function encryptPass(pass){
	 	  var encryptedPass = CryptoJS.SHA256(pass);
	 	  
    	  return encryptedPass.toString();
      }
	  
	  $("#btnchangePass").click(function (e){
		  var oldPass = $("#oldPass").val();
		  var newPass = $("#newPass").val();
		  var newPassConfirm = $("#newPassConfirm").val();
		  
		  if(oldPass.length > 0 && newPass.length > 0 && newPassConfirm.length > 0){
			  console.log(oldPass);
			  console.log(newPass);
			  console.log(newPassConfirm);
			  
			  var encryptedoldPass = encryptPass(oldPass);
			  var encryptednewPass = encryptPass(newPass);
			  
			  if(newPass == newPassConfirm){
				  var data = {
							'email' : cookieEmail,
							'oldPass' : encryptedoldPass,
							'newPass' : encryptednewPass
						};
				  
				  console.log(data);
				  
				  $.ajax({
			  			url: "webapi/user",
			  			type: "PUT",
						data: JSON.stringify(data),
						contentType: "application/json",
						success: function (response) {
			  				if(response === "true"){
			  					$("#error").css({ 'color': 'green' });
			  					$("#error").text("Success!");
			  		        }else{
			  		        	// TELL USER THAT DETAILS ARE INCORRECT
			  		        	$("#error").css({ 'color': 'red' });
			  		        	$("#error").text("* The old pasword doesn't match.");
			  		        }
			  		    }
			  		});
			  }else{
				  $("#error").css({ 'color': 'red' });
				  $("#error").text("* The new paswords don't match.");
			  }
		  } 
	  });
	
	  	 $(function(){
           $('#selectOpt ul li a').on('click', function(e){
             e.preventDefault();
             var newcontent = $(this).attr('href');
             
             $('#selectOpt ul li a').removeClass('sel');
             $(this).addClass('sel');
             
             $('#content div').each(function(){
               if(!$(this).hasClass('hidden')) {
				$(this).addClass('hidden'); 
				
				}
				
             });
             
             $(newcontent).removeClass('hidden');
           });
         });
      </script>
   </body>
</html>