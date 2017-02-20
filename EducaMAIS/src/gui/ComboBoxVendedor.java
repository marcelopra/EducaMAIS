/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import cadastro.alunos.AlunoController;
import cadastro.vendedor.VendedorController;
import entidades.Aluno;
import entidades.Vendedor;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Marcelo
 */
public class ComboBoxVendedor extends JComboBox {
    
    public void atualizaCombo() {
        removeAllItems();
        List<Vendedor> l = new VendedorController().listagem();
        for(Vendedor v : l) {
            addItem(v);
        }
    }
    
    public void atualizaComboComOpcaoTodos() {
        atualizaCombo();
        addItem(Vendedor.TODOS);
        setSelectedItem(Vendedor.TODOS);
    }
    
    public Vendedor getVendedorSelecionado() {
        if(getSelectedIndex() != -1) {
            return (Vendedor) getSelectedItem();
        }
        return null;
    }
    
}
