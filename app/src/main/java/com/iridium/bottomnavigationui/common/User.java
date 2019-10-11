package com.iridium.bottomnavigationui.common;

public class User
{
    String userName;
    String userPhone;
    String userEmail;
    String imageURL;

    public User()
    {

    }

    public User(String name, String phone, String imageURL)
    {
        this.userName =name;
        this.userPhone = phone;
        this.imageURL = imageURL;
    }

    public User(String name, String phone, String userEmail, String imageURL)
    {
        this.userName =name;
        this.userPhone = phone;
        this.imageURL = imageURL;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
