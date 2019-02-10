package com.example.facepay_android;

public class PaymentHandler {

    private String uid;
    private AppUser user;

    public PaymentHandler(String uid) {
        this.uid = uid;

        this.user = getUser(uid);


    }

    private AppUser getUser(String uid) {

        return null;
    }
}
