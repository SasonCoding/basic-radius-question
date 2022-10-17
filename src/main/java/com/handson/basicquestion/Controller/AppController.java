package com.handson.basicquestion.Controller;

import com.handson.basicquestion.Model.Car;
import com.handson.basicquestion.Service.CarService;
import com.handson.basicquestion.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    LocationService locationService;
    @Autowired
    CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<?> findCars() {
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }

    @RequestMapping(value = "/car/{carId}", method = RequestMethod.GET)
    public ResponseEntity<?> findCar(@PathVariable String carId) {
        return new ResponseEntity<>(carService.findCar(carId), HttpStatus.OK);
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<Car> insertCar(@RequestBody Car requestCar) {
        Car car = carService.insertCar(requestCar.getId(), requestCar.getLatitude(), requestCar.getLongitude());

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkRadius", method = RequestMethod.GET)
    public ResponseEntity<?> checkRadius(@RequestParam Double centerLatitude, @RequestParam Double centerLongitude, @RequestParam Double radius) {
        List<Car> carsInDistance = locationService.checkCarsInRadius(centerLatitude, centerLongitude, radius);

        return new ResponseEntity<>(carsInDistance, HttpStatus.OK);
    }

    @RequestMapping(value = "/car/{carId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@PathVariable String carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>("Car: " + carId + " was successfully deleted", HttpStatus.OK);
    }
}
