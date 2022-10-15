package com.handson.basicquestion.Model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && longitude.equals(car.longitude) && latitude.equals(car.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude);
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
