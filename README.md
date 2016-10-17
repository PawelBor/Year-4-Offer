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
###MongoDB

####Prerequisites
To get MongoDB up and running , download the mongodb installer from their [Website](https://www.mongodb.com/)
</br>
Once finished installing make a folder on your computer eg. ```C:\data\db``` , to store the data from the Mongo database.</br>
You can also set up to use MongoDB in text editor eg. Intellij to interact with MongoDB.

####MongoDB
MongoDB is a cross platform document oriented database.</br> 
There are no structured tables like in relational databases. Mongo uses dynamic schemas and similar to JSON-like documents to store data. Documents are stored in collections and the documents are indexed by MongoDB to keep track of the order of the documents inserted.</br>
Mongo is used to handle diverse data types, fast queries and for ease of scalling the data.

###Why MongoDB
We decided to use Mongo for our project as we will not be dealing with complex transactions with the database.
Another reason why we chose MongoDB is to learn a new technology and learn about using NoSQL databases.

##Collections within the database
*	User collection for log in details. Eg. UserName/Password
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

![image](http://i68.tinypic.com/2rdul9d.jpg)

This is the 3-tier architecture of our project.</br>
The user interacts with the client and requests some data. Jquery then makes a request to the server in order to obtain specific information, then the server talks to the database and the database returns the data requested by the server, to the server and then the server forwards the data to the client.
