package com.haulmont.testtask.data.dbService;

import java.util.List;

import com.haulmont.testtask.data.dataSets.SuperDataSet;

public interface DBService {
	
	String getLocalStatus();
    void save();
    <T extends SuperDataSet> T read(long id);    
    List<?> readAll();
    void shutdown();
    
}
