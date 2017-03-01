<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <title>Login/Register</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
      <link rel="stylesheet" href="css/homeStyles.css">
      <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
      <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9/crypto-js.js"</script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/sha256.js"></script>
   </head>
   <body>
      <div class="container">
         <div class="row">
            <div class="inner">
               <h1 class="login-header"><span class="iHighlight">i</span>OFFER</h1>
               <h4>Anything can be found.</h4>
               <hr>
            </div>
            <div id="genForm">
               <p align="center" > Would you like to...</p>
               <div align="center" style="width: 200px; margin: 0 auto;">
                  <button style="float: left;" class="btn btn-lg btn-primary" id="showLogin">Sign In</button>
                  <button style="float: right;" class="btn btn-lg btn-primary" id="showRegister">Sign Up</button>
                  <br>
                  <br>
               </div>
               <hr style="width: 300px;">
               <div class="container login">
                  <div id="collapseLogin" class="form-signin">
                     <div id="loginForm" class="card card-block">
                        <div class="card card-block">
                           <h2 class="form-signin-heading">Please Login</h2>
                           <input type="email" class="form-control" id="loginEmail" name="email" placeholder="Email Address" required="" autofocus="" />
                           <input type="password" class="form-control" id="loginPassword" name="password" placeholder="Password" required=""/>  
                           <button class="btn btn-lg btn-success btn-block" id="loginButton" name="send">Login</button> 
                           <div class="checkbox">
							  <label><input type="checkbox" id="loginRemember" value="">Remember Me</label>
						   </div>
                           <div class="error"><p id="errorMessage"></p></div>
                        </div>
                     </div>
                  </div>
                  <div class="form-signin">
                     <div class="collapse" id="collapseRegister">
                        <div class="card card-block">
                           <h2 class="form-register-heading">Please Register</h2>
                           <input type="email" class="form-control" id="regEmail" name="email" placeholder="Email Address" required="" autofocus="" />
                           <input type="password" class="form-control" id="regPassword" name="password" placeholder="Password" required=""/>
                           <input type="text" class="form-control" id="regName" name="name" placeholder="Name" required="" autofocus="" />
                           
							 <br>
                           <button class="btn btn-lg btn-success btn-block" name="send" id="regButton">Register</button>
                           <div align="center">
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <!--END_div genForm-->
         </div>
         <!--END_row-->
      </div>
      <!--END_container-->
      <div class="container">
         <hr>
         <footer>
            <div class="row">
               <div class="col-lg-12">
                  <p>Copyright &copy; iOffer 2017</p>
               </div>
            </div>
         </footer>
         <!--END_footer-->
      </div>
      <!-- END_container -->
      <script type="text/javascript">
	      $(document).ready(function() {
	   		// https://developer.mozilla.org/en-US/docs/Web/API/Document/cookie
	   	    
	   	    // Check if cookie called "email" exists. If it does, change the reg/login button to a different one
	   	    if (document.cookie.indexOf('email') > -1 ) {
	   		  var cookieEmail = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
		     	window.location.assign("profile.jsp");
	   		}
	   	});
/*Page Login/Register Switch*/

		var showRegister = document.getElementById("showRegister");
         $("#showRegister").click(function(){
             $("#collapseLogin").hide();
             $("#collapseRegister").show();
         
         });
         var showLogin = document.getElementById("showLogin");
         $("#showLogin").click(function(){
             $("#collapseLogin").show();
             $("#collapseRegister").hide();;
         });



/*Encryption*/
      	  //function using the cryptoJs lib to encrypt password
      	  function encryptPass(pass){
    	 	  var encryptedPass = CryptoJS.SHA256(pass);
    	 	  
	    	  return encryptedPass.toString();
	      }
      
	      $("#loginButton").click(function(e){
	    	  var email = $("#loginEmail").val(); 
	    	  
	  		//vars to take password from html form and encrypt it.	  		
	  		var passKey = $("#loginPassword").val();
	  		var encryptedPass = encryptPass(passKey);
	  		
	  		$.ajax({
	  			url: '/service/login',
	  			type: 'POST',
	  			data: {email: email, password: encryptedPass},
	  			success: function(response){
	  				console.log("server returned: " + response + " current password: " + encryptedPass);
	  				if(response !== ""){
	  		        	// GO TO HOMEPAGE & SAVE COOKIES
	  					var date = new Date();
	  		        	var cEmail;
	  		        	if(loginRemember){
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 720) + ";path=/";
	  		        	}else{
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	  		        	}
	  		        	
	  		        	document.cookie = cEmail;
	  		        	
	  		        	window.location.assign("index.jsp");
	  					console.log("Right credentials");
	  		        }else{
	  		        	// TELL USER THAT DETAILS ARE INCORRECT
	  		        	console.log("Wrong credentials");
	  		        	$("#errorMessage").text("- Wrong credentials");
	  		        }
	  		    }
	  		});
	  	});
	      
	      $("#regButton").click(function(e){
	    	var email = $("#regEmail").val(); 
	    	var name = $("#regName").val();
	  		//vars to take password from html form and encrypt it.	  		
	  		var passKey = $("#regPassword").val();
	  		var encryptedPass = encryptPass(passKey);
	  		
	  		$.ajax({
	  			url: "webapi/user",
	  			type: "POST",
	  			data: {email: email, password: encryptedPass, name: name},
	  			success: function(response){
	  				console.log("server returned: " + response);
	  				if(response){
	  		        	// GO TO HOMEPAGE & SAVE COOKIES
	  					var date = new Date();
	  		        	var cEmail;
	  		        	if(loginRemember){
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 720) + ";path=/";
	  		        	}else{
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	  		        	}
	  		        	
	  		        	document.cookie = cEmail;
	  		        	
	  		        	window.location.assign("index.jsp");
	  					console.log("Register success");
	  		        }else{
	  		        	// TELL USER THAT DETAILS ARE INCORRECT
	  		        	console.log("Register failure");
	  		        }
	  		    }
	  		});
	  	});
  		
      
		var btnRegister = document.getElementById("btnRegister");
		$("#btnRegister").click(function(){
		    $("#btnRegister").hide();
		});
      </script>
   </body>
</html>