<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="utf-8">
    <title>iOffer</title>
    <meta name="description" content="iOffer.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/homeStyles.css">
    <title>Remove Markers</title>
    <style>
        html,
        body {
            height: 100%;
            background: black;
            padding: 10px;
        }
        #map {
            display: inline-block;
            position: absolute;
            top: 25%;
            bottom: 10%;
            left: 1%;
            right: 1%;
        }
        #selectPanel {
            top: 10%;
            position: relative;
            left: 32.5%;
        }
    </style>
</head>

<body>

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
                        <a class="page-scroll" href="profile.jsp">Profile</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="add.jsp">testing stuff</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="search.jsp">SHOP test</a>
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

    <div id="selectPanel">

        <input class="btn btn-primary" onclick="clearMarkers();" type=button value="Hide Markers">
        <input class="btn btn-primary" onclick="showMarkers();" type=button value="Show All Markers">
        <input class="btn btn-primary" onclick="deleteMarkers();" type=button value="Delete Markers">
        <input class="btn btn-primary" onclick="initMap();" type=button value="Initialize">

    </div>
    <div id="map"></div>
    <script>
	//Taken from:
	// https://developers.google.com/maps/documentation/javascript/markers
        // In the following example, markers appear when the user clicks on the map.
        // The markers are stored in an array.
        // The user can then click an option to hide, show or delete the markers.
        var map;
        var markers = [];
        var clatitude;
        var clongitude;

        function success(position) {
            clatitude = position.coords.latitude;
            clongitude = position.coords.longitude;

        }

        function initMap() {
            navigator.geolocation.getCurrentPosition(success);

            var userLocation = {
                lat: parseFloat(clatitude),
                lng: parseFloat(clongitude)
            };

            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 16,
                center: userLocation,
                mapTypeId: 'terrain'
            });

            // This event listener will call addMarker() when the map is clicked.
            map.addListener('click', function(event) {
                addMarker(event.latLng);
                console.log(event.latLng);
            });

            // Adds a marker at the center of the map.
            addMarker(userLocation);
        }

        // Adds a marker to the map and push to the array.
        function addMarker(location) {
            var marker = new google.maps.Marker({
                position: location,
                map: map

            });
            markers.push(marker);

        }

        // Sets the map on all markers in the array.
        function setMapOnAll(map) {
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(map);
            }
        }

        // Removes the markers from the map, but keeps them in the array.
        function clearMarkers() {
            setMapOnAll(null);
        }

        // Shows any markers currently in the array.
        function showMarkers() {
            setMapOnAll(map);
        }

        // Deletes all markers in the array by removing references to them.
        function deleteMarkers() {
            clearMarkers();
            markers = [];
        }

        setTimeout(initMap, 4000);
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCEXEWAlb54kadBS390PHS6ME-huUDaM9I&callback=initMap">
    </script>
</body>

</html>