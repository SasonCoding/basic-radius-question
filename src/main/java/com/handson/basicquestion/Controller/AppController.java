package com.handson.basicquestion.Controller;

import com.handson.basicquestion.Model.Car;
import com.handson.basicquestion.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.lang.Math;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/findCars", method = RequestMethod.GET)
    public ResponseEntity<?> findCars(@RequestParam Double centerLatitude, @RequestParam Double centerLongitude, @RequestParam Double radius) {
        List<Car> cars = mongoTemplate.findAll(Car.class);

        if(cars.size() > 0) {
            List<Car> carsInDistance = cars.stream().filter(car -> {
                Integer RadiusInKm = 6371;
                Double dLat = deg2rad(cars.get(0).getLatitude() - centerLatitude);  // deg2rad below
                Double dLon = deg2rad(cars.get(0).getLongitude() - centerLongitude);
                Double a =
                        Math.sin(dLat/2) * Math.sin(dLat/2) +
                                Math.cos(deg2rad(cars.get(0).getLatitude())) * Math.cos(deg2rad(centerLatitude)) *
                                        Math.sin(dLon/2) * Math.sin(dLon/2)
                        ;
                Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                Double d = RadiusInKm * c; // Distance in km
                return d <= radius;
            }).collect(Collectors.toList());
            if(carsInDistance.isEmpty()) {
                return new ResponseEntity<>("No cars were found in this radius.", HttpStatus.OK);
            }
            return new ResponseEntity<>(carsInDistance, HttpStatus.OK);
        }
        return new ResponseEntity<>("The database doesn't contain any cars yet.", HttpStatus.BAD_REQUEST);
    }

    public Double deg2rad(Double deg) {
        return deg * (Math.PI/180);
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<?> insertCar(@RequestParam String carName, @RequestParam Double latitude, @RequestParam Double longitude) {
        Query query = new Query();
        query.addCriteria(Criteria.where("carName").is(carName));

        List<Car> cars = mongoTemplate.find(query, Car.class);

        if(cars.size() > 0) { //If the car already exists.
            String currentId = cars.get(0).getId();
            Optional<Car> findById = carRepository.findById(currentId);
            Car carEntity = findById.get();
            carEntity.setLatitude(latitude);
            carEntity.setLongitude(longitude);
            carEntity = carRepository.save(carEntity);
            return new ResponseEntity<>(carEntity.toString(), HttpStatus.OK);
        }

        if(cars.isEmpty()) {
            Car car = Car.CarBuilder
                    .aCar()
                    .carName(carName)
                    .longitude(longitude)
                    .latitude(latitude)
                    .build();
            car = carRepository.save(car);
            return new ResponseEntity<>(car.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Server Error", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
