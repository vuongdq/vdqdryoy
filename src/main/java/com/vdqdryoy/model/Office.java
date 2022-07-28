package com.vdqdryoy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "office")
public class Office {
    @Id
    private String id;
    private String name;
    private String phone;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date deletedDate;

    public Office() { }

    public Office(String id, String name, String phone, Date deletedDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.deletedDate = deletedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
