# Introduction
Sample spring boot project using mongo as database. 

## Features
* Swagger
* MongoDB
* Rxjava

## Installing and Running MongoDB
**Installation**
* Download mongodb [here](https://www.mongodb.com/download-center/enterprise/releases)
* For windows create folder in **C:\data\db** name **data** and inside of it create another folder name **db** in this folder mongodb will store the data.

**Running**
* After installation navigate to the bin folder of mongodb and run the **mongod.exe**(for windows) to start mongodb.

## Swagger
You can access the swagger-ui for testing http://localhost:8080/swagger-ui.html.

## Testing
Note: Before running the repository test case, shutdown first the mongodb instance to avoid conflict since it uses the EmbeddedMongo.

## MongoDB GUI
To easily view the data in mongodb i use some additional third party application called Robomongo you can download it [here](https://robomongo.org/)
