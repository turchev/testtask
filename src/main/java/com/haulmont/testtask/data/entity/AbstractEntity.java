/**
 * 
 */
package com.haulmont.testtask.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("526e9cf6-9ae4-4b7a-9a47-bca2d9aca95c")
@MappedSuperclass
public abstract class AbstractEntity {
    @objid ("5c1a5caf-84ac-4164-9e55-58c2636f817c")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @objid ("9e5d39de-1102-41f5-af23-5dd25d07fe71")
    long getId() {
        return id;
    }

    @objid ("10ebf2b0-594e-46dc-9b22-0c95d58c5a40")
    void setId(long id) {
        this.id = id;
    }

}
