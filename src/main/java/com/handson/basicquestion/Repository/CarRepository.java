package com.handson.basicquestion.Repository;

import com.handson.basicquestion.Model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> { }
