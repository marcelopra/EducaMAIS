package processos.consultar;

import cadastro.alunos.FormSelecionarAluno;
import entidades.Aluno;
import processos.criarpedido.*;
import entidades.Pedido;
import gui.DialogPadrao;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import processos.emitircertificado.EmitirCertificado;

public class FormConsultarPorAluno extends DialogPadrao {
    
    private List<Pedido> listagem;
    
    public FormConsultarPorAluno(Window parent) {
        super(parent, ModalityType.MODELESS);
        initComponents();
        
        tablePadrao1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        comboBoxAluno1.atualizaCombo();
        comboBoxAluno1.setSelectedItem(null);
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablePadrao1 = new gui.TablePadrao();
        jLabel1 = new javax.swing.JLabel();
        comboBoxAluno1 = new gui.ComboBoxAluno();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("Consulta");

        tablePadrao1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso", "Data Início", "Data Conclusão", "Nº de Registro", "Data de Emissão do Certificado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePadrao1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(tablePadrao1);
        if (tablePadrao1.getColumnModel().getColumnCount() > 0) {
            tablePadrao1.getColumnModel().getColumn(0).setPreferredWidth(350);
            tablePadrao1.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablePadrao1.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablePadrao1.getColumnModel().getColumn(3).setPreferredWidth(75);
            tablePadrao1.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Aluno:");

        comboBoxAluno1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxAluno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAluno1ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout());

        jButton2.setText("Reemitir Certificado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton1.setText("Cancelar Curso");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxAluno1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 404, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxAluno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxArea1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxArea1ActionPerformed
        atualizaListagem();
    }//GEN-LAST:event_comboBoxArea1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(getPedidoSelecionado() != null) {
            new PedidoController().cancelarCurso(getPedidoSelecionado());
            atualizaListagem();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboBoxAluno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAluno1ActionPerformed
        atualizaListagem();
    }//GEN-LAST:event_comboBoxAluno1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Aluno a = new FormSelecionarAluno().getAluno();
        if(a != null) {
            comboBoxAluno1.atualizaCombo();
            comboBoxAluno1.setSelectedItem(a);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new EmitirCertificado().emitir(getPedidoSelecionado());
        atualizaListagem();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void atualizaListagem() {
        tablePadrao1.limparTabela();
        listagem = new PedidoController().listagem(comboBoxAluno1.getAlunoSelecionado());
        if(listagem != null) {
            for(Pedido p : listagem) {
                if(p.isCancelado()) {
                    tablePadrao1.adicionarLinha(new Object[] {getTextRiscado(p.getCurso().getNome()), getTextRiscado(p.getDataInicioToString()), 
                        getTextRiscado(p.getDataTerminoToString()), getTextRiscado(p.getNumeroRegistroToString()), 
                        getTextRiscado(p.getDataEmissaoCertificadoToString())});
                } else {
                    tablePadrao1.adicionarLinha(new Object[] {p.getCurso().getNome(), p.getDataInicioToString(), 
                        p.getDataTerminoToString(), p.getNumeroRegistroToString(), 
                        p.getDataEmissaoCertificadoToString()});
                }
            }
        }
        tablePadrao1.selectAndScroll(0);
    }
    
    private List<Pedido> getPedidosSelecionados() {
        List<Pedido> listagemPedidosSel = new ArrayList<>();
        for(int i : tablePadrao1.getSelectedRows()) {
            listagemPedidosSel.add(listagem.get(i));
        }
        return listagemPedidosSel;
    }
    
    private Pedido getPedidoSelecionado() {
        if(tablePadrao1.getSelectedRow() != -1) {
            return listagem.get(tablePadrao1.getSelectedRow());
        }
        return null;
    }
    
    private String getTextRiscado(String text) {
        return "<html><strike>" + text + "</html></strike>";
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.ComboBoxAluno comboBoxAluno1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private gui.TablePadrao tablePadrao1;
    // End of variables declaration//GEN-END:variables
}