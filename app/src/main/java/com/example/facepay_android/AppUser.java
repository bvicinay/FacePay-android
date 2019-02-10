package com.example.facepay_android;

public class AppUser {
    public String uid;
    public String name;
    public String customerID;

    public AppUser(String uid, String name, String customerID) {
        this.uid = uid;
        this.name = name;
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setUid(String newID) {
        this.uid = newID;
    }

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getUid() {
        return this.uid;
    }

}
