package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7e1a871e-d911-4d2d-a482-d593d36fad50")
public interface SaveAndEdit {
    @objid ("104410f8-9160-42e6-b167-15015a5159d7")
    void save();

    @objid ("77c26872-2bfd-4126-8d9d-63fbdd1b2285")
    void cancel();

    @objid ("ee063386-29e3-4d5a-8230-3c19130571eb")
    void update();

}
