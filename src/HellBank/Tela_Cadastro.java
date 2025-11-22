package HellBank;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Tela_Cadastro extends javax.swing.JFrame {

    private User usuarioParaEditar = null;

    // --- CONSTRUTOR 1 (NOVO) ---
    public Tela_Cadastro() {
        initCustomComponents(); // Chama configurações comuns
    }
    
    // --- CONSTRUTOR 2 (EDITAR) ---
    public Tela_Cadastro(User usuario) {
        this.usuarioParaEditar = usuario;
        initCustomComponents(); // Chama configurações comuns
        
        preencherCampos();
        this.setTitle("Editar Usuário");
        btnSalvarCadastro.setText("Salvar Alterações");
        txtCpfCadastro.setEnabled(false);
    }

    // Método auxiliar para não repetir código nos dois construtores
    private void initCustomComponents() {
        initComponents();
        
        // --- O PULO DO GATO (LAYOUT MANUAL) ---
        this.getContentPane().setLayout(null);
        // --------------------------------------
        
        // Ouvinte de Redimensionamento
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        configurarCampos();
        configurarEventoSenha(); 
        
        try {
            this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fogo.png")).getImage());
        } catch (Exception e) {}
        
        // Tamanho Inicial (Retrato)
        this.setSize(300, 500); 
        this.setLocationRelativeTo(null);
    }
    
    private void configurarCampos() {
        txtNome.putClientProperty("JTextField.placeholderText", "Digite seu nome");
        txtCpfCadastro.putClientProperty("JTextField.placeholderText", "Digite seu CPF");
        txtCelular.putClientProperty("JTextField.placeholderText", "Digite seu Número");
        txtEmailCadastro.putClientProperty("JTextField.placeholderText", "Digite seu Email");
        txtNascimento.putClientProperty("JTextField.placeholderText", "dd/mm/aaaa");
        passSenhaCadastro.putClientProperty("JTextField.placeholderText", "Crie sua senha");
        passConfirmaSenha.putClientProperty("JTextField.placeholderText", "Confirme sua senha");
        txtId.setEnabled(false);
    }

    private void configurarEventoSenha() {
        passSenhaCadastro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String senha = new String(passSenhaCadastro.getPassword());
                int tamanho = senha.length();
                
                if (tamanho == 0) {
                    lblForcaSenha.setText(""); 
                } else if (tamanho < 4) {
                    lblForcaSenha.setText("Muito curta (Min 4)");
                    lblForcaSenha.setForeground(Color.RED);
                } else if (tamanho >= 4 && tamanho <= 7) {
                    lblForcaSenha.setText("Senha Fraca");
                    lblForcaSenha.setForeground(Color.RED);
                } else if (tamanho >= 8 && tamanho <= 15) {
                    lblForcaSenha.setText("Senha Média");
                    lblForcaSenha.setForeground(Color.ORANGE);
                } else { 
                    lblForcaSenha.setText("Senha Forte");
                    lblForcaSenha.setForeground(new Color(0, 153, 0)); 
                }
            }
        });
    }
    
    private void preencherCampos() {
        if (this.usuarioParaEditar != null) {
            txtId.setText(String.valueOf(usuarioParaEditar.getId()));
            txtNome.setText(usuarioParaEditar.getName());
            txtCpfCadastro.setText(usuarioParaEditar.getCpf());
            txtEmailCadastro.setText(usuarioParaEditar.getEmail());
            txtCelular.setText(usuarioParaEditar.getPhone());
            txtNascimento.setText(usuarioParaEditar.getDob());
            
            passSenhaCadastro.setText(usuarioParaEditar.getPassword());
            passConfirmaSenha.setText(usuarioParaEditar.getPassword());
            
            txtId.setEnabled(false);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblConfirmasenha = new javax.swing.JLabel();
        txtNascimento = new javax.swing.JTextField();
        passConfirmaSenha = new javax.swing.JPasswordField();
        btnCANCELAR = new javax.swing.JButton();
        btnSalvarCadastro = new javax.swing.JButton();
        lblNASC = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        lblCPFCadastro1 = new javax.swing.JLabel();
        lblCEL = new javax.swing.JLabel();
        lblEMAIL = new javax.swing.JLabel();
        txtCpfCadastro = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtEmailCadastro = new javax.swing.JTextField();
        lblSENHA = new javax.swing.JLabel();
        passSenhaCadastro = new javax.swing.JPasswordField();
        lblForcaSenha = new javax.swing.JLabel();
        chkMostrarSenha = new javax.swing.JCheckBox();
        lblCADASTRO = new javax.swing.JLabel();
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
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNome.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("NOME:");
        jPanel3.add(lblNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 49, -1));

        lblConfirmasenha.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblConfirmasenha.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirmasenha.setText("SENHA:");
        jPanel3.add(lblConfirmasenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        txtNascimento.setBackground(new java.awt.Color(54, 57, 63));
        txtNascimento.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        txtNascimento.setForeground(new java.awt.Color(255, 255, 255));
        txtNascimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNascimentoActionPerformed(evt);
            }
        });
        txtNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNascimentoKeyReleased(evt);
            }
        });
        jPanel3.add(txtNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 150, 30));

        passConfirmaSenha.setBackground(new java.awt.Color(54, 57, 63));
        passConfirmaSenha.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        passConfirmaSenha.setForeground(new java.awt.Color(255, 255, 255));
        passConfirmaSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passConfirmaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passConfirmaSenhaKeyTyped(evt);
            }
        });
        jPanel3.add(passConfirmaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 150, 30));

        btnCANCELAR.setBackground(new java.awt.Color(128, 0, 0));
        btnCANCELAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCANCELAR.setForeground(new java.awt.Color(255, 255, 255));
        btnCANCELAR.setText("CANCELAR");
        btnCANCELAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELARActionPerformed(evt);
            }
        });
        jPanel3.add(btnCANCELAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        btnSalvarCadastro.setBackground(new java.awt.Color(128, 0, 0));
        btnSalvarCadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalvarCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarCadastro.setText("CADASTRAR");
        btnSalvarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCadastroActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalvarCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 120, -1));

        lblNASC.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblNASC.setForeground(new java.awt.Color(255, 255, 255));
        lblNASC.setText("NASC:");
        jPanel3.add(lblNASC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        lblID.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setText("ID:");
        jPanel3.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 20, 20));

        txtId.setBackground(new java.awt.Color(54, 57, 63));
        txtId.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel3.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 150, 30));

        txtNome.setBackground(new java.awt.Color(54, 57, 63));
        txtNome.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        txtNome.setForeground(new java.awt.Color(255, 255, 255));
        txtNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });
        jPanel3.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 150, 30));

        lblCPFCadastro1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblCPFCadastro1.setForeground(new java.awt.Color(255, 255, 255));
        lblCPFCadastro1.setText("CPF:");
        jPanel3.add(lblCPFCadastro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        lblCEL.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblCEL.setForeground(new java.awt.Color(255, 255, 255));
        lblCEL.setText("CEL:");
        jPanel3.add(lblCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        lblEMAIL.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblEMAIL.setForeground(new java.awt.Color(255, 255, 255));
        lblEMAIL.setText("EMAIL:");
        jPanel3.add(lblEMAIL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txtCpfCadastro.setBackground(new java.awt.Color(54, 57, 63));
        txtCpfCadastro.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        txtCpfCadastro.setForeground(new java.awt.Color(255, 255, 255));
        txtCpfCadastro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCpfCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfCadastroActionPerformed(evt);
            }
        });
        txtCpfCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpfCadastroKeyReleased(evt);
            }
        });
        jPanel3.add(txtCpfCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 150, 30));

        txtCelular.setBackground(new java.awt.Color(54, 57, 63));
        txtCelular.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        txtCelular.setForeground(new java.awt.Color(255, 255, 255));
        txtCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });
        jPanel3.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 150, 30));

        txtEmailCadastro.setBackground(new java.awt.Color(54, 57, 63));
        txtEmailCadastro.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        txtEmailCadastro.setForeground(new java.awt.Color(255, 255, 255));
        txtEmailCadastro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmailCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailCadastroActionPerformed(evt);
            }
        });
        jPanel3.add(txtEmailCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 150, 30));

        lblSENHA.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblSENHA.setForeground(new java.awt.Color(255, 255, 255));
        lblSENHA.setText("SENHA:");
        jPanel3.add(lblSENHA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        passSenhaCadastro.setBackground(new java.awt.Color(54, 57, 63));
        passSenhaCadastro.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        passSenhaCadastro.setForeground(new java.awt.Color(255, 255, 255));
        passSenhaCadastro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passSenhaCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passSenhaCadastroKeyTyped(evt);
            }
        });
        jPanel3.add(passSenhaCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 150, 30));
        jPanel3.add(lblForcaSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 150, 20));

        chkMostrarSenha.setBackground(new java.awt.Color(44, 47, 51));
        chkMostrarSenha.setToolTipText("Ocultar ou Mostrar Senha");
        chkMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMostrarSenhaActionPerformed(evt);
            }
        });
        jPanel3.add(chkMostrarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 250, 380));

        lblCADASTRO.setBackground(new java.awt.Color(44, 47, 51));
        lblCADASTRO.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCADASTRO.setForeground(new java.awt.Color(255, 255, 255));
        lblCADASTRO.setText("        CADASTRO");
        lblCADASTRO.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        getContentPane().add(lblCADASTRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -260, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                         
    private void txtNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNascimentoActionPerformed

    private void btnCANCELARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELARActionPerformed
        this.dispose();
        Tela_Login login = new Tela_Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnCANCELARActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCpfCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfCadastroActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtEmailCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailCadastroActionPerformed

    private void btnSalvarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCadastroActionPerformed
     String senha1 = new String(passSenhaCadastro.getPassword());
        String senha2 = new String(passConfirmaSenha.getPassword());

        if (txtNome.getText().trim().isEmpty() || txtCpfCadastro.getText().trim().isEmpty() ||
            txtEmailCadastro.getText().trim().isEmpty() || senha1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        try {
            String dataTexto = txtNascimento.getText().trim(); 
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate dataNascimento = LocalDate.parse(dataTexto, formato);
            LocalDate hoje = LocalDate.now();
            int idade = Period.between(dataNascimento, hoje).getYears();
            
            if (idade < 18) {
                JOptionPane.showMessageDialog(this, "Cadastro não permitido.\nÉ necessário ter pelo menos 18 anos.", "Menor de Idade", JOptionPane.WARNING_MESSAGE);
                return; 
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Data de Nascimento inválida!\nVerifique se digitou dia/mês/ano corretamente (ex: 01/01/2000).", "Erro na Data", JOptionPane.ERROR_MESSAGE);
             return;
        }

        if (!isCpfValido(txtCpfCadastro.getText())) {
            JOptionPane.showMessageDialog(this, "CPF Inválido! Verifique se os números estão corretos.", "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        if (!isEmailValido(txtEmailCadastro.getText())) {
            JOptionPane.showMessageDialog(this, "E-mail Inválido!\nFormato esperado: exemplo@email.com", "Erro no Cadastro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!senha1.equals(senha2)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem!", "Erro de Senha", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        boolean temNumero = senha1.matches(".*\\d.*");
        boolean temMaiuscula = senha1.matches(".*[A-Z].*");
        boolean temEspecial = senha1.matches(".*[!@#$%^&*(),.?\":{}|<>].*"); 
        
        if (senha1.length() < 4 || !temNumero || !temMaiuscula || !temEspecial) {
             JOptionPane.showMessageDialog(this, "Sua senha precisa ter:\n- Pelo menos 1 Número\n- Pelo menos 1 Letra Maiúscula\n- Pelo menos 1 Caracter Especial", "Senha Fraca", JOptionPane.WARNING_MESSAGE);
             return;
        }

        UserDAO dao = new UserDAO();
        
        if (this.usuarioParaEditar == null) {
            User novoUsuario = new User();
            novoUsuario.setName(txtNome.getText().trim());
            novoUsuario.setCpf(txtCpfCadastro.getText().trim()); 
            novoUsuario.setEmail(txtEmailCadastro.getText().trim());
            novoUsuario.setPhone(txtCelular.getText().trim());
            novoUsuario.setDob(txtNascimento.getText().trim());
            novoUsuario.setPassword(senha1); 
            
            dao.createUser(novoUsuario);
            
            this.dispose();
            Tela_Login telaLogin = new Tela_Login();
            telaLogin.setVisible(true);
            telaLogin.setLocationRelativeTo(null);
            
        } else {
            this.usuarioParaEditar.setName(txtNome.getText().trim());
            this.usuarioParaEditar.setEmail(txtEmailCadastro.getText().trim());
            this.usuarioParaEditar.setPhone(txtCelular.getText().trim());
            this.usuarioParaEditar.setDob(txtNascimento.getText().trim());
            this.usuarioParaEditar.setPassword(senha1);
            
            dao.updateUser(this.usuarioParaEditar);
            this.dispose(); 
        }
    }//GEN-LAST:event_btnSalvarCadastroActionPerformed

    private void chkMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMostrarSenhaActionPerformed
       if (chkMostrarSenha.isSelected()) {
            passSenhaCadastro.setEchoChar((char) 0);
            passConfirmaSenha.setEchoChar((char) 0);
        } else {
            passSenhaCadastro.setEchoChar('*');
            passConfirmaSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_chkMostrarSenhaActionPerformed

    private void txtCpfCadastroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfCadastroKeyReleased
        String textoAtual = txtCpfCadastro.getText();
        String apenasNumeros = textoAtual.replaceAll("[^0-9]", "");
        if (apenasNumeros.length() == 11) {
            String cpfFormatado = apenasNumeros.substring(0, 3) + "." +
                                  apenasNumeros.substring(3, 6) + "." +
                                  apenasNumeros.substring(6, 9) + "-" +
                                  apenasNumeros.substring(9, 11);
            txtCpfCadastro.setText(cpfFormatado);
        }
        if (textoAtual.length() > 14) {
            txtCpfCadastro.setText(textoAtual.substring(0, 14));
        }
    }//GEN-LAST:event_txtCpfCadastroKeyReleased

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        String textoAtual = txtCelular.getText();
        String apenasNumeros = textoAtual.replaceAll("[^0-9]", "");
        if (apenasNumeros.length() == 11) {
            String celularFormatado = "(" + apenasNumeros.substring(0, 2) + ") " +
                                      apenasNumeros.substring(2, 7) + "-" +
                                      apenasNumeros.substring(7, 11);
            txtCelular.setText(celularFormatado);
        }
        if (textoAtual.length() > 15) {
            txtCelular.setText(textoAtual.substring(0, 15));
        }
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtNascimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNascimentoKeyReleased
        String textoAtual = txtNascimento.getText();
        String apenasNumeros = textoAtual.replaceAll("[^0-9]", "");
        if (apenasNumeros.length() == 8) {
            String dataFormatada = apenasNumeros.substring(0, 2) + "/" + 
                                   apenasNumeros.substring(2, 4) + "/" + 
                                   apenasNumeros.substring(4, 8);         
            txtNascimento.setText(dataFormatada);
        }
        if (textoAtual.length() > 10) {
            txtNascimento.setText(textoAtual.substring(0, 10));
        }
    }//GEN-LAST:event_txtNascimentoKeyReleased

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
            System.out.println("Erro imagem: " + e.getMessage());
        }

        // 2. CENTRALIZA O PAINEL
        if (jPanel3 != null) {
            int larguraPainel = jPanel3.getWidth();
            int alturaPainel = jPanel3.getHeight();
            
            int xPanel = (w - larguraPainel) / 2;
            int yPanel = ((h - alturaPainel) / 2) + 23; 
            
            jPanel3.setLocation(xPanel, yPanel);
            
            // 3. CENTRALIZA O TÍTULO "CADASTRO"
            if (lblCADASTRO != null) {
                int larguraTexto = lblCADASTRO.getWidth();
                int xTexto = xPanel + (larguraPainel - larguraTexto) / 2;
                // Ajuste de altura (-51px do painel)
                int yTexto = yPanel - 51; 
                
                lblCADASTRO.setLocation(xTexto, yTexto);
            }
        }
        
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_formComponentResized

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
        if (txtNome.getText().length() >= 100) {
            evt.consume(); 
        }
    }//GEN-LAST:event_txtNomeKeyTyped

    private void passSenhaCadastroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passSenhaCadastroKeyTyped
        if (passSenhaCadastro.getPassword().length >= 50) {
            evt.consume(); 
        }
    }//GEN-LAST:event_passSenhaCadastroKeyTyped

    private void passConfirmaSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passConfirmaSenhaKeyTyped
        if (passConfirmaSenha.getPassword().length >= 50) {
            evt.consume(); 
        }
    }//GEN-LAST:event_passConfirmaSenhaKeyTyped

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        int posicaoCursor = txtNome.getCaretPosition();
        txtNome.setText(txtNome.getText().toUpperCase());
        try { txtNome.setCaretPosition(posicaoCursor); } catch (Exception e) {}
    }                                   

    public static void main(String args[]) {
        try {
             javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {}

        java.awt.EventQueue.invokeLater(() -> new Tela_Cadastro().setVisible(true));
    }//GEN-LAST:event_txtNomeKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCANCELAR;
    private javax.swing.JButton btnSalvarCadastro;
    private javax.swing.JCheckBox chkMostrarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCADASTRO;
    private javax.swing.JLabel lblCEL;
    private javax.swing.JLabel lblCPFCadastro1;
    private javax.swing.JLabel lblConfirmasenha;
    private javax.swing.JLabel lblEMAIL;
    private javax.swing.JLabel lblForcaSenha;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNASC;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSENHA;
    private javax.swing.JPasswordField passConfirmaSenha;
    private javax.swing.JPasswordField passSenhaCadastro;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCpfCadastro;
    private javax.swing.JTextField txtEmailCadastro;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables

         private boolean isCpfValido(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        try {
            int soma = 0, peso = 10;
            for (int i = 0; i < 9; i++) { soma += (cpf.charAt(i) - '0') * peso--; }
            int r = 11 - (soma % 11);
            char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            soma = 0; peso = 11;
            for (int i = 0; i < 10; i++) { soma += (cpf.charAt(i) - '0') * peso--; }
            r = 11 - (soma % 11);
            char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (Exception e) { return false; }
    }
    
    private boolean isEmailValido(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
        }   
      }
