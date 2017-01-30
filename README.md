# Course Project - Virtual Lifestyle Coach 

**Introduction to Service Design and Engineering | University of Trento**

**Server | Process Centric**

**Student**: Gianvito Taneburgo (182569)

This file aims to provide a short documentation for the course project. Further details can be found in the report [here](https://github.com/virtual-life-coach/common/blob/master/report.pdf).
The original instructions can be found [here](https://docs.google.com/document/u/1/d/1kU66KOoprmdypDEE1W1bs1iQsX-Vf7_SXH7gAm5UYMU/edit?usp=sharing).

The project was developed individually.

Server URL: [https://vlc-server-process-centric.appspot.com/](https://vlc-server-process-centric.appspot.com/)

## Project structure

The project repository is made up of the following *files* and **folders**:
* **src/main**: application source code
    * **java/vlc/pc**: Java source code
        * **dispatcher**: requests dispatcher
            * *RequestDispatcher.java*: dispatch requests appropriately orchestrating API calls
        * **rest**: exposes RESTful API
            * *Function.java*: Cloud Endpoint REST API
    * **webapp**: web application
        * **WEB-INF**: web application resources
            * *appengine-web.xml*: GAE deployment descriptor
            * *logging.properties*: GAE logging level
            * *web.xml*: Java EE deployment descriptor
        * *index.html*: home page
        * *loading_cube.gif*: home page content
* *pom.xml*: Maven project descriptor
* *README.md*: this file
