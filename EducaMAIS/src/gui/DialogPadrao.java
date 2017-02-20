/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author marcelo
 */
public class DialogPadrao extends JDialog {
 
    public DialogPadrao(Window parent, ModalityType modalityType) {
        super(parent, modalityType);
        
        //this.setIconImage(new ImageIcon(FormCadastroPadrao.class.getResource("/imagens/iconeAutomacao.png")).getImage());
        
        teclaEsc();
    }
    
    public DialogPadrao() {
        this(null, ModalityType.APPLICATION_MODAL);
    }
    
    @Override
    public void setVisible(boolean valor) {
        this.setLocationRelativeTo(null);
        super.setVisible(valor);
    }
    
    private void teclaEsc() {
        JRootPane meurootpane = getRootPane();  
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");  
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
            @Override
            public void actionPerformed(ActionEvent e) {  
                dispose();
            }});
    }
    
}
