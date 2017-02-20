package cadastro.cursos;

import cadastro.areas.FormCadastroArea;
import entidades.Aluno;
import entidades.Apostila;
import entidades.Assunto;
import entidades.Curso;
import entidades.ValidacaoEntidadeException;
import gui.DialogPadrao;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Msg;

public class FormCadastroCurso extends DialogPadrao {

    public int MODO = 0;
    public int INCLUSAO = 1;
    public int EDICAO = 2;
    
    private Curso curso;
    private List<Apostila> listagemApostilas;
    private List<Assunto> listagemAssuntos;
    private boolean novoApartirDeste;

    public FormCadastroCurso() {
        initComponents();
        MODO = INCLUSAO;
        this.listagemApostilas = new ArrayList<>();
        this.listagemAssuntos = new ArrayList<>();
        this.curso = new Curso();
        
        comboBoxArea1.atualizaCombo();
        comboBoxArea1.setSelectedItem(null);
        btnEditarApostila.setEnabled(false);
    }
    
    /*MOO EDIÇÃO*/
    public FormCadastroCurso(Curso cursParaEditar, boolean novoApartirDeste) {
        this();
        MODO = EDICAO;
        this.curso = cursParaEditar;
        this.novoApartirDeste = novoApartirDeste;
        setCurso(cursParaEditar);
        btnEditarApostila.setEnabled(true);
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
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabApostilas = new gui.TablePadrao();
        jPanel5 = new javax.swing.JPanel();
        btnAdicionarApostila = new javax.swing.JButton();
        btnExcluirApostila = new javax.swing.JButton();
        btnEditarApostila = new javax.swing.JButton();
        comboBoxArea1 = new gui.ComboBoxArea();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabAssuntos = new gui.TablePadrao();
        jPanel7 = new javax.swing.JPanel();
        btnAdicionarAssunto = new javax.swing.JButton();
        btnExcluirAssunto = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCargaHoraria = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Cursos");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnGravar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGravar);

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Área:");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Apostilas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tabApostilas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Arquivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabApostilas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabApostilas);
        if (tabApostilas.getColumnModel().getColumnCount() > 0) {
            tabApostilas.getColumnModel().getColumn(0).setPreferredWidth(800);
        }

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        btnAdicionarApostila.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdicionarApostila.setText("Adicionar");
        btnAdicionarApostila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarApostilaActionPerformed(evt);
            }
        });
        jPanel5.add(btnAdicionarApostila);

        btnExcluirApostila.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluirApostila.setText("Excluir");
        btnExcluirApostila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirApostilaActionPerformed(evt);
            }
        });
        jPanel5.add(btnExcluirApostila);

        btnEditarApostila.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarApostila.setText("Editar Apostila");
        btnEditarApostila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarApostilaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarApostila)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarApostila)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 47, Short.MAX_VALUE)))
        );

        comboBoxArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Nova Área");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comboBoxArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Geral", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Grade de Assuntos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tabAssuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Assunto", "Carga Horária"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabAssuntos);
        if (tabAssuntos.getColumnModel().getColumnCount() > 0) {
            tabAssuntos.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabAssuntos.getColumnModel().getColumn(1).setPreferredWidth(600);
            tabAssuntos.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btnAdicionarAssunto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdicionarAssunto.setText("Adicionar");
        btnAdicionarAssunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarAssuntoActionPerformed(evt);
            }
        });
        jPanel7.add(btnAdicionarAssunto);

        btnExcluirAssunto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluirAssunto.setText("Excluir");
        btnExcluirAssunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAssuntoActionPerformed(evt);
            }
        });
        jPanel7.add(btnExcluirAssunto);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Carga Horária Total do Curso:");

        txtCargaHoraria.setEditable(false);
        txtCargaHoraria.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCargaHoraria.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCargaHoraria.setText("0");
        txtCargaHoraria.setToolTipText("");
        txtCargaHoraria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("horas");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(51, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Carga Horária", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        try {
            getAluno().valida();
            if(MODO == INCLUSAO || novoApartirDeste) {
                new CursoController().salvar(getAluno());
            } else {
                new CursoController().atualizar(getAluno());
            }
            this.dispose();
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAdicionarApostilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarApostilaActionPerformed
        Apostila a = new FormCadastroApostila().getApostilaRtn();
        if(a != null) {
            listagemApostilas.add(a);
            atualizaListagemApostilas();
        }
    }//GEN-LAST:event_btnAdicionarApostilaActionPerformed

    private void btnExcluirApostilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirApostilaActionPerformed
        if(tabApostilas.getSelectedRow() != -1) {
            listagemApostilas.remove(tabApostilas.getSelectedRow());
            atualizaListagemApostilas();
        } else {
            new Msg().msgAtencao("Nenhuma apostila selecionada.");
        }
    }//GEN-LAST:event_btnExcluirApostilaActionPerformed

    private void btnAdicionarAssuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarAssuntoActionPerformed
        Assunto a = new FormCadastroAssunto().getApostilaRtn();
        if(a != null) {
            listagemAssuntos.add(a);
            atualizaListagemAssuntos();
        }
    }//GEN-LAST:event_btnAdicionarAssuntoActionPerformed

    private void btnExcluirAssuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAssuntoActionPerformed
        if(tabAssuntos.getSelectedRow() != -1) {
            listagemAssuntos.remove(tabAssuntos.getSelectedRow());
            atualizaListagemAssuntos();
        } else {
            new Msg().msgAtencao("Nenhum assunto selecionado.");
        }
    }//GEN-LAST:event_btnExcluirAssuntoActionPerformed

    private void btnEditarApostilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarApostilaActionPerformed
        if(tabApostilas.getSelectedRow() != -1) {
            try {
                Desktop.getDesktop().edit(new File(getApostilaSelecionada().getCaminhoCompleto()));
            } catch (IOException ex) {
                new Msg().msgAtencao("Erro ao editar a apostila: " + ex.getMessage());
            }
        } else {
            new Msg().msgAtencao("Nenhuma apostila selecionada.");
        }
    }//GEN-LAST:event_btnEditarApostilaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FormCadastroArea().setVisible(true);
        comboBoxArea1.atualizaCombo();
        comboBoxArea1.setSelectedItem(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void setCurso(Curso curso) {
        txtNome.setText(curso.getNome());
        comboBoxArea1.setSelectedItem(curso.getArea());
        txtCargaHoraria.setText(String.valueOf(curso.getCargaHoraria()));
        listagemApostilas = curso.getListagemApostilas();
        listagemAssuntos = curso.getListagemAssuntos();
        
        atualizaListagemApostilas();
        atualizaListagemAssuntos();
    }
    
    private Curso getAluno() {
        curso.setNome(txtNome.getText());
        curso.setArea(comboBoxArea1.getAreaSelecionada());
        curso.setCargaHoraria(Integer.valueOf(txtCargaHoraria.getText()));
        curso.setListagemApostilas(listagemApostilas);
        curso.setListagemAssuntos(listagemAssuntos);
        return curso;
    }
    
    private void atualizaListagemApostilas() {
        tabApostilas.limparTabela();
        if(listagemApostilas != null) {
            for(Apostila a : listagemApostilas) {
                tabApostilas.adicionarLinha(new Object[] {a.getNomeArquivo(), a.getDescricao()});
            }
        }
    }
    
    private Apostila getApostilaSelecionada() {
        if(tabApostilas.getSelectedRow() != -1) {
            return listagemApostilas.get(tabApostilas.getSelectedRow());
        }
        return null;
    }
    
    private void atualizaListagemAssuntos() {
        tabAssuntos.limparTabela();
        if(listagemAssuntos != null) {
            int i = 1;
            int totalCargaHoraria = 0;
            for(Assunto a : listagemAssuntos) {
                tabAssuntos.adicionarLinha(new Object[] {i, a.getAssunto(), a.getCargaHoraria()});
                i++;
                totalCargaHoraria += a.getCargaHoraria();
            }
            txtCargaHoraria.setText(String.valueOf(totalCargaHoraria));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarApostila;
    private javax.swing.JButton btnAdicionarAssunto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarApostila;
    private javax.swing.JButton btnExcluirApostila;
    private javax.swing.JButton btnExcluirAssunto;
    private javax.swing.JButton btnGravar;
    private gui.ComboBoxArea comboBoxArea1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private gui.TablePadrao tabApostilas;
    private gui.TablePadrao tabAssuntos;
    private javax.swing.JFormattedTextField txtCargaHoraria;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}