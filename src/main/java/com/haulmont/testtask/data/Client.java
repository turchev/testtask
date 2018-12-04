package com.haulmont.testtask.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    
    @Column(name = "first_name")
    private String firstName = "";
    
    @Column(name = "last_name")
    private String lastName = "";
    
    @Column(name = "patronnymic")
    private String patronnymic = "";
    
    @Column(name = "phone")
    private String phone = "";
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getPatronnymic() {
        return patronnymic;
    }
    
    public void setPatronnymic(String patronnymic) {
        this.patronnymic = patronnymic;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
