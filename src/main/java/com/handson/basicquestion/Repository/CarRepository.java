package com.handson.basicquestion.Repository;

import com.handson.basicquestion.Model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> { }
