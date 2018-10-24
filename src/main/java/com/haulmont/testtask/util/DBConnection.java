package com.haulmont.testtask.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("13b6e265-b088-4954-a9ac-c9201d3c6291")
public class DBConnection {
    @objid ("ee8a2283-50dc-43b4-9007-4a6154524b42")
    public static Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            System.out.println("HSQLDB JDBCDriver Loaded");
            con = DriverManager.getConnection(
                    "jdbc:hsqldb:file:db/car_service_db;shutdown=true;ifexists=false", "SA", "");
            System.out.println("HSQLDB Connection Created");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
