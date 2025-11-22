package HellBank;

import javax.swing.table.DefaultTableModel; 
import java.util.List;                        
import javax.swing.JOptionPane;

public class Tela_Usuarios extends javax.swing.JFrame {
    
    // --- Guardamos quem está usando a tela ---
    private User adminLogado; 
    // ----------------------------------------------
    
    // CONSTANTE DO CPF DO CHEFE (Para não depender de ID)
    private final String CPF_MESTRE = "38959340898";
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tela_Usuarios.class.getName());

    /**
     * Construtor Padrão (Apenas para testes visuais)
     */
    public Tela_Usuarios() {
        initComponents();
        configurarTela();
    }

    /**
     * CONSTRUTOR PRINCIPAL (Recebe o Admin Logado)
     */
    public Tela_Usuarios(User admin) {
        initComponents();
        this.adminLogado = admin; // Guarda quem logou
        configurarTela();
    }
    
    // Método auxiliar para não repetir código nos construtores
    private void configurarTela() {
        this.getContentPane().setLayout(null);
        this.setSize(600, 450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        try {
            this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fogo.png")).getImage());
        } catch (Exception e) {}

        tblUsuarios.setAutoCreateRowSorter(true);
        readJTable();
        ajustarFundoFixo();
    }
    
    private void ajustarFundoFixo() {                                      
        int w = 800; 
        int h = 500;
        try {
            javax.swing.ImageIcon iconOriginal = new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"));
            java.awt.Image img = iconOriginal.getImage();
            java.awt.Image imgRedimensionada = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            jLabel1.setIcon(new javax.swing.ImageIcon(imgRedimensionada));
            jLabel1.setBounds(0, 0, w, h);
        } catch (Exception e) {}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnExcluirTodos = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnHierarquia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciador de Usuários");
        setBackground(new java.awt.Color(0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUsuarios.setBackground(new java.awt.Color(54, 57, 63));
        tblUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "EMAIL", "CELULAR", "NASC", "DATA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setGridColor(new java.awt.Color(51, 204, 255));
        jScrollPane1.setViewportView(tblUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 550, 330));

        btnNovo.setBackground(new java.awt.Color(128, 0, 0));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setText("NOVO");
        btnNovo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, -1, -1));

        btnEditar.setBackground(new java.awt.Color(128, 0, 0));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 15, -1, -1));

        btnExcluir.setBackground(new java.awt.Color(128, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 15, -1, -1));

        btnExcluirTodos.setBackground(new java.awt.Color(128, 0, 0));
        btnExcluirTodos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnExcluirTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirTodos.setText("EXCLUIR TUDO");
        btnExcluirTodos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnExcluirTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirTodosActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluirTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 15, -1, -1));

        btnAtualizar.setBackground(new java.awt.Color(128, 0, 0));
        btnAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizar.setText("ATUALIZAR");
        btnAtualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 15, -1, -1));

        btnHierarquia.setBackground(new java.awt.Color(128, 0, 0));
        btnHierarquia.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHierarquia.setForeground(new java.awt.Color(255, 255, 255));
        btnHierarquia.setText("PERMISSÃO");
        btnHierarquia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnHierarquia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHierarquiaActionPerformed(evt);
            }
        });
        getContentPane().add(btnHierarquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 15, 100, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -260, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        Tela_Cadastro telaDeCadastro = new Tela_Cadastro();
        telaDeCadastro.setVisible(true);
        telaDeCadastro.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tblUsuarios.getSelectedRow() != -1) {
            
            int id = (int) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0);
            UserDAO dao = new UserDAO();
            User usuarioAlvo = dao.getUserById(id);
            
            if (usuarioAlvo != null) {
                String cpfLimpo = usuarioAlvo.getCpf().replaceAll("[^0-9]", "");
                
                // VERIFICA SE O CPF É O DO MESTRE
                if (cpfLimpo.equals(CPF_MESTRE)) {
                    JOptionPane.showMessageDialog(this, 
                        "AÇÃO BLOQUEADA:\n\n" + 
                        "O usuário " + usuarioAlvo.getName() + " é o ADMINISTRADOR MESTRE.\n" +
                        "Por segurança, ele não pode ser excluído do sistema.", 
                        "Proteção de Sistema", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
            }

            int option = JOptionPane.showConfirmDialog(
                null, 
                "Tem certeza que deseja excluir o usuário selecionado?",
                "Confirmação de Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(option == JOptionPane.YES_OPTION) {
                dao.deleteUser(id);
                readJTable();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, 
                "Selecione um usuário na tabela para excluir.",
                "Nenhum usuário selecionado",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirTodosActionPerformed
       int option = JOptionPane.showConfirmDialog(
        null, 
        "TEM CERTEZA?\nIsso apagará TODOS os usuários do sistema permanentemente.",
        "!! ATENÇÃO !!",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    
    if(option == JOptionPane.YES_OPTION) {
        UserDAO dao = new UserDAO();
        dao.deleteAllUsers(); // Esse método no DAO já está protegido para não apagar o Mestre
        readJTable(); 
        }
    }//GEN-LAST:event_btnExcluirTodosActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        readJTable();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblUsuarios.getSelectedRow() != -1) {
            
            int id = (int) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0);
            
            UserDAO dao = new UserDAO();
            User usuarioAlvo = dao.getUserById(id);
            
            if (usuarioAlvo != null) {
                
                // 1. Verifica se o usuário alvo é o Mestre (Pelo CPF)
                String cpfAlvo = usuarioAlvo.getCpf().replaceAll("[^0-9]", "");
                
                if (cpfAlvo.equals(CPF_MESTRE)) {
                    
                    // 2. Se for o Mestre, verifica quem está logado
                    String meuCpf = "";
                    if (adminLogado != null) {
                        meuCpf = adminLogado.getCpf().replaceAll("[^0-9]", "");
                    }
                    
                    // 3. Se eu não sou o Mestre, não posso editar
                    if (!meuCpf.equals(CPF_MESTRE)) {
                         JOptionPane.showMessageDialog(this, 
                            "AÇÃO BLOQUEADA:\n\n" + 
                            "Os dados do ADMINISTRADOR MESTRE são protegidos.\n" +
                            "Apenas o próprio Mestre pode editar seus dados.", 
                            "Acesso Negado", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                // Se passou, abre a tela
                Tela_Cadastro telaEditar = new Tela_Cadastro(usuarioAlvo);
                telaEditar.setVisible(true);
                telaEditar.setLocationRelativeTo(null); 
                
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Não foi possível encontrar o usuário selecionado.",
                    "Erro de Edição", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "Selecione um usuário na tabela para editar.",
                "Nenhum usuário selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        
    }//GEN-LAST:event_formComponentResized

    private void btnHierarquiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHierarquiaActionPerformed
         int linhaSelecionada = tblUsuarios.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, selecione um usuário na tabela para alterar a permissão.", 
                "Nenhum Usuário Selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idUsuario = (int) tblUsuarios.getValueAt(linhaSelecionada, 0);
        String nomeUsuario = (String) tblUsuarios.getValueAt(linhaSelecionada, 1);

        UserDAO dao = new UserDAO();
        User usuarioSelecionado = dao.getUserById(idUsuario);
        
        // PROTEÇÃO DO CPF MESTRE
        if (usuarioSelecionado != null) {
            String cpfLimpo = usuarioSelecionado.getCpf().replaceAll("[^0-9]", "");
            if (cpfLimpo.equals(CPF_MESTRE)) {
                JOptionPane.showMessageDialog(this, 
                    "AÇÃO BLOQUEADA:\n\n" + 
                    "O usuário " + nomeUsuario + " é o ADMINISTRADOR MESTRE.\n" +
                    "Por segurança, a permissão dele não pode ser alterada.", 
                    "Proteção de Sistema", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        }

        Object[] opcoes = { "Usuário Comum", "Administrador" };
        int escolha = JOptionPane.showOptionDialog(this,
                "Definir nível de acesso para: " + nomeUsuario + "\n\n" +
                "ADMINISTRADOR: Acesso total ao sistema.\n" +
                "USUÁRIO COMUM: Acesso apenas à conta bancária.",
                "Gerenciar Permissão",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, opcoes[0]);

        if (escolha == 0) {
            dao.atualizarPermissao(idUsuario, "USUARIO");
        } else if (escolha == 1) {
            javax.swing.JPasswordField pf = new javax.swing.JPasswordField();
            int acao = JOptionPane.showConfirmDialog(this, pf, 
                    "SEGURANÇA: Digite a senha de Admin para confirmar:", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (acao == JOptionPane.OK_OPTION) {
                String senhaDigitada = new String(pf.getPassword());
                if (senhaDigitada.equals("ADMIN&041210")) { 
                    dao.atualizarPermissao(idUsuario, "ADMIN");
                } else {
                    JOptionPane.showMessageDialog(this, "Senha incorreta! Operação cancelada.", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        readJTable();
    }//GEN-LAST:event_btnHierarquiaActionPerformed

    public static void main(String args[]) {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Tela_Usuarios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluirTodos;
    private javax.swing.JButton btnHierarquia;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables

    private void readJTable() {
       DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.setRowCount(0); 
        UserDAO dao = new UserDAO();
        List<User> listaDeUsuarios = dao.readUsers();

        for (User u : listaDeUsuarios) {
            modelo.addRow(new Object[]{
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPhone(),
                u.getDob(),
                u.getDataCadastro() 
            });
        }
    }
}