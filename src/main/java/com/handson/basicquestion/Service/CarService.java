package com.handson.basicquestion.Service;

import com.handson.basicquestion.Model.Car;
import com.handson.basicquestion.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.handson.basicquestion.Model.Car.CarBuilder.aCar;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Car findCar (String carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isPresent()) {
            return carOptional.get();
        }else {
            throw new RuntimeException("No car was found with this: '" + carId + "' id");
        }
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
