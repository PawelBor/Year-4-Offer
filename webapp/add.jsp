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
      <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCEXEWAlb54kadBS390PHS6ME-huUDaM9I"></script>
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
            <div class="col-xs-12">
               <p>Advert Form:</p>
            </div>
         </div>
         <div class="row">
            <div class="col-xs-12">
               <h2>Product Details</h2>
               <div>
                  <div class="form-group">
                     <label for="prodName">Name:</label>
                     <input type="text" class="form-control" id="prodName" name="name" required="" >
                  </div>
                  <div class="form-group">
                     <label for="prodPrice">Price:</label>
                     <input class="form-control" id="prodPrice" name="price" required=""></input>
                  </div>
                  <div class="form-group">
                     <label for="prodDescription">Description:</label>
                     <textarea class="form-control" id="prodDescription" name="description" required="" rows="5"></textarea>
                  </div>
                  <div class="form-group">
                     <label for="prodLocation">Location:</label>
                     <input class="form-control" id="prodLocation" name="location" required=""></input>
                  </div>
                  <div class="form-group">
                     <div>
                        <label for="productImages">Image Select:</label>
                     </div>
                     <label class="btn btn-lg btn-warning" for="images">
                     Select File<input type="file" accept=".jpg,.jpeg,.png" id="images" style="display:none;" name="images" multiple />
                     </label>
                     <output id="list"></output>
                  </div>
                  <div class="form-group">
                     <div>
                        <label for="locationbtn">Location:</label>
                     </div>
                     <button class="btn btn-lg btn-warning" name="location" id="locationbtn">Get location</button>
                  </div>
                  <div class="form-group">
                     <label for="prodCategory">Category:</label>
                     <select class="form-control" style="width: 35%" id="prodCategory" name="category" required="">
                        <option value="" disabled selected hidden>Please Choose Category...</option>
                        <option value="electronics">Electronics</option>
                        <option value="entertainment">Entertainment</option>
                        <option value="leisure">Leisure</option>
                        <option value="automotive">Automotive</option>
                        <option value="food">Food</option>
                        <option value="other">Other</option>
                     </select>
                  </div>
                  <div class="form-group">
                     <button class="btn btn-lg btn-success btn-block" type="submit" id="postButton" name="send">Submit</button>
                  </div>
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
      <script>
		$("#menu-toggle").click(function (e) {
			e.preventDefault(); //<!-- preveent default action - dont go to a link-->
			$("#wrapper").toggleClass("menuDisplayed"); //<!-- adds/rem,oves toggle class-->
		});

		$("#postButton").click(function (e) {
			var name = $("#prodName").val();
			var description = $("#prodDescription").val();
			var price = $("#prodPrice").val();
			var location = $("#prodLocation").val();
			var category = $("#prodCategory").val();
			
			var data = new FormData();
			data.append("name", name);
			data.append("description", description);
			data.append("price", price);
			data.append("location", location);
			data.append("category", category);
			data.append("county", "Gaillimh");
			data.append("author", "Spinnerbaitman");
			
			for(i = 0; i < document.getElementById("images").files.length; i++){
				data.append("images", document.getElementById("images").files[i]);
			}
			
			
			$.ajax({
				url: "webapi/product",
				type: "POST",
				data: data,
				contentType: false,
				processData: false,
				success: function (response) {
					console.log(response)
					if (response != null) {
						window.location.assign("/service/webapi/product/" + response);
					} else {
						// TELL USER THAT DETAILS ARE INCORRECT
						console.log("Putting into db failed");
					}
				}
			});
		});
		var img;
		function handleFileSelect(evt) {
			for (var i = 0, f; f = evt.target.files[i]; i++) {
				var output = '<img width="150" "height="100" src="' + URL.createObjectURL(f) + '">';
				document.getElementById('list').innerHTML += output;
				//img.push(f);
			}
		}

		document.getElementById('images').addEventListener('change', handleFileSelect, false);
		
		var userLocation = "0 0";
		var lat;
		var lon;
		$("#locationbtn").click(function (e) {
			function loc(position) {
				latitude = position.coords.latitude;
				longitude = position.coords.longitude;
				
				lat = latitude;
				lon = longitude;
				userLocation = latitude + " " + longitude;

				return userLocation;
			}
			
			navigator.geolocation.getCurrentPosition(loc)
			
			setTimeout(function(){ document.getElementById('prodLocation').value = userLocation;
			getCounty(lat, lon);}, 1000);
		});
      </script>
   </body>
</html>