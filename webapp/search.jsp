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
      </head>
   <body class="login-body">
      <nav class="navbar navbar-default navbar-fixed-top">
         <div class="container">
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-navbar">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand page-scroll" href="homePage.html"><i class="ion-ios-analytics-outline"></i>iOffer</a>
            </div>
            <div class="navbar-collapse collapse" id="bs-navbar">
               <ul class="nav navbar-nav">
                  <li>
                     <a class="page-scroll" href="loginPage.html">Register / Sign IN</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="locationPage.html">Map</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="profilePage.html">Profile</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="sidebarPage.html">testing stuff</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="shopHomePage.html">SHOP test</a>
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
               <div class="row carousel-holder">
                  <div class="col-md-12">
                     <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                           <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                           <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                           <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                           <div class="item active">
                              <img class="slide-image" src="http://placehold.it/800x300" alt="">
                           </div>
                           <div class="item">
                              <img class="slide-image" src="http://placehold.it/800x300" alt="">
                           </div>
                           <div class="item">
                              <img class="slide-image" src="http://placehold.it/800x300" alt="">
                           </div>
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
        	$(window).on("load", function() {
        		$.getJSON("webapi/products", function (data){
        			for (var i=0;i < data.length ;i++) {
        				var tab = createTab(data[i])
        				$('#products').append(tab);
                    }
        		});	
        	});
        	
        	// A function to create a template for each item returned from the search
        	function createTab(data){
        		var tab = '<div class="col-sm-4 col-lg-4 col-md-4">';
        		tab += '<div class="thumbnail">';
        		tab += '<img style="width:320px;height:150px;" src="data:image/png;base64,' + data.image + '">';
        		tab += '<div class="caption">';
        		tab += '<h4 class="pull-right">€' + data.price + '</h4>';
        		// This href takes the user to a different page displaying the ad clicked
        		tab += '<h4><a href="webapi/product/' + data.productId + '">'+ data.name + '</a></h4>';
        		tab += '<p>' + data.description + '</p>';
        		tab += '</div></div></div>';
        		
        		return tab;
        	}
        </script>
   </body>
</html>