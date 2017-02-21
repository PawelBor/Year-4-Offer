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
            <div class="col-sm-6">
               <form method="post" class="form-signin">
                  <div>
                  </div>
                  <div class="" id="collapseLogin">
                     <div class="card card-block">
                        <h2 class="form-signin-heading">Please login</h2>
                        <input type="email" class="form-control" id="loginEmail" name="email" placeholder="Email Address" required="" autofocus="" />
                        <input type="password" class="form-control" id="loginPassword" name="password" placeholder="Password" required=""/>  
                        <button class="btn btn-lg btn-primary btn-block" id="loginButton" name="send">Login</button> 
                     </div>
                  </div>
               </form>
               <form action="register" method="post" class="form-signin">
                  <div align="center">
                     <button class="btn btn-primary" id="btnRegister" hidden type="button" data-toggle="collapse" data-target="#collapseRegister" aria-controls="collapseRegister">
                     REGISTER
                     </button>
                  </div>
                  <div class="collapse" id="collapseRegister">
                     <div class="card card-block">
                        <h2 class="form-register-heading">Please Register</h2>
                        <input type="email" class="form-control" name="email" placeholder="Email Address" required="" autofocus="" />
                        <input type="password" class="form-control" name="password" placeholder="Password" required=""/>
                        <input type="text" class="form-control" name="name" placeholder="Name" required="" autofocus="" />
                        <input type="text" class="form-control" name="county" placeholder="County" required=""/>
                        <button class="btn btn-lg btn-primary btn-block" name="send" type="submit">Register</button> 
                     </div>
                  </div>
               </form>
            </div>
            <!--END_col-md-6-->
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
	  		// currently only returns false
	  		
	  		//vars to take password from html form and encrypt it.
	  		var passKey = $("#loginPassword").val();
	  		var encryptedPass = encryptPass(passkey, passKey);
	  		
	  		$.ajax({
	  			url: '/service/login',
	  			type: 'POST',
	  			data: {email: $("#loginEmail").val(), password: encryptedpass},
	  			success: function(response){
	  				console.log("server returned: " + response + " current password: " + $("#loginPassword").val());
	  				if(response === "success"){
	  		        	// GO TO HOMEPAGE & SAVE COOKIES
	  					window.location.assign("index.jsp");
	  					console.log("Right credentials");
	  		        }else{
	  		        	// TELL USER THAT DETAILS ARE INCORRECT
	  		        	console.log("Wrong credentials");
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