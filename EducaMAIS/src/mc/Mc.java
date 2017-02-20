/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc;

import dao.AlterarIdiomaMySql;
import dao.DataBaseConnection;
import gui.LookAndFeelUtil;
import java.sql.SQLException;
import javax.swing.UnsupportedLookAndFeelException;
import util.TrataException;
import view.MainWindow;

/**
 *
 * @author u0181181
 */
public class Mc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            /*Atualiza o tema das janelas conforme o tema do Windows*/
            new LookAndFeelUtil().setTemaSistemaOperacional();
            
            DataBaseConnection.conectar();
            
            new AlterarIdiomaMySql().setIdiomaPortugues();
            
            DataBaseConnection.CON.close();
            DataBaseConnection.conectar();
            
            //new Login().logar();
            
            new MainWindow().setVisible(true);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        } catch (ClassNotFoundException ex) {
            TrataException.fatal(ex);
        } catch (InstantiationException ex) {
            TrataException.fatal(ex);
        } catch (IllegalAccessException ex) {
            TrataException.fatal(ex);
        } catch (UnsupportedLookAndFeelException ex) {
            TrataException.fatal(ex);
        }
    }
    
}
