package com.idsk;

public class UserCredential {
    protected String userName, password,firstName,lastName,qualification;
    protected Integer userID;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getQualification() {
        return qualification;
    }


}