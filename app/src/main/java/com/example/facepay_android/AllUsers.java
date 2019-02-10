package com.example.facepay_android;


import java.util.HashMap;

public class AllUsers {

    public static HashMap<String, AppUser> users;

    public AllUsers() {
        /*
        AppUser borja = new AppUser();
        AppUser colton = new AppUser();
        AppUser ed = new AppUser();
        AppUser diego = new AppUser();
        addUsers(borja, colton, ed, diego);
        */

    }

    public static void addUsers(AppUser... users) {
        for (AppUser u : users) {
            addUser(u);
        }
    }
    public static void addUser(AppUser new_user) {
        AllUsers.users.put(new_user.getUid(), new_user);
    }

    public static AppUser getUser(String uid) {

        return AllUsers.users.get(uid);
    }
}
