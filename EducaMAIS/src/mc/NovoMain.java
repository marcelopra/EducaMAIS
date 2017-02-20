/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc;

import dao.DataBaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        DataBaseConnection.conectar();
        
        PreparedStatement stmt = DataBaseConnection.CON.prepareStatement("SELECT CODIGO "
                                                                       + "FROM PEDIDOSS P "
                                                                       + "ORDER BY NUMERO_REGISTRO");
        ResultSet rs = stmt.executeQuery();
        int cont = 1;
        while(rs.next()) {
            PreparedStatement stmtInsert = DataBaseConnection.CON.prepareStatement("UPDATE PEDIDOSS SET NUMERO_REGISTRO_NOVO=? WHERE CODIGO=?");
            stmtInsert.setInt(1, cont);
            stmtInsert.setInt(2, rs.getInt("CODIGO"));
            stmtInsert.executeUpdate();
            cont++;
        }
        
    }
    
}
