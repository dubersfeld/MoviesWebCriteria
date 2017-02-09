# MoviesWebCriteria

This is a demonstration project on Spring development. It involves:

MySQL as a database with Spring Criteria support 
Eclipse Mars with Maven plugin Tomcat 8 as a container

moviesDBBasic.sql was used to generate the MySql database used in this project.

This project can run on Tomcat 8 from Eclipse or on Tomcat 8 standalone from a .war file. It requires Java 8 Runtime Environment.

This is the third version of this project. More stuff is coming soon with a version that uses JPA Spring support.

For the basic JDBC version of this project see the repository:

https://github.com/dubersfeld/MoviesWebJDBC

For the JPQL version of this project see the repository:

https://github.com/dubersfeld/MoviesWebJPQL

For a full tutorial about this project please visit my personal site:

http://www.dominique-ubersfeld.com/JAVADEV/SpringDevelopment.html

As a reference book I mainly used Java for Web Applications by Nicholas S. Williams

Note: this project was run on my home computer. To run it on your system you have to edit some files to customize them to your actual file system. They are:

source/production/resources/install.properties: photoTempDir=/home/dominique/Pictures/tmp/

source/production/resources/log4j2.xml:

where the folder /home/dominique should be replaced by a folder that matches your own file system.

Dominique Ubersfeld
