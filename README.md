# Java Servlet Project

## Description
2. **Система Time-Tracking**. Администратор закрепляет за пользователем
Активность. У пользователя может быть одна или несколько Активностей.
Пользователь отмечает кол-во затраченного времени на каждую активность.
Пользователь может отправить запрос на добавление/удаление Активности.

## Installation
* Install jdk version 9 or later (tested with open-jdk-11)
* Install maven (tested with version 3)
* Install Tomcat version 8 or later (tested with Tomcat 8.5 and Tomcat 9.0)
* Install MySQL (tested with version 5.7)

## Configuration
* Tomcat: user = admin, password = admin
* MySQL: user = root, password = root

## Running
* Create and populate the database: run SQL script 
src/main/resources/db/sql_script.sql  
(for example,  
from *nix command line `$ mysql -u root -p < PATH`,  
from mysql command line `mysql> source PATH`,  
where PATH is the aforementioned path to the script)
* Run Tomcat (in case it is not running as a daemon):  
`$CATALINA_HOME/bin/startup.sh` (*nix),  
`$CATALINA_HOME/bin/startup.bat` (Windows)
* Deploy: mvn tomcat7:deploy

## Usage
* Go to localhost:8080/time-tracking
* Sign in as an admin: login = john, password = doe to add new users 
and perform other admin actions  
or  
* Sign in as an ordinary user: login = ivan, password = ivan to perform user actions

