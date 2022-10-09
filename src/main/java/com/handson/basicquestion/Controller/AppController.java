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

    @RequestMapping(value = "/createCar", method = RequestMethod.POST)
    public ResponseEntity<?> insertCar(@RequestParam String carId, @RequestParam Double latitude, @RequestParam Double longitude) {
        Car car = carService.insertCar(carId, latitude, longitude);
        return new ResponseEntity<>(car.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkRadius", method = RequestMethod.GET)
    public ResponseEntity<?> checkRadius(@RequestParam Double centerLatitude, @RequestParam Double centerLongitude, @RequestParam Double radius) {
        List<Car> carsInDistance =  locationService.checkCarsInRadius(centerLatitude, centerLongitude, radius);

        if (carsInDistance.size() < 1) {
            return new ResponseEntity<>("No cars were found in this radius.", HttpStatus.OK);
        }

        return new ResponseEntity<>(carsInDistance, HttpStatus.OK);
    }

    @RequestMapping(value = "/findCars", method = RequestMethod.GET)
    public ResponseEntity<?> findCars() {
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@RequestParam String carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>("Car: " + carId + " was successfully deleted", HttpStatus.OK);
    }
}
