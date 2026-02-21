package com.renuja.carService.models;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String vehicleModel;
    private String vehicleType;
    private String location;

    public User() {}

    public User(String name, String email, String phone,
                String vehicleModel, String vehicleType, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
