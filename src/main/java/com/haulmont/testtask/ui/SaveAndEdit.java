package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("2436f1f2-2b17-4287-956d-f996c4ceaa7b")
public interface SaveAndEdit {
    @objid ("87f6c8cb-19fb-4714-86c2-94703bd3395d")
    void save();

    @objid ("5a0b2161-2f7c-43eb-b894-302099bdff68")
    void cancel();

    @objid ("e368cdb7-ce35-4523-a9df-f4fdc61c4414")
    void update();

}
