package com.handson.basicquestion.Model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Car")
public class Car {
    @Id
    private String id;
    @Indexed(unique = true)
    private String carName;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;

    @Override
    public String toString() {
                return "Car{" +
                                "id='" + id + '\'' +
                                ", name='" + carName + '\'' +
                                ", longitude=" + longitude +
                                ", latitude=" + latitude +
                                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    public static final class CarBuilder {
        private String id;
        private String carName;
        private Double longitude;
        private Double latitude;

        private CarBuilder() {
        }

        public static CarBuilder aCar() {
            return new CarBuilder();
        }

        public CarBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CarBuilder carName(String carName) {
            this.carName = carName;
            return this;
        }

        public CarBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public CarBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.setId(id);
            car.setCarName(carName);
            car.setLongitude(longitude);
            car.setLatitude(latitude);
            return car;
        }
    }
}
