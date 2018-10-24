package com.haulmont.testtask.data.dao.exeption;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("52c38a9a-966d-4cc5-b5b0-abf24b6c7c12")
@SuppressWarnings("serial")
public class DaoException extends Exception {
    @objid ("6d9a0602-5655-46e1-b88a-616e709f0513")
    public DaoException(Throwable throwable) {
        super(throwable);
    }

}
