<%@ page language="java" contentType="text/html; utf-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Shop Homepage</title>
      <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/homeStyles.css">
   </head>
   <body class="login-body">
      <!-- Navigation -->
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
      <!-- Page Content -->
      <div class="container">
         <div class="row">
            <div class="col-md-9">
               <div class="thumbnail">
                  <div class="row carousel-holder">
	                  <div class="col-md-12">
	                     <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	                        <ol class="carousel-indicators">
	                           <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	                           <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	                           <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	                        </ol>
	                        <div class="carousel-inner" id="images">
	                        </div>
	                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
	                        <span class="glyphicon glyphicon-chevron-left"></span>
	                        </a>
	                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
	                        <span class="glyphicon glyphicon-chevron-right"></span>
	                        </a>
	                     </div>
	                  </div>
	               </div>
                  <div class="caption-full">
                     <h4 class="pull-right" id="prodPrice"></h4>
                     <h4 id="prodName">
                     </h4>
                     <p id="prodDescription"></p>
                     <button id="deletebtn" class="btn btn-danger btn-block" style="display: none;">Delete</button>
                  </div>
               </div>
               <div class="well">
                  <div class="form-group">
                     <label for="itemComment">Your Comment Here:</label>
                     <input class="form-control" id="itemComment" type="text">
                     <br>
                     <div class="text-right">
                        <button class="btn btn-success" id="postComment">Post Comment</button>
                     </div>
                  </div>
                  <hr>
                  <div class="row">
                     <div class="col-md-12">
                        <b>TestUserName</b>
                        <span class="pull-right">PostDateHere</span>
                        <p>Test User Comment</p>
                     </div>
                  </div>
                  <hr>
               </div>
            </div>
         </div>
      </div>
      <!-- /.container -->
      <div class="container">
         <hr>
         <!-- Footer -->
         <footer>
            <div class="row">
               <div class="col-lg-12">
                  <p>Copyright &copy; iOffer 2017</p>
               </div>
            </div>
         </footer>
      </div>
   </body>
   <script type="text/javascript">
   		var pmail = "";
	   $(document).ready(function() {
			// https://developer.mozilla.org/en-US/docs/Web/API/Document/cookie
		    
		    // Check if cookie called "email" exists. If it does, change the reg/login button to a different one
		    if (document.cookie.indexOf('email') > -1 ) {
			    var cookieEmail = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
			 	// Update the time before the user is logged out
			 	var date = new Date();
	   		 	var cEmail = "email=" + cookieEmail + ";expires=" + date.setHours(date.getHours() + 1) + ";path=/";
	   		 	document.cookie = cEmail;
	   		 	pmail = cookieEmail;
	    	    // Change button value/href
	    	    $("#btnLoginText").text(cookieEmail);
	    	    $("#btnLogin").prop("href", "profile.jsp");
			}
		});   
   
   		var response = "";
   		
	    window.onload = function () {
		    var url = document.location.href,
		        params = url.split('?')[1].split('&'),
		        data = {}, tmp;
		    for (var i = 0, l = params.length; i < l; i++) {
		         tmp = params[i].split('=');
		         data[tmp[0]] = tmp[1];
		    }
		    
		    response = data.id;
		    
		    $.getJSON("webapi/product/"+response, function (data){
		    	console.log(data);
		    	
		    	if(data.author === pmail){
	    	    	$("#deletebtn").show();
	    	    }
		    	
                $("#prodName").text(data.name);
                $("#prodPrice").text("€" + data.price);
                $("#prodDescription").text(data.description);
                var images = data.image.split(",");
                for(i = 0; i<images.length; i++){
                	if(i == 0){
                		var tab = '<div class="item active">';
                		tab += '<img class="slide-image" src="data:image/png;base64,' + images[i] + '" alt="">';
                		tab += '</div>'
                		$("#images").append(tab);
                		// <div class="item active">
                	}else{
                		var tab = '<div class="item">';
                		tab += '<img class="slide-image" src="data:image/png;base64,' + images[i] + '" alt="">';
                		tab += '</div>'
                		$("#images").append(tab);
                	}
                }
            });
		}
	    
	    $("#deletebtn").click(function (e) {
			var data = new FormData();
			data.append("id", response);
			
			$.ajax({
				url: "webapi/product/"+response,
				type: "DELETE",
				processData: false,
				success: function (response) {
					console.log(response)
					if (response) {
						window.location.assign("profile.jsp");
					} else {
						// TELL USER THAT DETAILS ARE INCORRECT
						console.log("Putting into db failed");
					}
				}
			});
		});
	    
	    $("#postComment").click(function (e) {
	    	var comment = $("#itemComment").val();
			var d = new Date();
			//+ d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds()
			var date = d.toString();
			
			var data = {
				'id' : response,
				'date' : date,
				'comment' : comment
			};
			
			$.ajax({
				url: "webapi/product/comment",
				type: "PUT",
				data: JSON.stringify(data),
				contentType: "application/json",
				success: function (success) {
					console.log(success)
					if (success) {
						window.location.assign("item.jsp?id=" + response);
					} else {
						// TELL USER THAT DETAILS ARE INCORRECT
						console.log("Putting into db failed");
					}
				}
			});
		});
   
   		
   </script>
</html>