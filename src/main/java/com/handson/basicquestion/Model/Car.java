package com.handson.basicquestion.Model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Car")
public class Car {
    @Id
    private String id;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;

    @Override
    public String toString() {
                return "Car{" +
                                "id='" + id + '\'' +
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

    @NonNull
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull Double longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull Double latitude) {
        this.latitude = latitude;
    }


    public static final class CarBuilder {
        private String id;
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
            car.setLongitude(longitude);
            car.setLatitude(latitude);
            return car;
        }
    }
}
