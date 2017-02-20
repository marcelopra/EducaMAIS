/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marcelo
 */
public class DataBaseConnection {
    
    public static Connection CON;
    private static final String usuario = "root";
    private static final String senha = "admin";
    private static final String url = "jdbc:mysql://localhost:3306/mysql";
    private static final String catalog = "alpha";
    
    public static void conectar() throws SQLException {
        CON = DriverManager.getConnection(url, usuario, senha);
        CON.setAutoCommit(true);
        CON.setCatalog(catalog);
    }
    
}
