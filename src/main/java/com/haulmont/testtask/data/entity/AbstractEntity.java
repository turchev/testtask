/**
 * 
 */
package com.haulmont.testtask.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("be92bb14-a3f5-4571-a2ca-990153c6bbfc")
@MappedSuperclass
public abstract class AbstractEntity {
    @objid ("d6a9bad3-cb02-46a4-b3b3-6e478f8aa6e4")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @objid ("ce5902c7-1c29-4567-9ee4-7b3f4e598f6d")
    long getId() {
        return id;
    }

    @objid ("892d03a7-b498-4033-a8ae-a99dd1343eac")
    void setId(long id) {
        this.id = id;
    }

}
