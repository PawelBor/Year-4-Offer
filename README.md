# OFFER

# *Applied Project + Minor Dissertation Project 2016 - Year 4*

**Name:** Pawel Borzym, Edvardas Lasauskas, Niks Gurins, Gediminas Saparauskas </br>
**College:** Galway-Mayo Institute of Technology </br>
**Course:** Software Development - Y4 </br>
**Module:** Applied Project + Minor Dissertation </br>
**Lecturer:** Dr.John Healy </br>
**Supervisor:** Dr.Brian McGinley </br>


# Introduction

This is our 4th year Software Development project. </br>
The project will be a web / android based application created using technologies such as:
* HTML, CSS + JavaScript
* Bootstrap
* J2EE
* MongoDB
* Java, JAX RS, Jersey
* XML + JSON
* Google Api's
* Agile


# Functionality</br>

The purpose of the application is to deliver information to the user about current offers from businesses or private people willing to sell their items.
</br>
The user will be able to post an item for sale which will fall under desired category eg. PC/Car Parts or Food offers around the users location.</br>
The offers will be displayed on map around the users location, the user will be able to increase/decrease the radius of offers displayed.
</br>
In order for the seller to post an item they would need to complete registration form and further log in to the system.</br>
The shopper will have freedom of registering or just using the application without the need to register.</br>
The shopper is not actually buying anything on the web but is accessing the advertisement and the contact details of the poster.</br>

Advertisements will be displayed on the map with the location set by the seller using a marker icon.

# Technologies

## Front-End

We’ve decided to create this application using HTML, CSS and JavaScript for the client side simply because it can be accessed by any device. For the design we’re going to use Bootstrap.
Bootstrap comes with jQuery which we can then use for ease of Http Requests.

We have also decided to create a native android application along side the web-based technologies for improved user interaction and UX.
The main development language will be java with all necessary Google Api's and data-interchange formats.
For a large portion of the development cycle the Android studio IDE will be utilized.

### ***The Trio:*** HTML, CSS, JavaScript

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

## Server - J2EE

J2EE is a platform-independent, Java-centric environment from Sun for developing, building and deploying Web-based enterprise applications online. The J2EE platform consists of a set of services, APIs, and protocols that provide the functionality for developing multitiered, Web-based applications.
It comes makes back end functionality development much easier to work with.

We decided to implement a restful services to our website. For this we are going to use the **JAX RS** java API.

JAX RS is a java API which provides support to create a functional web service using the representational state transfer programming pattern. **REST(Representational state transfer)** web applications allow for programs to have access to data in a variety of media types in an abstract way.

REST also allows them to change textual representations of the websites resources, by using uniform and defined set of stateless operations, such as ***GET, POST, DELETE, PUT***. To implement JAX RS web services in our glassfish java back end server, we are going to use the **Jersey** framework.

Jersey provides an API itself that can extend the JAX RS API with more features, which allow to make the RESTful web service development more simple. One of the goals of Jersey framework is to allow for easier development of RESTful web services using java programming language.

Example of JAX RS:

```
@Path("/example")
Public class example{
	@GET
	@Path("{id}")
	Public Response getExample(@PathParam("id") String id){
		return Response.status(200).entity(id).build();
	} 
}
```

This example code returns the value of the id variable, which is typed in by the user in the URL. Eg. ***Website.com/example/123***, would return to the web page 123, which is the **/(“{id}”)** part of the URL route.

## Database
### MongoDB

####Prerequisites
To get MongoDB up and running , download the mongodb installer from their [Website](https://www.mongodb.com/)
</br>
Once finished installing make a folder on your computer eg. ```C:\data\db``` , to store the data from the Mongo database.</br>
You can also set up to use MongoDB in text editor eg. Intellij to interact with MongoDB.

#### MongoDB
MongoDB is a cross platform document oriented database.</br> 
There are no structured tables like in relational databases. Mongo uses dynamic schemas and similar to JSON-like documents to store data. Documents are stored in collections and the documents are indexed by MongoDB to keep track of the order of the documents inserted.</br>
Mongo is used to handle diverse data types, fast queries and for ease of scalling the data.

### Why MongoDB
We decided to use Mongo for our project as we will not be dealing with complex transactions with the database.
Another reason why we chose MongoDB is to learn a new technology and learn about using NoSQL databases.

### Images with MongoDB
We decided to store images that are stored on our MongoDB database by creating a product on the web site. 

A common library is available with MongoDB called Gridfs, however we will not use it as we will not be storing images or files that are over 16 megabytes in size.

Instead we decided to use something we have done in class one time, which is the Base64 encoding format. We wanted to put what we learned to practise. We select images in product creation using a file picker which filters file input only to .jpg, .jpeg and .png files. On the server side the images are converted to Base64 strings and stored in the database.

To retrieve the images, we carry out the opposite of what we do to store them. We pass down the Base64 strings to javascript on the front end and it decodes the strings back to image format for display on the web site.

## Collections within the database
*	User collection for log in details. Eg. UserName/Password
*	Offer/Ad collection which contains users posts. Based on Category.

```
{
	"userName":John,
	"productName":"OnePlus 3",
	"productCategory":"Electronics",
	"productPrice":"399",
	"productDescription" : "Flagship killer",
	"productLocation" : "Galway",
	"productImages" : "1..*"
}
```


## DATA
### Login
For Login functionality the user will be asked to provide user information such as:
* E-Mail
* Password (SHA-265)

### Registration
For Registration the user will be asked to provide:
* E-Mail - which will be used for logging in
* Password (SHA-265 Encrypted)
* Full Name
* County - Possibly display posts by county.
* Phone Number - Make sure that the seller will be accessible if no other information is provided.

## Advertisement Posting
These are the following requirements to post an advertisement:
* Advertisement Image (Optional)
* Advertisement Name
* Advertisement Description
* Phone Number (Provided from Registration)
* Seller ID/Name
* Position (lat/long) - Will be used to post on map. The Seller will have a choice to use their location **OR** by placing a marker on the map.
* Advertisement Category
* Advertisement Price


# Architecture

![ScreenShot](http://i63.tinypic.com/11hbhja.jpg)

Our project is based on the 3 tier client server architecture.</br>
This involves a front end (the client), back end (server) and a database to store and retrieve data. </br>

**Front end:** The client in our project is a web application written in HTML, CSS, bootstrap and Jquery. The user interacts with the front end of the app to display to enter data.</br>

**Back end:** The server written in java using j2ee receives requests from the front end made by the users. A request could be to retrieve some data from the database or to send some data to update or insert into the database.</br>

**Database:** The database we are using for our project is MongoDB. The server can run a query against the database to insert, update, delete or insert data. </br>

***Example:*** The user can request some data to be displayed. A http get request is sent to the server with some parameters to use them to run a query against the database. The database will return a result set of the query to the server. The server then sends the data back and it is displayed to the user in the front end.



# Work Plan
* *Week 1 - 2*	Brainstorm ideas
* *Week 3* 	Confirmed our idea with the supervisor
* *Week 4* 	Begin research on the various technologies we'll use
* *Week 5 - 8* 	Write up some documentation and create a basic connection between all the different technologies
* *Week 9 - ?* 	Actualize the idea over the technologies, continue implementation
* *Week 24* 	Deploy project on remote machine
