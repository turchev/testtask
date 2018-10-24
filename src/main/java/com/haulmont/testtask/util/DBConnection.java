package com.haulmont.testtask.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("2cafd17d-69c7-4062-869f-55d9c6bd9d78")
public class DBConnection {
    @objid ("1cf13b37-cc4c-4a85-963b-73dd0eb486f3")
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
