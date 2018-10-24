package com.haulmont.testtask.data.entity;

import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("37d9b8f5-096a-4b6c-8106-94d530444b9e")
@Entity
@Table(name = "mechanic")
public class Mechanic extends AbstractEntity {
    @objid ("c9ebff9c-d576-4012-968e-95a431be2ef2")
    @Column(name = "first_name")
    private String firstName = "";

    @objid ("01c1231e-bd18-4973-bb71-40e82914f0ca")
    @Column(name = "last_name")
    private String lastName = "";

    @objid ("29411397-0966-42f8-a284-1ef9218ffbf7")
    @Column(name = "patronnymic")
    private String patronnymic = "";

    @objid ("7636f399-cf61-4b77-b3b4-b802839db51b")
    @Column(name = "wages")
    private Currency wages = Currency.getInstance("RUB");

    @objid ("3436b4f2-ef9e-4b20-b164-d21341ad36e3")
    public String getFirstName() {
        return firstName;
    }

    @objid ("a83ac8bf-ddf6-4f80-8e4b-007d224a9f4a")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @objid ("8fee3a29-00ca-443b-a9d9-c5ce10fcb114")
    public String getLastName() {
        return lastName;
    }

    @objid ("6ff98f8f-0707-427c-a3b7-1c3c507d0aba")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @objid ("218de782-3669-4d29-96a2-a804dafef24c")
    public String getPatronnymic() {
        return patronnymic;
    }

    @objid ("02c4a03b-6fde-42ed-ade0-49372d2e4ece")
    public void setPatronnymic(String patronnymic) {
        this.patronnymic = patronnymic;
    }

    @objid ("cc80dcd6-be2b-499e-b227-1aee74cb0c4f")
    public Currency getWages() {
        return wages;
    }

    @objid ("d7f52c58-7b1c-4104-a8e8-836c910001ac")
    public void setWages(Currency wages) {
        this.wages = wages;
    }

    @objid ("0653ca64-0580-470d-95e9-181b66f79f3a")
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}
