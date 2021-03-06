package cadastro.vendedor;

import entidades.Vendedor;
import gui.DialogPadrao;
import java.awt.Window;
import java.util.List;

public class FormListagemVendedor extends DialogPadrao {
    
    private List<Vendedor> listagem;
    
    public FormListagemVendedor(Window parent) {
        super(parent, ModalityType.MODELESS);
        initComponents();
        
        atualizaListagem();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNovoAnalistaAutomacao = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnExcluirUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePadrao1 = new gui.TablePadrao();

        setTitle("Listagem de Usuários");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnNovoAnalistaAutomacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNovoAnalistaAutomacao.setText("Novo");
        btnNovoAnalistaAutomacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoAnalistaAutomacaoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNovoAnalistaAutomacao);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        btnExcluirUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluirUsuario.setText("Excluir");
        btnExcluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcluirUsuario);

        tablePadrao1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablePadrao1);
        if (tablePadrao1.getColumnModel().getColumnCount() > 0) {
            tablePadrao1.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablePadrao1.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirUsuarioActionPerformed
        new VendedorController().deletar(getUsuarioSelecionado());
        atualizaListagem();
    }//GEN-LAST:event_btnExcluirUsuarioActionPerformed

    private void btnNovoAnalistaAutomacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoAnalistaAutomacaoActionPerformed
        new FormCadastroVendedor().setVisible(true);
        atualizaListagem();
    }//GEN-LAST:event_btnNovoAnalistaAutomacaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new FormCadastroVendedor(getUsuarioSelecionado()).setVisible(true);
        atualizaListagem();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboBoxArea1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxArea1ActionPerformed
        atualizaListagem();
    }//GEN-LAST:event_comboBoxArea1ActionPerformed

    private void atualizaListagem() {
        tablePadrao1.limparTabela();
        listagem = new VendedorController().listagem();
        if(listagem != null) {
            for(Vendedor u : listagem) {
                System.out.println(u.getNome());
                tablePadrao1.adicionarLinha(new Object[] {u.getCodigo(), u.getNome()});
            }
        }
    }
    
    private Vendedor getUsuarioSelecionado() {
        if(tablePadrao1.getSelectedRow() != -1) {
            return listagem.get(tablePadrao1.getSelectedRow());
        }
        return null;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluirUsuario;
    private javax.swing.JButton btnNovoAnalistaAutomacao;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private gui.TablePadrao tablePadrao1;
    // End of variables declaration//GEN-END:variables
}
