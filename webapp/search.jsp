<%@ page language="java" contentType="text/html; charset=utf-8"
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
      <!-- Custom CSS -->
      <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/homeStyles.css">
      </head>
   <body class="login-body">
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
            <div class="col-md-3">
               <p class="lead">iOffer</p>
               <hr>
               <form action="searchProduct" method="post">
                  <div class="form-group">
                     <label for="productName">Product Name:</label>
                     <input class="form-control" id="productName" type="text">
                     <label for="minPrice">Min Price:</label>
                     <input class="form-control" id="minPrice" type="text">
                     <label for="maxPrice">Max Price:</label>
                     <input class="form-control" id="maxPrice" type="text">
                     <label for="categorySelect">Category:</label></br>
                     <select class="form-control" id="categorySelect" name="category">
                        <option value="" disabled selected hidden>Please Choose Category...</option>
                        <option value="electronics">Electronics</option>
                        <option value="entertainment">Entertainment</option>
                        <option value="leisure">Leisure</option>
                        <option value="automotive">Automotive</option>
                        <option value="food">Food</option>
                        <option value="other">Other</option>
                     </select>
                     <hr>
                     <button type="submit" name="send" class="btn btn-primary btn-xl">Submit</button>
                  </div>
                  <!--<button type="submit" value="Submit" name="send">-->
               </form>
            </div>
            <div class="col-md-9">
               <div class="row" id="products">
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
      <!-- /.container -->
      
      <script type="text/javascript">
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
      
        	$(window).on("load", function() {
	            var QueryString = function () {
	                // This function is anonymous, is executed immediately and 
	                // the return value is assigned to QueryString.
	                var query_string = {};
	                var query = window.location.search.substring(1);
	                var vars = query.split("&");
	                for (var i=0;i<vars.length;i++) {
	                  var pair = vars[i].split("=");
	                      // If first entry with this name
	                  if (typeof query_string[pair[0]] === "undefined") {
	                    query_string[pair[0]] = decodeURIComponent(pair[1]);
	                      // If second entry with this name
	                  } else if (typeof query_string[pair[0]] === "string") {
	                    var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
	                    query_string[pair[0]] = arr;
	                      // If third or later entry with this name
	                  } else {
	                    query_string[pair[0]].push(decodeURIComponent(pair[1]));
	                  }
	                } 
	                return query_string;
	            };
	            
	            var catagory = QueryString.category;
	            
	            if(catagory != null)
	            {
	              $.getJSON("webapi/product/category/"+catagory, function (data){
	                  for (var i=0;i < data.length ;i++) {
	                    var tab = createTab(data[i])
	                    $('#getCategory').append(tab);
	                  }
	                });
	            }else // No category selected
	          {
	              $.getJSON("webapi/products", function (data){
	                  for (var i=0;i < data.length ;i++) {
	                    var tab = createTab(data[i])
	                    $('#products').append(tab);
	                        }
	                }); 
	          }
          });
        	
        	// A function to create a template for each item returned from the search
        	function createTab(data){
        		var tab = '<div class="col-sm-4 col-lg-4 col-md-4">';
        		tab += '<div class="thumbnail">';
        		tab += '<img style="width:320px;height:150px;" src="data:image/png;base64,' + data.image + '">';
        		tab += '<div class="caption">';
        		tab += '<h4 class="pull-right">€' + data.price + '</h4>';
        		// This href takes the user to a different page displaying the ad clicked
        		tab += '<h4><a href="item.jsp?id=' + data.productId + '">'+ data.name + '</a></h4>';
        		tab += '<p>' + data.description + '</p>';
        		tab += '</div></div></div>';
        		
        		return tab;
        	}
        </script>
   </body>
</html>
