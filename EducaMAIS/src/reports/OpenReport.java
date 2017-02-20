/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import dao.DataBaseConnection;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.TrataException;

/**
 *
 * @author marcelo
 */
public class OpenReport {
    
    public void abrir(String arquivoJasper, Map<String, Object> parametros) {
        try {
            
            System.out.println("Relatório: " + arquivoJasper);
            
            JasperPrint printReport = JasperFillManager.fillReport(getClass().getResourceAsStream(arquivoJasper), parametros, DataBaseConnection.CON);
            
            JasperViewer jrviewer = new JasperViewer(printReport, false);
            jrviewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            jrviewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            jrviewer.setVisible(true);  
            
        } catch (JRException ex) {
            TrataException.fatal(ex);
        }
    }
    
}
