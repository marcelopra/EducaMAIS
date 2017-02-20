/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import cadastro.alunos.AlunoController;
import entidades.Aluno;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Marcelo
 */
public class ComboBoxAluno extends JComboBox {
    
    public void atualizaCombo() {
        removeAllItems();
        List<Aluno> l = new AlunoController().listagem();
        for(Aluno a : l) {
            addItem(a);
        }
    }
    
    public void atualizaComboComOpcaoTodos() {
        atualizaCombo();
        addItem(Aluno.TODOS);
        setSelectedItem(Aluno.TODOS);
    }
    
    public Aluno getAlunoSelecionado() {
        if(getSelectedIndex() != -1) {
            return (Aluno) getSelectedItem();
        }
        return null;
    }
    
}
