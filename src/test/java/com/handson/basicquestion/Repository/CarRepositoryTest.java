package com.handson.basicquestion.Repository;

import com.handson.basicquestion.BasicQuestionApplication;
import com.handson.basicquestion.Controller.AppController;
import com.handson.basicquestion.Model.Car;
import static org.junit.jupiter.api.Assertions.*;

import com.handson.basicquestion.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@SpringBootTest(classes = {BasicQuestionApplication.class})
@ActiveProfiles("test")
@WebAppConfiguration
@SpringJUnitConfig
class CarRepositoryTest {
    public static final double RADIUS_IN_KM_AROUND_TEL_AVIV_THAT_INCLUDES_HAIFA = 85.0;
    public static final double RADIUS_IN_KM_AROUND_TEL_AVIV_THAT_DOES_NOT_INCLUDE_JERUSALEM = 40.0;
    @Autowired
    TestService ts;
    @Autowired
    AppController appController;

    
    @Test
    public void checkInsertTest() {
        Car testCar = ts.insertCarInLocation(Locations.ASHDOD);

        Car insertedCar = (Car) (appController.findCar(Locations.ASHDOD.getCarId()).getBody());

        assertEquals(insertedCar, testCar);
    }


    @Test
    public void checkRadius_success() {
        ts.insertCarInLocation(Locations.HAIFA);

        List<Car> carsInRadius = ts.getCarsAroundLocation(Locations.TEL_AVIV, RADIUS_IN_KM_AROUND_TEL_AVIV_THAT_INCLUDES_HAIFA);

        assertNotNull(ts.filterListByCarId(carsInRadius, Locations.HAIFA.getCarId()));
    }

    @Test
    public void checkRadius_fail() {
        ts.insertCarInLocation(Locations.JERUSALEM);

        List<Car> carsInRadius = ts.getCarsAroundLocation(Locations.TEL_AVIV, RADIUS_IN_KM_AROUND_TEL_AVIV_THAT_DOES_NOT_INCLUDE_JERUSALEM);

        assertNull(ts.filterListByCarId(carsInRadius, Locations.JERUSALEM.getCarId()));
    }

}