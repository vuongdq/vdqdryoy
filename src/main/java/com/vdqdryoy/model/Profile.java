package com.vdqdryoy.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
public class Profile {
    @Id
    private String id;
    private String gender;
    private String phoneNumber;
    private String career;

    public Profile() {
    }

    public Profile(String id, String gender, String phoneNumber, String career) {
        this.id = id;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.career = career;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
