# robot
Screen scraping project for e-libraries

+20 points - first estimates
+2 points

How to run?
- clone project from repository: https://github.com/caroo7/Caro-robot
- when you are on 'master' branch make 'mvn clean install'.
  If you want to change database go to Caro-robot\database\src\main\resources\application.properties
  and set appropriate database name and credentials. By default our remote database which is located on Amazon AWS server is configured.
- in Caro-robot\parser\target \parser-executable.jar file will be created. Run it:
  java -jar parser-executable.jar. Database will' be created, ebooks from empik and publio will be downloaded,
  everything will be saved on database and finally cache files will be created (cache module will save serialized objects from database into files).
- go to Caro-robot\web and run 'mvn jetty run' - web client will appear in browser under: 'localhost:8080/servlet' address and you can use it.

FOR DEMO PURPOSES, RIGHT NOW ONLY FEW BOOKS ARE PROCESSED BECAUSE OF PERFORMANCE!