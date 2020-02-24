# ymorTest

[Docker version 19.03.5]<br/>
<br/>
to create the containers, please run the following commands

1. docker pull mysql:5.6 <br/>
2. docker run --name mysql-ymortest -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=ymortest -e MYSQL_USER=user -e MYSQL_PASSWORD=password -d mysql:5.6 <br/>
3. docker build . -t spring-boot-ymortest  (to run within your project) <br/> 
4. docker run -p 8086:8086 --name spring-boot-ymortest --link mysql-ymortest:mysql -d spring-boot-ymortest <br/>

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
  
## Requirements<br/>
For building and running the application you need:<br/>
<br/>
* JDK 11
* Maven 3
