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

@objid ("88df2090-b4f5-4afa-b720-06ae8808bcb0")
@Entity
@Table(name = "order")
public class Order extends AbstractEntity {
    @objid ("38848aea-e92c-49a4-87c3-d0d3078b6585")
    @Column(name = "description")
    private String description = "";

    @objid ("be00852f-75be-47ca-908c-2d5606c790e6")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Long clientId;

    @objid ("baa34cff-d2c7-4ea7-a7c2-9674d475282a")
    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Long mechanicId;

    @objid ("b42ee264-2f11-46ab-9ae6-ff0d661a0815")
    @Column(name = "status")
    private String status;

    @objid ("95e08821-1a1d-4e83-acd6-3f69b4cf588e")
    @Column(name = "date_creat")
    private LocalDate dateCreat;

    @objid ("59877acc-3cc0-43c4-8a1f-fbab4b5fa33a")
    @Column(name = "completion_date")
    private LocalDate completionDate;

    @objid ("cfb6132b-f4ca-4447-a8cb-a8a9bf861be8")
    @Column(name = "price")
    private Currency price = Currency.getInstance("RUB");

    @objid ("9a3a7488-f7b5-43d1-b38c-0acccc16037a")
    public String getDescription() {
        return description;
    }

    @objid ("0dfae66d-b6e9-4b4f-93fa-8a7de06895ff")
    public void setDescription(String description) {
        this.description = description;
    }

    @objid ("fb2a8824-c2dd-44f8-a332-0e2f263821c7")
    public Long getClientId() {
        return clientId;
    }

    @objid ("ef576714-f017-4159-9c1c-125617e4cb40")
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @objid ("99da2e07-f32c-49ad-8d9e-a60c42642f75")
    public Long getMechanicId() {
        return mechanicId;
    }

    @objid ("49ca6c34-ee62-426f-87d4-286906348e4e")
    public void setMechanicId(Long mechanicId) {
        this.mechanicId = mechanicId;
    }

    @objid ("1533a9df-1d1a-4ae2-ba4e-b9c336a495bf")
    public LocalDate getDateCreat() {
        return dateCreat;
    }

    @objid ("3245f39c-48d9-4fb6-96fa-fa2e1b2efd60")
    public void setDateCreat(LocalDate dateCreat) {
        this.dateCreat = dateCreat;
    }

    @objid ("74e14113-b7d1-4d48-bda8-0d3d8ba71b12")
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    @objid ("2c19c19b-b3d8-4d90-9529-6aef16dbd336")
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    @objid ("f890deb5-753d-49fc-bb1c-ca560eec755f")
    public Currency getPrice() {
        return price;
    }

    @objid ("21ebf489-7fc7-4d32-8ab1-285941f054cc")
    public void setPrice(Currency price) {
        this.price = price;
    }

    @objid ("6e506d99-fa3e-48e4-8dcf-9d5a4827ecda")
    public String getStatus() {
        return status;
    }

    @objid ("e5d1ec49-68de-4430-9932-50f2801b3677")
    public void setStatus(OrderStatusType status) {
        this.status = status.name();
    }

    @objid ("00a731b3-649e-4ea1-8915-53f577f95acc")
    @Override
    public String toString() {
        return description + " " + status;
    }

}
