# ymorTest
## Requirements<br/>
For building and running the application you need:<br/>
<br/>
* JDK 11
* Maven 3
<br/>
within the project<br/>
*I used Junit 5 <br/>
*I run mysql within a docker container <br/>

[Docker version 19.03.5]<br/>
to create the containers, please run the following commands<br/> 
* docker pull mysql:5.6<br/>
* docker run --name mysql-ymortest -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=ymortest -e MYSQL_USER=user -e MYSQL_PASSWORD=password -d mysql:5.6 

* docker build . -t spring-boot-ymortest  (to run within your project) <br/>
* docker run -p 8086:8086 --name spring-boot-ymortest --link mysql-ymortest:mysql -d spring-boot-ymortest
<br/>

## Mysql<br/>
<br/>

you can create the following table and [import to it the csv file through for instance MySQL Workbench](https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-table.html)
<br/>
<br/>
<br/>CREATE TABLE `ymortest`.`postcode_coordinates` ( <br/>
  `id` BIGINT(20) NOT NULL, <br/>
  `postcode` VARCHAR(6) NOT NULL, <br/>
  `latitude` DECIMAL(10,8) NOT NULL, <br/>
  `longitude` DECIMAL(10,9) NOT NULL, <br/>
  PRIMARY KEY (`id`)); <br/>
  

