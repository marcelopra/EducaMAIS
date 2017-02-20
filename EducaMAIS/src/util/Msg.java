/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author marcelo
 */
public class Msg {
    
    private Component parent;
    
    public Msg(Component parent) {
        this.parent = parent;
    }
    
    public Msg() {
        this(null);
    }
    
    public void msgAtencao(final String msg) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    public String inputDialog(final String msg) {
        return JOptionPane.showInputDialog(null, msg);
    }
    
    public void msgInformacao(final String msg, final String titulo) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    public void msgErro(final String msg) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    public int msgConfirmacaoExclusao() {
        int op = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o item selecionado?", "Confirmar Exclusão", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sim", "Não"}, "Não");
        return op;
    }
    
    public int msgOpcoes(String msg, String[] opcoes) {
        int op = JOptionPane.showOptionDialog(null, msg, "Confirmar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, null);
        return op;
    }
    
    public int msgConfirmacao(String msg) {
        int op = JOptionPane.showOptionDialog(null, msg, "Confirmar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sim", "Não"}, "Não");
        return op;
    }
}
