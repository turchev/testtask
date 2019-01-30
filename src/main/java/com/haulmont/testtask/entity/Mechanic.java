package com.haulmont.testtask.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mechanic")
public class Mechanic extends AbstractEntity {    
    
    @Column(name = "last_name")
    private String lastName = "";
    
    @Column(name = "first_name")
    private String firstName = "";
    
    @Column(name = "patronnymic")
    private String patronnymic = "";
    
    @Column(name = "wages")
    private BigDecimal wages;       
    
    public Mechanic() {		
	}
      
    public Mechanic(String lastName, String firstName, String patronnymic) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronnymic = patronnymic;
	}	

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
    
    public BigDecimal getWages() {
        return wages;
    }
    
    public void setWages(BigDecimal wages) {
        this.wages = wages;
    }
    
    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronnymic;
    }
}
