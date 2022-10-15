package com.handson.basicquestion;

import com.handson.basicquestion.Controller.AppController;
import com.handson.basicquestion.Model.Car;
import com.handson.basicquestion.Repository.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.handson.basicquestion.Model.Car.CarBuilder.aCar;

@Service
public class TestService {
    @Autowired
    AppController appController;
    public Car getTestCar(Locations carLocation) {
        return aCar().id(carLocation.getCarId()).latitude(carLocation.getLatitude()).longitude(carLocation.getLongitude()).build();
    }
    public Car insertCarInLocation(Locations location) {
        Car testCar = getTestCar(location);
        appController.insertCar(testCar);
        return testCar;
    }

    public List<Car> getCarsAroundLocation(Locations centerLocation, Double radius) {
        return (List<Car>) appController.checkRadius(centerLocation.getLatitude(), centerLocation.getLongitude(), radius).getBody();
    }

    public Car filterListByCarId(List<Car> carsList, String carId) {
        return carsList.stream().filter(car -> car.getId().equals(carId)).findFirst().orElse(null);
    }
}
