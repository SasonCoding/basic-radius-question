package com.handson.basicquestion.Service;

import com.handson.basicquestion.Model.Car;
import com.handson.basicquestion.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LocationService {
    @Autowired
    CarRepository carRepository;

    public List<Car> checkCarsInRadius(Double centerLatitude, Double centerLongitude, Double radius) {
        List<Car> cars = carRepository.findAll();

        List<Car> carsInDistance = cars.stream().filter(car -> {
            Integer RadiusInKm = 6371;
            Double dLat = deg2rad(cars.get(0).getLatitude() - centerLatitude);  // deg2rad below
            Double dLon = deg2rad(cars.get(0).getLongitude() - centerLongitude);
            Double a =
                    Math.sin(dLat/2) * Math.sin(dLat/2) +
                            Math.cos(deg2rad(cars.get(0).getLatitude())) * Math.cos(deg2rad(centerLatitude)) *
                                    Math.sin(dLon/2) * Math.sin(dLon/2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            Double d = RadiusInKm * c; // Distance in km
            return d <= radius;
        }).collect(Collectors.toList());

        return carsInDistance;
    }
    public Double deg2rad(Double deg) {
        return deg * (Math.PI/180);
    }

}
