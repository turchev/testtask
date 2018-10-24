package com.haulmont.testtask.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("72dd8891-47c5-4e15-8129-d3a53898cd5b")
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @objid ("91e0d0e8-6bac-43a1-bf31-1896889b668e")
    @Column(name = "first_name")
    private String firstName = "";

    @objid ("20b46bf1-1fb2-4fc0-8955-810c2484830a")
    @Column(name = "last_name")
    private String lastName = "";

    @objid ("fa7a1b55-aa80-4835-a108-c91a97319954")
    @Column(name = "patronnymic")
    private String patronnymic = "";

    @objid ("af48f55f-af28-4c24-9c92-3598df5660a0")
    @Column(name = "phone")
    private String phone = "";

    @objid ("45af61cc-e142-4c4e-81aa-dd392b719293")
    public String getLastName() {
        return lastName;
    }

    @objid ("01968cef-1e65-4efb-ae62-4899c400e32e")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @objid ("06aa241f-f0b3-4327-8b44-83423e77a655")
    public String getFirstName() {
        return firstName;
    }

    @objid ("298f9d63-e713-49b4-ae03-429386a6010e")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @objid ("9d119a0e-90a3-41f8-85af-8e406df3bf9f")
    public String getPatronnymic() {
        return patronnymic;
    }

    @objid ("443218d4-140c-4b10-96ae-2932a9200e28")
    public void setPatronnymic(String patronnymic) {
        this.patronnymic = patronnymic;
    }

    @objid ("addd0165-127b-436a-a569-0bf3dc97aa7b")
    public String getPhone() {
        return phone;
    }

    @objid ("b722557a-8a5b-4b6e-8a71-47f185f075fa")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @objid ("8a246207-c581-47eb-bb67-7b25fcf77974")
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}
