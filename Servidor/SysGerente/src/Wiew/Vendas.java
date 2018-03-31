
package Wiew;

import Controller.ConectaBD;
import Model.ModeloVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;


public class Vendas extends javax.swing.JFrame {
    Connection conecta;
    ConectaBD conn = new ConectaBD();
    ModeloVenda mod = new ModeloVenda();
      PreparedStatement pst;
      ResultSet rs;
      int codVenda;
  
    

   
    public Vendas() throws ClassNotFoundException {
        
        initComponents();
       
                
        try {
            PreparedStatement pst = conecta.prepareStatement("insert into vendas(valor) values (?)");
            String sql_insert = "insert into vendas(valor) values (?)";
          
            pst = conecta.prepareStatement(sql_insert);
            pst.setFloat(1, 0);
            pst.execute();
            
            
         
           
            
            
            
         
           
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane,"ERRO" + ex);
        }
        
        try{
            MaskFormatter form = new MaskFormatter("##/##/####");
            txtdata.setFormatterFactory(new DefaultFormatterFactory(form));
        
        }catch(ParseException error){
            
            JOptionPane.showMessageDialog(null, error);
        
        }
    }
    
    public void listarClientes(){
        String sql = "Select id, nome from clientes order by id Asc";
        
        try{
        
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbcliente.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        
        }     
     }
    
    public void pesquisarClientes(){
       
         String sql = "Select id, nome, cpf_cnpj from clientes where nome like ?";
         
         try{
             pst = conecta.prepareStatement(sql);
             pst.setString(1, txtcliente.getText()+"%");
             rs = pst.executeQuery();
             
             tbcliente.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(SQLException error){
             JOptionPane.showMessageDialog(null, error);
         }
    }
    
    public void listarProdutos(){
        String sql = "Select id, descricao from produtos order by id Asc";
        
        try{
        
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbproduto.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        
        
        }   
    }
    
    public void pesquisarProdutos(){
       
         String sql = "Select * from produtos where descricao like ?";
         
         try{
             pst = conecta.prepareStatement(sql);
             pst.setString(1, txtproduto.getText()+"%");
             rs = pst.executeQuery();
             
             tbproduto.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(SQLException error){
             JOptionPane.showMessageDialog(null, error);
         
         }
     
     }
    
    public void mostrarItens(){
     
         int seleciona = tbproduto.getSelectedRow();
         txtvalor_item.setText(tbproduto.getModel().getValueAt(seleciona, 2 ).toString());
         txtquantidade.setText("1");

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtproduto = new javax.swing.JTextField();
        btnbusca_produto = new javax.swing.JButton();
        btnbusca_cliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtdata = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtquantidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtvalor_item = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbcliente = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnadd_item = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbproduto = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbitens_venda = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtvalorTotal = new javax.swing.JTextField();
        btnfinalizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setTitle("Controle de vendas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Controle de vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nome do cliente: ");

        txtcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclienteKeyReleased(evt);
            }
        });

        jLabel4.setText("Produto:");

        txtproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprodutoActionPerformed(evt);
            }
        });
        txtproduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprodutoKeyReleased(evt);
            }
        });

        btnbusca_produto.setText("BUSCA");
        btnbusca_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusca_produtoActionPerformed(evt);
            }
        });

        btnbusca_cliente.setText("BUSCA");
        btnbusca_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusca_clienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Data:");

        try {
            txtdata.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtdata.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtdataFocusGained(evt);
            }
        });

        jLabel5.setText("Quantidade:");

        txtquantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtquantidadeFocusGained(evt);
            }
        });

        jLabel6.setText("Valor por item:");

        tbcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbcliente);

        jLabel8.setText("Pesquisar Cliente");

        btnadd_item.setText("ADD");
        btnadd_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd_itemActionPerformed(evt);
            }
        });

        tbproduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbprodutoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbproduto);

        jLabel10.setText("Pesquisar Produto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbusca_produto, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtvalor_item, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnadd_item, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdata, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnbusca_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnbusca_cliente)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbusca_produto))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtvalor_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnadd_item)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Itens da venda");

        tbitens_venda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbitens_venda);

        jLabel7.setText("Valor total:");

        txtvalorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtvalorTotalFocusGained(evt);
            }
        });
        txtvalorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorTotalActionPerformed(evt);
            }
        });

        btnfinalizar.setText("Finalizar venda");

        btncancelar.setText("Cancelar venda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtvalorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnfinalizar)
                                .addGap(35, 35, 35)
                                .addComponent(btncancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(283, 283, 283)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtvalorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnfinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprodutoActionPerformed
      pesquisarClientes();
    }//GEN-LAST:event_txtprodutoActionPerformed

    private void btnbusca_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusca_clienteActionPerformed
       
        pesquisarClientes();
    }//GEN-LAST:event_btnbusca_clienteActionPerformed

    private void txtclienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclienteKeyReleased
       
        
        pesquisarClientes();
    }//GEN-LAST:event_txtclienteKeyReleased

    private void txtprodutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprodutoKeyReleased
       pesquisarProdutos();
    }//GEN-LAST:event_txtprodutoKeyReleased

    private void btnbusca_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusca_produtoActionPerformed
       
        
        pesquisarProdutos();
    }//GEN-LAST:event_btnbusca_produtoActionPerformed

    private void tbclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclienteMouseClicked
       
       
           String nome_cliente = "" + tbcliente.getValueAt(tbcliente.getSelectedRow(), 1);
           txtcliente.setText(nome_cliente);
       
    }//GEN-LAST:event_tbclienteMouseClicked

    private void tbprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodutoMouseClicked
        mostrarItens();
       
           String nome_produto = "" + tbproduto.getValueAt(tbproduto.getSelectedRow(), 1);
           txtproduto.setText(nome_produto);
       
       
    }//GEN-LAST:event_tbprodutoMouseClicked

    private void txtquantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtquantidadeFocusGained
        float valorTotal = 0;
        valorTotal = Float.parseFloat(txtvalor_item.getText()) * Integer.parseInt(txtquantidade.getText());
        txtvalorTotal.setText(String.valueOf(valorTotal));
    }//GEN-LAST:event_txtquantidadeFocusGained

    private void txtdataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdataFocusGained
       float valorTotal = 0;
        valorTotal = Float.parseFloat(txtvalor_item.getText()) * Integer.parseInt(txtquantidade.getText());
        txtvalorTotal.setText(String.valueOf(valorTotal));
    }//GEN-LAST:event_txtdataFocusGained

    private void btnadd_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd_itemActionPerformed
       mod.setNome_produto(txtproduto.getText());
       mod.setQtd_item(Integer.parseInt(txtquantidade.getText()));
    }//GEN-LAST:event_btnadd_itemActionPerformed

    private void txtvalorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorTotalActionPerformed

    private void txtvalorTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalorTotalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorTotalFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Vendas().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd_item;
    private javax.swing.JButton btnbusca_cliente;
    private javax.swing.JButton btnbusca_produto;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnfinalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tbcliente;
    private javax.swing.JTable tbitens_venda;
    private javax.swing.JTable tbproduto;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JFormattedTextField txtdata;
    private javax.swing.JTextField txtproduto;
    private javax.swing.JTextField txtquantidade;
    private javax.swing.JTextField txtvalorTotal;
    private javax.swing.JTextField txtvalor_item;
    // End of variables declaration//GEN-END:variables
}
