/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import cadastro.alunos.AlunoController;
import cadastro.areas.AreaController;
import entidades.Aluno;
import entidades.Area;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Marcelo
 */
public class ComboBoxArea extends JComboBox {
    
    public void atualizaCombo() {
        removeAllItems();
        List<Area> l = new AreaController().listagem();
        for(Area a : l) {
            addItem(a);
        }
    }
    
    public void atualizaComboComOpcaoTodos() {
        atualizaCombo();
        addItem(Area.TODOS);
        setSelectedItem(Area.TODOS);
    }
    
    public Area getAreaSelecionada() {
        if(getSelectedIndex() != -1) {
            return (Area) getSelectedItem();
        }
        return null;
    }
    
}
