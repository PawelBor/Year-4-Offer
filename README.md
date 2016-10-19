# OFFER

# *Applied Project + Minor Dissertation Project 2016 - Year 4*

**Name:** Pawel Borzym, Edvardas Lasauskas, Niks Gurins, Gediminas Saparauskas </br>
**College:** Galway-Mayo Institute of Technology </br>
**Course:** Software Development - Y4 </br>
**Module:** Applied Project + Minor Dissertation </br>
**Lecturer:** Dr.John Healy </br>
**Supervisor:** Dr.Brian McGinley </br>


#Introduction

This is our 4th year Software Development project. </br>
The project will be a web / android based application created using technologies such as:
* HTML, CSS + JavaScript
* Bootstrap
* J2EE
* MongoDB
* Java
* XML + JSON
* Google Api's
* Agile


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

We have also decided to create a native android application along side the web-based technologies for improved user interaction and UX.
The main development language will be java with all necessary Google Api's and data-interchange formats.
For a large portion of the development cycle the Android studio IDE will be utilized.

###***The Trio:*** HTML, CSS, JavaScript

**HTML** stands for *HyperText Markup Language* and is a standard language used for creation of web pages and web aplications.</br>
It describes the structure/architecture and overall appearance of the information on the Internet. Keywords and tags are used to format and demonstrate the content of Web Page.</br>
Below we have example of a very simple HTML document with keywords and tags such as ```<p>``` which means "paragraph".
```
<!DOCTYPE html>
<html>
<head>
<title>Simple HTML Document</title>
</head>
<body>

<p>Hello HTML World.</p>

</body>
</html>
```

**CSS** represent Cascading Style Sheets, it is used to define how the HTML elements are to be laid out on the screen and in general how will the user see it. It's highly beneficial as the CSS document can manage the layout of not just one page or just specific item on the page but every single page in the project. </br>
For example website made up of 5 different pages has button on each page, the CSS can manage the design of every button to be the same no matter what page it's on. CSS can be internal or external. Internal CSS means that the CSS will be inside the HTML document and the downside of this is that it will only affect the page containing the code rather than all the pages.
External CSS on the other hand is saved as a separate file in the solution with ```.css``` extension and it may be used by all pages in the project.</br>
Example of how to link HTML to CSS:</br>
```
<head>
<link rel="stylesheet" type="text/css" href="design.css">
</head>
```
Also below an example of actual CSS file.
```
body {
    background-color: white;
}

h1 {
    margin-left: 50px;
    color: red;
}
```



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

![image](http://i63.tinypic.com/11hbhja.jpg)

This is the 3-tier architecture of our project.</br>
The user interacts with the client and requests some data. Jquery then makes a request to the server in order to obtain specific information, then the server talks to the database and the database returns the data requested by the server, to the server and then the server forwards the data to the client.


#Work Plan
* *Week 1 - 2* Brainstorm ideas
* *Week 3* Confirmed our idea with the supervisor
* *Week 4* Begin research on the various technologies we'll use
* *Week 5 - 8* Write up some documentation and create a basic connection between all the different technologies
* *Week 9 - ?* Actualize the idea over the technologies, continue implementation
* *Week 24* Deploy project on remote machine
