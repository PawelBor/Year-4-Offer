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
      <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
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
                           <select class="form-control"  id="regCounty" name="county" placeholder="County" required="">
								<option value="" disabled selected hidden>Please Choose County...</option>
								<option value="antrim">Antrim</option>
								<option value="armagh">Armagh</option>
								<option value="carlow">Carlow</option>
								<option value="cavan">Cavan</option>
								<option value="clare">Clare</option>
								<option value="cork">Cork</option>
								<option value="derry">Derry</option>
								<option value="donegal">Donegal</option>
								<option value="down">Down</option>
								<option value="dublin">Dublin</option>
								<option value="fermanagh">Fermanagh</option>
								<option value="galway">Galway</option>
								<option value="kerry">Kerry</option>
								<option value="kildare">Kildare</option>
								<option value="kilkenny">Kilkenny</option>
								<option value="laois">Laois</option>
								<option value="leitrim">Leitrim</option>
								<option value="limerick">Limerick</option>
								<option value="longford">Longford</option>
								<option value="louth">Louth</option>
								<option value="mayo">Mayo</option>
								<option value="meath">Meath</option>
								<option value="monaghan">Monaghan</option>
								<option value="offaly">Offaly</option>
								<option value="roscommon">Roscommon</option>
								<option value="sligo">Sligo</option>
								<option value="tipperary">Tipperary</option>
								<option value="tyrone">Tyrone</option>
								<option value="waterford">Waterford</option>
								<option value="westmeath">Westmeath</option>
								<option value="wexford">Wexford</option>
								<option value="wicklow">Wicklow</option>
							 </select>
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
      	  function encryptPass(pass, key){
    	 	  var encryptedPass = CryptoJS.AES.encrypt(pass, key);
    	  
	    	  return encryptedPass.toString();
	      }
      	  
      	  //function ussing the cryptoJS lib to decrypt password
      	  function decryptPass(pass, key){
  	 	  var decryptedPass = CryptoJS.AES.decrypt(pass, key);
  	  
	    	  return decryptedPass.toString(CryptoJS.enc.Utf8);
	      }
      
	      $("#loginButton").click(function(e){
	    	  var email = $("#loginEmail").val(); 
	    	  
	  		//vars to take password from html form and encrypt it.	  		
	  		var passKey = $("#loginPassword").val();
	  		var encryptedPass = encryptPass(passKey, passKey);
	  		
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
	  		        	var cCounty;
	  		        	if(loginRemember){
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 720) + ";path=/";
	  		        		var cCounty = "county=" + response + ";expires=" + date.setHours(date.getHours() + 720) + ";path=/";
	  		        	}else{
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	  		        		var cCounty = "county=" + response + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	  		        	}
	  		        	
	  		        	document.cookie = cEmail;
	  		        	document.cookie = cCounty;
	  		        	
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
	    	var county = $("#regCounty").val();
	  		//vars to take password from html form and encrypt it.	  		
	  		var passKey = $("#regPassword").val();
	  		var encryptedPass = encryptPass(passKey, passKey);
	  		
	  		$.ajax({
	  			url: "webapi/user",
	  			type: "POST",
	  			data: {email: email, password: encryptedPass, name: name, county: county},
	  			success: function(response){
	  				console.log("server returned: " + response);
	  				if(response){
	  		        	// GO TO HOMEPAGE & SAVE COOKIES
	  					var date = new Date();
	  		        	var cEmail;
	  		        	var cCounty;
	  		        	if(loginRemember){
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 720) + ";path=/";
	  		        		var cCounty = "county=" + response + ";expires=" + date.setHours(date.getHours() + 720) + ";path=/";
	  		        	}else{
	  		        		var cEmail = "email=" + email + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	  		        		var cCounty = "county=" + response + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	  		        	}
	  		        	
	  		        	document.cookie = cEmail;
	  		        	document.cookie = cCounty;
	  		        	
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