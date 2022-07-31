package com.vdqdryoy.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserProfile {
    // User Entity
    private int userProfileId;
    private String id;
    private String firstName;
    private String lastName;
    private String officeId;
    private String profileId;
    private String address;
    private int accountStatus;
    private Date birthDate;
    // Profile Entity
    private String gender;
    private String phoneNumber;
    private String career;


    public UserProfile() {
    }

    public UserProfile(int userProfileId, String firstName) {
        this.userProfileId = userProfileId;
        this.firstName = firstName;
    }

    public UserProfile(String id, String firstName, String lastName, String officeId, String profileId, String address, int accountStatus, Date birthDate, String gender, String phoneNumber, String career) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeId = officeId;
        this.profileId = profileId;
        this.address = address;
        this.accountStatus = accountStatus;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.career = career;
    }

    public int getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(int userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
