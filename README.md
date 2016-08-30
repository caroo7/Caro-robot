# robot
Screen scraping project for e-libraries

+20 points - first estimates


Run instuction:

First demo purpose is to retrieve data from empik site and save them on database.

Requirements:

    postgres database server is installed on your machine,
    on postgres database you have to create new empty database with name 'bookRobot' (make sure you set appropriate owner on this database),
    in Caro-robot/database/src/main/resources/application.properties file set appropriate credentials to your database (it depends on previous set user).

Steps to run:

    clone repository and checkout on FIRST_DEMO branch
    in Caro-robot/database/src/main/resources/application.properties set property: hibernate.hbm2ddl.auto to 'create' value and run application (run main mathod in ParserMain). Hibernate put appropriate tables into 'bookRobot' database.
    now you have to add genres which are mandatory for retrieved books, so on postgres side execute sqls which create predefined genres:

﻿insert into genre (name) values ('no category');
insert into genre (name) values ('biographical');
insert into genre (name) values ('economic');
insert into genre (name) values ('kids');
insert into genre (name) values ('youth');
insert into genre (name) values ('house');
insert into genre (name) values ('encyclopedia');
insert into genre (name) values ('fantasy');
insert into genre (name) values ('IT');
insert into genre (name) values ('technology');
insert into genre (name) values ('languages');
insert into genre (name) values ('classic');
insert into genre (name) values ('comic');
insert into genre (name) values ('criminal');
insert into genre (name) values ('academic_book');
insert into genre (name) values ('kitchen');
insert into genre (name) values ('lectures');
insert into genre (name) values ('literature');
insert into genre (name) values ('medicine');
insert into genre (name) values ('science');
insert into genre (name) values ('studen_book');
insert into genre (name) values ('guide');
insert into genre (name) values ('law');
insert into genre (name) values ('romance');
insert into genre (name) values ('prose');
insert into genre (name) values ('sensation');
insert into genre (name) values ('art');
insert into genre (name) values ('vacation');
insert into genre (name) values ('thriller');


Thanks to that, empik genres will be mapped to appropriate genres.

    in Caro-robot/database/src/main/resources/application.properties set property: hibernate.hbm2ddl.auto to 'update' value and run ParserMain class. Hibernate will populate previously created tables.


