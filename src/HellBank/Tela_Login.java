package HellBank;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Login extends javax.swing.JFrame {
    
    private int tentativasErradas = 0;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tela_Login.class.getName());

    public Tela_Login() {
        // 1. Cria os componentes visuais
        initComponents();
        
        // Desativa o gerenciador do NetBeans para que nosso código de redimensionamento
        // seja a única autoridade sobre a posição dos elementos.
        this.getContentPane().setLayout(null);
        // -------------------------------------------
        
        // 2. ADICIONA O "OUVIDO" PARA O REDIMENSIONAMENTO (CRUCIAL!)
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        // 3. Configurações Visuais
        txtCpfLogin.putClientProperty("JTextField.placeholderText", "Digite seu CPF");
        passSenhaLogin.putClientProperty("JTextField.placeholderText", "Digite sua senha");
        
        this.getRootPane().setDefaultButton(btnEntrar);
        
        try {
            this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fogo.png")).getImage());
        } catch (Exception e) {}
        
        // 4. Tamanho Inicial
        this.setSize(320, 400); 
        this.setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lblCPFLogin = new javax.swing.JLabel();
        lblSENHALOGIN = new javax.swing.JLabel();
        txtCpfLogin = new javax.swing.JTextField();
        passSenhaLogin = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        chkMostrarSenha = new javax.swing.JCheckBox();
        lblLogin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HELLBANK");
        setBackground(new java.awt.Color(0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(44, 47, 51));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCPFLogin.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        lblCPFLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblCPFLogin.setText("CPF:");
        jPanel3.add(lblCPFLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 37, 49, -1));

        lblSENHALOGIN.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        lblSENHALOGIN.setForeground(new java.awt.Color(255, 255, 255));
        lblSENHALOGIN.setText("SENHA:");
        jPanel3.add(lblSENHALOGIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 101, -1, -1));

        txtCpfLogin.setBackground(new java.awt.Color(54, 57, 63));
        txtCpfLogin.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtCpfLogin.setForeground(new java.awt.Color(255, 255, 255));
        txtCpfLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfLoginActionPerformed(evt);
            }
        });
        txtCpfLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpfLoginKeyReleased(evt);
            }
        });
        jPanel3.add(txtCpfLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 65, 160, 24));

        passSenhaLogin.setBackground(new java.awt.Color(54, 57, 63));
        passSenhaLogin.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        passSenhaLogin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(passSenhaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 129, 160, -1));

        btnEntrar.setBackground(new java.awt.Color(128, 0, 0));
        btnEntrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("ENTRAR");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 183, -1, -1));

        btnCadastrar.setBackground(new java.awt.Color(128, 0, 0));
        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 183, -1, -1));

        chkMostrarSenha.setBackground(new java.awt.Color(44, 47, 51));
        chkMostrarSenha.setToolTipText("Ocultar ou Mostrar Senha");
        chkMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMostrarSenhaActionPerformed(evt);
            }
        });
        jPanel3.add(chkMostrarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 30, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 230, 260));

        lblLogin.setBackground(new java.awt.Color(44, 47, 51));
        lblLogin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setText("           LOGIN");
        lblLogin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        getContentPane().add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 230, 47));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -260, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCpfLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfLoginActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfLoginActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
            Tela_Login.this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        
        try {
            String cpfDigitado = txtCpfLogin.getText().trim();
            String cpfLimpo = cpfDigitado.replaceAll("[^0-9]", "");
            String cpfFormatado = cpfDigitado; 

            if (cpfLimpo.length() == 11) {
                cpfFormatado = cpfLimpo.substring(0, 3) + "." +
                               cpfLimpo.substring(3, 6) + "." +
                               cpfLimpo.substring(6, 9) + "-" +
                               cpfLimpo.substring(9, 11);
            }

            String senha = new String(passSenhaLogin.getPassword()).trim();
            UserDAO dao = new UserDAO();
            User usuario = dao.getUserByCpf(cpfFormatado);

            if (usuario == null) {
                JOptionPane.showMessageDialog(this,"CPF não cadastrado no sistema.","Erro de Login",JOptionPane.ERROR_MESSAGE);
            } else {
                if (usuario.getPassword().equals(senha)) {
                    tentativasErradas = 0; 

                    String permissao = usuario.getTipoPermissao();
                    if (permissao == null) permissao = "USUARIO";
                    
                    if (permissao.equalsIgnoreCase("ADMIN") || permissao.equalsIgnoreCase("ADMINISTRADOR")) {
                        Object[] botoes = { "Gerenciar Usuários", "Acessar Minha Conta" };
                        int escolha = JOptionPane.showOptionDialog(this,
                                "Bem Vindo(a), " + usuario.getName() + "!\n\nQue área deseja acessar?",
                                "Área Administrativa",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, botoes, botoes[0]);

                        if (escolha == 0) {
                            Tela_Usuarios telaAdmin = new Tela_Usuarios(usuario);
                            telaAdmin.setVisible(true);
                            telaAdmin.setLocationRelativeTo(null); // Centraliza, SEM MAXIMIZAR
                            this.dispose();
                        } else if (escolha == 1) {
                            Tela_Extrato telaExtrato = new Tela_Extrato(usuario); 
                            telaExtrato.setVisible(true);
                            telaExtrato.setLocationRelativeTo(null); // Centraliza, SEM MAXIMIZAR
                            this.dispose();
                        }
                    } else {
                        Tela_Extrato telaExtrato = new Tela_Extrato(usuario); 
                        telaExtrato.setVisible(true);
                        telaExtrato.setLocationRelativeTo(null); // Centraliza, SEM MAXIMIZAR
                        this.dispose(); 
                    }

                } else {
                    tentativasErradas++;
                    tremerJanela();
                    
                    if (tentativasErradas >= 3) {
                        btnEntrar.setEnabled(false);
                        btnEntrar.setText("BLOQUEADO (30s)");
                        JOptionPane.showMessageDialog(this, 
                            "Muitas tentativas incorretas!\nO sistema foi bloqueado por 30 segundos.", 
                            "Acesso Bloqueado", JOptionPane.WARNING_MESSAGE);
                        
                        Timer timer = new Timer(30000, e -> {
                            btnEntrar.setEnabled(true);
                            btnEntrar.setText("ENTRAR");
                            tentativasErradas = 0;
                            ((Timer)e.getSource()).stop();
                        });
                        timer.setRepeats(false);
                        timer.start();
                    } else {
                        int restantes = 3 - tentativasErradas;
                        JOptionPane.showMessageDialog(this, "Senha inválida.\nRestam " + restantes + " tentativas.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } finally {
            Tela_Login.this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        Tela_Cadastro telaDeCadastro = new Tela_Cadastro();
        telaDeCadastro.setVisible(true);
        telaDeCadastro.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void chkMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMostrarSenhaActionPerformed
           if (chkMostrarSenha.isSelected()) {
            passSenhaLogin.setEchoChar((char) 0); 
        } else {
            passSenhaLogin.setEchoChar('*'); 
        }
    }//GEN-LAST:event_chkMostrarSenhaActionPerformed

    private void txtCpfLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfLoginKeyReleased
            String textoAtual = txtCpfLogin.getText();
        String apenasNumeros = textoAtual.replaceAll("[^0-9]", "");

        if (apenasNumeros.length() == 11) {
            String cpfFormatado = apenasNumeros.substring(0, 3) + "." +
            apenasNumeros.substring(3, 6) + "." +
            apenasNumeros.substring(6, 9) + "-" +
            apenasNumeros.substring(9, 11);
            txtCpfLogin.setText(cpfFormatado);
        }
        if (textoAtual.length() > 14) {
            txtCpfLogin.setText(textoAtual.substring(0, 14));
        }
    }//GEN-LAST:event_txtCpfLoginKeyReleased
    // =========================================================================
    // LÓGICA DE REDIMENSIONAMENTO BLINDADA
    // =========================================================================  
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int w = this.getContentPane().getWidth();
        int h = this.getContentPane().getHeight();
        
        // 1. AJUSTA O FUNDO
        try {
            javax.swing.ImageIcon iconOriginal = new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"));
            java.awt.Image img = iconOriginal.getImage();
            java.awt.Image imgRedimensionada = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            
            jLabel1.setIcon(new javax.swing.ImageIcon(imgRedimensionada));
            jLabel1.setBounds(0, 0, w, h); 
            
        } catch (Exception e) {
             // Ignora erro de imagem
        }

        // 2. CENTRALIZA O PAINEL COM OFFSET (DESLOCAMENTO)
        if (jPanel3 != null) {
            int larguraPainel = jPanel3.getWidth();
            int alturaPainel = jPanel3.getHeight();
            
            int xPanel = (w - larguraPainel) / 2;
            
            // --- O PULO DO GATO AQUI ---
            // Calcula o centro, e adiciona +30 pixels para empurrar para baixo.
            // Isso equilibra o espaço por causa do título "LOGIN" em cima.
            int yPanel = ((h - alturaPainel) / 2) + 23; 
            // ---------------------------
            
            jPanel3.setLocation(xPanel, yPanel);
            
            // 3. CENTRALIZA O TÍTULO "LOGIN" (Relativo ao Painel)
            if (lblLogin != null) {
                int larguraTexto = lblLogin.getWidth();
                int xTexto = xPanel + (larguraPainel - larguraTexto) / 2;
                // Posiciona o título 49px acima do painel (que já foi empurrado para baixo)
                int yTexto = yPanel - 48; 
                
                lblLogin.setLocation(xTexto, yTexto);
            }
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            txtCpfLogin.requestFocus();
        });
        
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_formComponentResized
        
        public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Falha ao iniciar o tema FlatLaf. Usando padrão.");
        }
        
        try {
            java.sql.Connection con = ConnectionFactory.getConnection();
            con.close(); 
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "ERRO CRÍTICO: O sistema não conseguiu conectar ao Banco de Dados.\n" +
                "Verifique se o XAMPP (MySQL) está ligado.", 
                "Servidor Offline", JOptionPane.ERROR_MESSAGE);
            System.exit(0); 
        }

        java.awt.EventQueue.invokeLater(() -> new Tela_Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JCheckBox chkMostrarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCPFLogin;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblSENHALOGIN;
    private javax.swing.JPasswordField passSenhaLogin;
    private javax.swing.JTextField txtCpfLogin;
    // End of variables declaration//GEN-END:variables
        
        private void tremerJanela() {
        final int originalX = this.getLocation().x;
        final int originalY = this.getLocation().y;
        new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    this.setLocation(originalX + 10, originalY);
                    Thread.sleep(40);
                    this.setLocation(originalX - 10, originalY);
                    Thread.sleep(40);
                }
                this.setLocation(originalX, originalY);
            } catch (InterruptedException e) {}
        }).start();
    }
}