# Geolocation
J2EE Application that provides RESTful WS and using MongoDB with Morphia.

For this example, this project provides a CRUD of geolocation with a location coordinates, and save or read this information from MongoDB database.

#Technologies
I use Maven to add the dependencies in the pom.xml file.

- Morphia: this component included in org.mongodb provides a DAO layer that is responsible of mapping the Object to MongoDB and viceversa. When projects with MongoDB are too big, is necessary Morphia because it abstracts you from the BD.

- Log4j: the project is integrated a system log with Log4j.

#Advices

- Don´t forget that MongoDB required only one instance, so you must initialize it in the Listener.

- In our GeoProfileVo object the Id is String type but MongoDB keeps a ObjectId, so we must instantiate an ObjectId(id) for seach for seach a specific object.

