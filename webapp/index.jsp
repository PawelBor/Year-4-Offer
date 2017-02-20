<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>iOffer</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script></script>
	</head>
	<body>
		<!--xs - phones
			sm - tablet
			md - desktops/laptops
			lg - large screens-->	
		<div class="wrapper">
			<form action="login" method="post" class="form-signin">
				<div class="" id="collapseLogin">
					<div class="card card-block">
						<h2 class="form-signin-heading">Please login</h2>
						<input type="email" class="form-control" id="loginEmail" name="email" placeholder="Email Address" required="" autofocus="" />
						<input type="password" class="form-control" id="loginPassword" name="password" placeholder="Password" required=""/>  
						<label class="checkbox">
						<input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
						</label>
						<button class="btn btn-lg btn-primary btn-block" id="loginButton">Login</button> 
					</div>
				</div>
			</form>
			<form action="register" method="post" class="form-signin">
				<div align="center" >
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
        <script type="text/javascript">
        	$("#loginButton").click(function(e){
        		// currently only returns false
        		$.ajax({
        			url: '/service/login',
        			type: 'POST',
        			data: {username: $("#loginEmail").val(), password: $("#loginPassword").val()},
        			success: function(data){
        				console.log("server returned: " + data + " current password: " + $("#loginPassword").val());
        				if(data){
        		        	// GO TO HOMEPAGE & SAVE COOKIES
        					window.location.assign("sidebarPage.html");
        		        }else{
        		        	// TELL USER THAT DETAILS ARE INCORRECT
        		        	console.log("Wrong credentials");
        		        }
        		    }
        		});
        	});
        	
        	// Quick code so that the register tab can be collapsed when clicked again
            $("#btnRegister").click(function(){
                $("#btnRegister").hide();
            });
        </script>
	</body>
</html>
