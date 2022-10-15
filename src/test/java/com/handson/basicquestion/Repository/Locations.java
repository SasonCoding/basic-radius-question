package com.handson.basicquestion.Repository;

public enum Locations {
    ASHDOD("123", 31.80056005890851, 34.66956283384276),
    TEL_AVIV("456", 32.09304777948897, 34.77667953145146),
    HAIFA("789", 32.798820559113004, 34.99228621847847),
    JERUSALEM("1234", 31.76594383874285, 35.21847275657447);
    String carId;
    Double latitude;
    Double longitude;

    public String getCarId() {
        return carId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    Locations(String carId, double latitude, double longitude) {
        this.carId = carId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
