package com.haulmont.testtask.data.entity;

import java.time.LocalDate;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.haulmont.testtask.util.OrderStatusType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("1da13095-1cbd-468e-8dbd-533c3f7d47da")
@Entity
@Table(name = "order")
public class Order extends AbstractEntity {
    @objid ("dc477dbc-ffe8-408a-948e-cd0da611ec06")
    @Column(name = "description")
    private String description = "";

    @objid ("669a2569-1414-4ba2-a555-16bc2d8cba62")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Long clientId;

    @objid ("7fab71ea-e81d-4657-91cd-94b6bd2d3047")
    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Long mechanicId;

    @objid ("fe219bda-dbe9-43d3-8a5b-652a91f24391")
    @Column(name = "status")
    private String status;

    @objid ("3e1ce512-d56e-4e36-9543-9834e5f43279")
    @Column(name = "date_creat")
    private LocalDate dateCreat;

    @objid ("3c5523e7-3ef3-437c-8079-c200a2f1b58a")
    @Column(name = "completion_date")
    private LocalDate completionDate;

    @objid ("bcbfd16d-1af2-487a-8c54-b7f7f0d59821")
    @Column(name = "price")
    private Currency price = Currency.getInstance("RUB");

    @objid ("516d7766-1904-4e52-a936-1f00cda63817")
    public String getDescription() {
        return description;
    }

    @objid ("231c882d-cecc-434e-b696-2db1c2aea3f2")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("f5adaddc-0aae-4e98-97cd-edb37f7efa0e")
    public Long getClientId() {
        return clientId;
    }

    @objid ("1d043e37-fc3f-4a8a-8fd0-d22304eb4c26")
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @objid ("4cad4ed0-22f0-4968-a881-c953a9b04b72")
    public Long getMechanicId() {
        return mechanicId;
    }

    @objid ("993579e7-ef47-4db1-a467-6b08644fc9ee")
    public void setMechanicId(Long mechanicId) {
        this.mechanicId = mechanicId;
    }

    @objid ("1dd2a600-d1bf-4903-bfca-26be3a142d02")
    public LocalDate getDateCreat() {
        return dateCreat;
    }

    @objid ("69e7c5b4-b4b3-4339-afd8-16d46e2cd4f1")
    public void setDateCreat(LocalDate dateCreat) {
        this.dateCreat = dateCreat;
    }

    @objid ("c9fba36f-944d-4f39-a45e-529c6638d0d7")
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    @objid ("5c052846-e6c1-45a1-84c4-5e769cda0487")
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    @objid ("691409c1-e9ac-4598-a5bd-8ab8271c121e")
    public Currency getPrice() {
        return price;
    }

    @objid ("1be7f442-f1ca-4c0e-9208-fafcbdaef9ad")
    public void setPrice(Currency price) {
        this.price = price;
    }

    @objid ("d220d3a4-cb68-4d63-bbf8-316067ef72fc")
    public String getStatus() {
        return status;
    }

    @objid ("97aa6d7a-14df-4c67-a8ca-14e4f4c61b62")
    public void setStatus(OrderStatusType status) {
        this.status = status.name();
    }

    @objid ("cf96ab66-20f8-4103-ba67-4d8f14d57a39")
    @Override
    public String toString() {
        return description + " " + status;
    }

}
