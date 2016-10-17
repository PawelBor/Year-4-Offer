# OFFER

# *Applied Project + Minor Dissertation Project 2016 - Year 4*

**Name:** Pawel Borzym, Edvardas Lasauskas, Niks Gurins, Gediminas Saparauskas </br>
**College:** Galway-Mayo Institute of Technology </br>
**Course:** Software Development - Y4 </br>
**Module:** Applied Project + Minor Dissertation </br>
**Lecturer:** Dr.John Healy </br>
**Supervisor:** Dr.Brian McGinley </br>


#Introduction

This is 4th year Software Development project. </br>
The project will be a web based application created using technologies such as:
* HTML, CSS + JavaScript
* Bootstrap
* J2EE
* MongoDB

#Functionality</br>

The purpose of the application is to deliver information to the user about current offers from businesses or private people willing to sell their items.
</br>
The user will be able to post an item for sale which will fall under desired category eg. PC/Car Parts or Food offers around the users location.</br>
The offers will be displayed on map around the users location, the user will be able to increase/decrease the radius of offers displayed.
</br>
In order for the seller to post an item they would need to complete registration form and further log in to the system.</br>
The shopper will have freedom of registering or just using the application without the need to register.</br>
The shopper is not actually buying anything on the web but is accessing the advertisement and the contact details of the poster.</br>

Advertisements will be displayed on the map with the location set by the seller using a marker icon.

#Technologies

##Front-End

We’ve decided to create this application using HTML, CSS and JavaScript for the client side simply because it can be accessed by any device. For the design we’re going to use Bootstrap.
Bootstrap comes with jQuery which we can then use for ease of Http Requests.

##Server - J2EE

J2EE is a platform-independent, Java-centric environment from Sun for developing, building and deploying Web-based enterprise applications online. The J2EE platform consists of a set of services, APIs, and protocols that provide the functionality for developing multitiered, Web-based applications.
It comes makes back end functionality development much easier to work with.

##Database

For the databse we went with MongoDB, first of all we’ve never used Non-Relational Database and now is a great opportunity to learn how it works.
MongoDB is a document-oriented database designed.
Instead of storing data in tables, with MongoDB you store data in JSON-like document with dynamic schemas.

##Collections within the database
*	User collection for log in details. Eg,. UserName/Password
*	Offer/Ad collection which contains users posts. Based on Category.
```
{
        "userName":John,
        "productName":"OnePlus 3",
        "productCategory":"Electronics",
	      "productPrice":"399",
	      "productDescription" : "Flagship killer",
        "productLocation" : "Galway"
	}
```
#Architecture

This is the 3-tier architecture of our project.</br>
The user interacts with the client and requests some data. Jquery then makes a request to the server in order to obtain specific information, then the server talks to the database and the database returns the data requested by the server, to the server and then the server forwards the data to the client.
