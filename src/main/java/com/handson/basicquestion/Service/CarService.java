package com.handson.basicquestion.Service;

import com.handson.basicquestion.Model.Car;
import com.handson.basicquestion.Repository.CarRepository;
import com.handson.basicquestion.Util.HandsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.handson.basicquestion.Model.Car.CarBuilder.aCar;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Car findCar (String carId) {
        return carRepository.findById(carId).orElseThrow(() -> new EmptyResultDataAccessException("No car was found with this: " + carId + " id", 1));
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(String carId) {
        carRepository.deleteById(carId);
    }

    public Car insertCar(String carId, Double latitude, Double longitude) {
        return carRepository.save(aCar().id(carId).longitude(longitude).latitude(latitude).build());
    }
}
