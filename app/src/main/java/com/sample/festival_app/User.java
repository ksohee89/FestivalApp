package com.sample.festival_app;

public class User {

    public String userId, userPw, userName, userTel;
    public int userPoint;

    public User(){

    }

    public User(String userId, String userPw, String userName, String userTel, int userPoint) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userTel = userTel;
        this.userPoint = userPoint;
    }


}