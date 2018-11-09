package com.haulmont.testtask.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("cc577e02-5636-4bf1-bb02-81a01b95833f")
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @objid ("0f4facb0-4eaf-4252-9e49-443be3406806")
    @Column(name = "first_name")
    private String firstName = "";

    @objid ("e758c4ad-a3ff-48a1-8804-efb04b851627")
    @Column(name = "last_name")
    private String lastName = "";

    @objid ("ff071594-428e-44c5-a78b-55f97584f541")
    @Column(name = "patronnymic")
    private String patronnymic = "";

    @objid ("cfc09e73-5e9e-4bc9-b830-22d05cff6068")
    @Column(name = "phone")
    private String phone = "";

    @objid ("2443f19a-b181-4861-a35a-be547ead1caa")
    public String getLastName() {
        return lastName;
    }

    @objid ("9aec9f22-a4b3-43df-8d2e-66d5428c72e5")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @objid ("47cf9314-5eb2-4f1f-ba23-22bc492b7d04")
    public String getFirstName() {
        return firstName;
    }

    @objid ("0849b986-5be8-430a-97d7-0d6d6c43ef4d")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @objid ("bfeb1217-e765-4eba-a3d8-816dd7e918ba")
    public String getPatronnymic() {
        return patronnymic;
    }

    @objid ("5154f7b6-cef4-400f-b12a-2e88f468f201")
    public void setPatronnymic(String patronnymic) {
        this.patronnymic = patronnymic;
    }

    @objid ("e18a7c48-84ff-464c-8590-29cf238e0bb6")
    public String getPhone() {
        return phone;
    }

    @objid ("92504828-6306-42a6-bce4-f64bc86e2a33")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @objid ("c2df3695-32c6-465b-af83-4332ca9fd0da")
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}
