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
               <div class="thumbnail">
                  <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                  <div class="caption-full">
                     <h4 class="pull-right">PriceOfItemHERE</h4>
                     <h4><a href="#">ProductNameHERE</a>
                     </h4>
                     <p>Paragraph describing the item.</p>
                  </div>
               </div>
               <div class="well">
                  <form action="postComment" method="post">
                     <label for="itemComment">Your Comment Here:</label>
                     <input class="form-control" id="itemComment" type="text">
                     <br>
                     <div class="text-right">
                        <button class="btn btn-success" type="submit" name="send">Post Comment</button>
                     </div>
                  </form>
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
</html>