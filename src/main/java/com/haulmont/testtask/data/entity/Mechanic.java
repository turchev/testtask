package com.haulmont.testtask.data.entity;

import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("5824edf0-7629-445a-939f-3e96551e933c")
@Entity
@Table(name = "mechanic")
public class Mechanic extends AbstractEntity {
    @objid ("3a5d002d-e37a-4f74-b993-4bbbb222e097")
    @Column(name = "first_name")
    private String firstName = "";

    @objid ("7f2a0c5e-0be7-43bd-a7de-76dc14244358")
    @Column(name = "last_name")
    private String lastName = "";

    @objid ("bbb09d4e-a96e-4ee2-a57c-bf3321c51117")
    @Column(name = "patronnymic")
    private String patronnymic = "";

    @objid ("be89d255-e292-422d-95da-6a4e6d97bc8c")
    @Column(name = "wages")
    private Currency wages = Currency.getInstance("RUB");

    @objid ("c63524d1-82c1-40da-b0f8-1b59590d0b39")
    public String getFirstName() {
        return firstName;
    }

    @objid ("33c7f562-b1aa-48b8-93e9-cb517123ec65")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @objid ("a3141885-883f-46aa-a123-730eeedd6bef")
    public String getLastName() {
        return lastName;
    }

    @objid ("657cacc5-0cc0-4fb2-a8a4-a53ba7e45a05")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @objid ("2d9c04bd-3527-420e-a2af-43ac5f4f6fa3")
    public String getPatronnymic() {
        return patronnymic;
    }

    @objid ("7ce1556a-a51d-4b8c-9752-83b1a905cc82")
    public void setPatronnymic(String patronnymic) {
        this.patronnymic = patronnymic;
    }

    @objid ("e94c718f-f58b-4967-938b-f4ab6517fba7")
    public Currency getWages() {
        return wages;
    }

    @objid ("088149f0-01c3-4365-9dba-d0acdaa07b16")
    public void setWages(Currency wages) {
        this.wages = wages;
    }

    @objid ("bf0c29f0-7ea2-43c0-99c1-6231ef1903ed")
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}
