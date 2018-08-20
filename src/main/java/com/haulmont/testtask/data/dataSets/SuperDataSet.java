/**
 * 
 */
package com.haulmont.testtask.data.dataSets;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SuperDataSet {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private long id;

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}
