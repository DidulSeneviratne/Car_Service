package com.renuja.carService.models;

public class Appointment {
    private int id;
    private int userId;
    private int ownerId;
    private String status;
    private String problem;
    private String visitType;
    private String location;
    private String name;
    private String phone;

    // constructor
    public Appointment(int id, int userId, int ownerId, String status,
                       String problem, String visitType, String location, String name, String phone) {
        this.id = id;
        this.userId = userId;
        this.ownerId = ownerId;
        this.status = status;
        this.problem = problem;
        this.visitType = visitType;
        this.location = location;
        this.name = name;
        this.phone = phone;
    }

    public Appointment() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
