# basic-radius-question

<a href="https://basic-question.herokuapp.com/swagger-ui.html/">basic-radius-question</a>

The goal of this program is to enable users the options to Create, Read, Update and Delete(CRUD) cars that will be stored in a MongoDB.

Each car will have a coordinates of Longitude & Latitude that indicates the location of the car on any point on the globe.

After the user has inserted his car he can then use the: "checkRadius" request and pass the: centerLongitude, centerLatitude and a radius.

the "checkRadius" request will run a complex algorithm that will check if there are any cars according to the center coordinates and radius in the database.

## Example:
ASHDOD coordinates:
Pass this to the POST request in order to create a car: (id: "123", latitude: 31.80056005890851, longitude: 34.66956283384276).

TEL-AVIV center coordinates
Pass this to the "checkRadius" request: (centerLatitude :32.09304777948897, centerLongitude: 34.77667953145146, radius: 40.0).

According to "Google Maps" the current distance in Km from ASHDOD to TEL-AVIV is 33.53 Km so when the ASHDOD coordinates are stored in the MongoDB and the user will run
the "checkRadius" with TEL-AVIV center coordinates and radius the response will contain ASHDOD and that indicates that ASHDOD is in the radius of 
under 40Km from TEL-AVIV.

This program follows the guidelines of a RestApi.

This program also includes Tests to fully check each operation of the RestApi and the database.

https://user-images.githubusercontent.com/76630855/196141244-dcd6f2af-4c6f-4641-8cd8-8dca1a578443.mp4
