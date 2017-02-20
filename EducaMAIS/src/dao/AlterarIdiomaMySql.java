/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TrataException;

/**
 *
 * @author Zulma
 */
public class AlterarIdiomaMySql extends DAO {
    
    public void setIdiomaPortugues() {
        try {
            conexaoDb.prepareStatement("SET GLOBAL lc_time_names=pt_BR").execute();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
}
