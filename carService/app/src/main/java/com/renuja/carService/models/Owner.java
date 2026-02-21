package com.renuja.carService.models;

public class Owner {
    private int id;
    private static String businessName;
    private String businessRegNo;
    private String businessType;
    private static String address;
    private static String phone;
    private String description;
    private float rating;


    public Owner() {}

    public Owner(int id, String businessName, String businessRegNo, String businessType,
                 String address, String phone, String description, float rating) {
        this.id = id;
        this.businessName = businessName;
        this.businessRegNo = businessRegNo;
        this.businessType = businessType;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.rating = rating;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public float getRating() {return rating;}

    public void setRating(float rating) {this.rating = rating;}

    public static String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessRegNo() {
        return businessRegNo;
    }

    public void setBusinessRegNo(String businessRegNo) {
        this.businessRegNo = businessRegNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public static String getAddress() {
        return address;
    }

    public void setAddress(String location) {
        this.address = location;
    }

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
