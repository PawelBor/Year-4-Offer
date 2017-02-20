<%-- 
    Document   : index
    Created on : 25-Jan-2017, 11:19:02
    Author     : Niks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>iOffer</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/sidebar.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	</head>
	<body>
            <div id="images"><div>
            
            <script type="text/javascript">
                var imageStrings = '${images}';
                var images = imageStrings.split(",");
                for(i = 0; i < images.length; i++){
                    document.getElementById("images").innerHTML += '<img height="100" width="100" src="data:image/png;base64,' + images[i] + '">';
                }
            </script>
	</body>
</html>
