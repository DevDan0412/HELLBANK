package HellBank;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import javax.swing.Timer;
import java.text.NumberFormat; 
import java.util.Locale;        

public class Tela_Extrato extends javax.swing.JFrame {
    
    private User usuarioLogado;
    private boolean saldoVisivel = true; 
    
    private final NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tela_Extrato.class.getName());

    /**
     * Construtor Padrão
     */
    public Tela_Extrato() {
        initComponents();
        configurarRenderizadorDeCores(); 
        
        // Configuração Fixa
        this.setSize(680, 480);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * CONSTRUTOR PRINCIPAL
     */
    public Tela_Extrato(User usuario) {
        initComponents();
        
        // --- 1. FORÇA O LAYOUT MANUAL (Importante!) ---
        this.getContentPane().setLayout(null);
        // ---------------------------------------------

        // --- 2. TRAVA O TAMANHO DA TELA ---
        this.setSize(680, 480); 
        this.setResizable(false); // Não deixa maximizar nem esticar
        // ----------------------------------
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                btnSair.doClick();
            }
        });
        
        // Adiciona o listener para carregar a imagem e posições ao abrir
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        
        try {
            this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fogo.png")).getImage());
        } catch (Exception e) {}
        
        this.usuarioLogado = usuario;
        
        configurarRenderizadorDeCores(); 
        atualizarCabecalho();
        carregarExtrato(null); 
        
        iniciarRelogio();
        iniciarContagemSessao();
        
        this.setLocationRelativeTo(null); // Centraliza na tela
        
        this.getRootPane().registerKeyboardAction(e -> {
            btnSair.doClick(); 
        }, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    private void iniciarRelogio() {
        Thread relogio = new Thread(() -> {
            while (true) { 
                try {
                    LocalDateTime agora = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    if(lblRelogio != null) lblRelogio.setText(agora.format(formato));
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        relogio.setDaemon(true);
        relogio.start();
    }
    
    private void iniciarContagemSessao() {
        int tempoLimite = 60000; 
        java.awt.event.ActionListener acaoSair = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, 
                        "Sessão expirada por inatividade.\nPor segurança, faça login novamente.", 
                        "Timeout de Segurança", JOptionPane.WARNING_MESSAGE);
                Tela_Login login = new Tela_Login();
                login.setVisible(true);
                login.setLocationRelativeTo(null);
                dispose(); 
            }
        };
        Timer timerSessao = new Timer(tempoLimite, acaoSair);
        timerSessao.setRepeats(false); 
        timerSessao.start(); 
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (timerSessao.isRunning()) timerSessao.restart();
            }
        }, AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }
    
    private void configurarRenderizadorDeCores() {
        CorDaLinha pintor = new CorDaLinha();
        for (int i = 0; i < tblExtrato.getColumnCount(); i++) {
            tblExtrato.getColumnModel().getColumn(i).setCellRenderer(pintor);
        }
    }
    
    private void atualizarCabecalho() {
        if (usuarioLogado != null) {
            int hora = java.time.LocalTime.now().getHour();
            String saudacao;
            if (hora >= 5 && hora < 12) saudacao = "Bom dia";
            else if (hora >= 12 && hora < 18) saudacao = "Boa tarde";
            else saudacao = "Boa noite";

            lblUsuarioInfo.setText(saudacao + ", " + usuarioLogado.getName() + " (ID: " + usuarioLogado.getId() + ")");
            
            UserDAO dao = new UserDAO();
            User usuarioAtualizado = dao.getUserById(usuarioLogado.getId());
            this.usuarioLogado = usuarioAtualizado;
            
            if (saldoVisivel) {
                lblSaldo.setText("SALDO: " + dinheiroBR.format(usuarioLogado.getBalance()));
            } else {
                lblSaldo.setText("SALDO: R$ •••••••");
            }
        }
    }
    
    private void carregarExtrato(String filtro) {
        DefaultTableModel modelo = (DefaultTableModel) tblExtrato.getModel();
        modelo.setRowCount(0); 
        TransactionDAO dao = new TransactionDAO();
        List<Transaction> lista = dao.getFilteredTransactions(usuarioLogado.getId(), filtro);
        for (Transaction t : lista) {
            modelo.addRow(new Object[]{
                t.getId(), t.getType(), t.getMethod(),    
                dinheiroBR.format(t.getAmount()), t.getDescription(), t.getDateTime()
            });
        }
    }
    
    private void gerarComprovante(Transaction t) {
        int opcao = JOptionPane.showConfirmDialog(this, 
                "Transação realizada!\nDeseja salvar o comprovante?", 
                "Gerar Comprovante", JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoArquivo = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            DateTimeFormatter formatoTexto = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            
            String nomeArquivo = "Comprovante_" + agora.format(formatoArquivo) + ".txt";
            
            try (FileWriter fileWriter = new FileWriter(nomeArquivo);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                
                printWriter.println("========================================");
                printWriter.println("              HELLBANK                  ");
                printWriter.println("       Comprovante de Transação         ");
                printWriter.println("========================================");
                printWriter.println("DATA/HORA: " + agora.format(formatoTexto));
                printWriter.println("----------------------------------------");
                printWriter.println("TIPO:      " + t.getType());
                printWriter.println("MÉTODO:    " + t.getMethod());
                printWriter.println("VALOR:     " + dinheiroBR.format(t.getAmount()));
                printWriter.println("----------------------------------------");
                printWriter.println("DESCRIÇÃO:");
                printWriter.println(t.getDescription());
                printWriter.println("----------------------------------------");
                printWriter.println("AUTENTICAÇÃO:");
                printWriter.println(java.util.UUID.randomUUID().toString().substring(0, 18).toUpperCase());
                printWriter.println("========================================");
                
                JOptionPane.showMessageDialog(this, "Comprovante salvo na pasta do projeto:\n" + nomeArquivo);
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + e.getMessage());
            }
        }
    }

    public class CorDaLinha extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object tipoObj = table.getValueAt(row, 1); 
            
            if (tipoObj != null) {
                String tipo = tipoObj.toString();
                if (tipo.equals("RECEITA")) c.setForeground(new Color(0, 153, 0)); 
                else if (tipo.equals("DESPESA")) c.setForeground(Color.RED); 
                else c.setForeground(Color.BLACK);
            }
            if (isSelected) c.setBackground(table.getSelectionBackground());
            else c.setBackground(Color.WHITE);
            
            return c;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblExtrato = new javax.swing.JTable();
        btnSair = new javax.swing.JButton();
        btnFiltroCreditos = new javax.swing.JButton();
        btnFiltroPix = new javax.swing.JButton();
        btnNovoLancamento = new javax.swing.JButton();
        btnFiltroTodos = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        lblUsuarioInfo = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        lblFiltros = new javax.swing.JLabel();
        btnFiltroDebitos = new javax.swing.JButton();
        btnTransferencia = new javax.swing.JButton();
        btnAtualizarExtrato = new javax.swing.JButton();
        btnOlho = new javax.swing.JButton();
        lblRelogio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extrato");
        setBackground(new java.awt.Color(0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblExtrato.setBackground(new java.awt.Color(44, 47, 51));
        tblExtrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "TIPO", "METODO", "VALOR", "DESCRIÇÃO", "DATA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExtrato.setGridColor(new java.awt.Color(51, 204, 255));
        tblExtrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExtratoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblExtrato);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 490, 360));

        btnSair.setBackground(new java.awt.Color(128, 0, 0));
        btnSair.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("SAIR");
        btnSair.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 60, 20));

        btnFiltroCreditos.setBackground(new java.awt.Color(128, 0, 0));
        btnFiltroCreditos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFiltroCreditos.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroCreditos.setText("CRÉDITO");
        btnFiltroCreditos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnFiltroCreditos.setMaximumSize(new java.awt.Dimension(56, 26));
        btnFiltroCreditos.setMinimumSize(new java.awt.Dimension(56, 26));
        btnFiltroCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroCreditosActionPerformed(evt);
            }
        });
        getContentPane().add(btnFiltroCreditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 160, 40));

        btnFiltroPix.setBackground(new java.awt.Color(128, 0, 0));
        btnFiltroPix.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFiltroPix.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroPix.setText("PIX");
        btnFiltroPix.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnFiltroPix.setMaximumSize(new java.awt.Dimension(57, 26));
        btnFiltroPix.setMinimumSize(new java.awt.Dimension(57, 26));
        btnFiltroPix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroPixActionPerformed(evt);
            }
        });
        getContentPane().add(btnFiltroPix, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 160, 40));

        btnNovoLancamento.setBackground(new java.awt.Color(128, 0, 0));
        btnNovoLancamento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNovoLancamento.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoLancamento.setText("NOVO LANÇAMENTO");
        btnNovoLancamento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnNovoLancamento.setMaximumSize(new java.awt.Dimension(56, 26));
        btnNovoLancamento.setMinimumSize(new java.awt.Dimension(56, 26));
        btnNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoLancamentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovoLancamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 160, 40));

        btnFiltroTodos.setBackground(new java.awt.Color(128, 0, 0));
        btnFiltroTodos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFiltroTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroTodos.setText("TODOS");
        btnFiltroTodos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnFiltroTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroTodosActionPerformed(evt);
            }
        });
        getContentPane().add(btnFiltroTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 160, 40));

        btnEditarUsuario.setBackground(new java.awt.Color(128, 0, 0));
        btnEditarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarUsuario.setText("EDITAR USUÁRIO");
        btnEditarUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 130, 20));

        lblUsuarioInfo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuarioInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioInfo.setText("Usuário:");
        getContentPane().add(lblUsuarioInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 390, -1));

        lblSaldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldo.setText("SALDO:");
        getContentPane().add(lblSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 130, -1));

        lblFiltros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltros.setForeground(new java.awt.Color(255, 255, 255));
        lblFiltros.setText("Filtros e ações:");
        getContentPane().add(lblFiltros, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 90, -1));

        btnFiltroDebitos.setBackground(new java.awt.Color(128, 0, 0));
        btnFiltroDebitos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFiltroDebitos.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroDebitos.setText("DÉBITO");
        btnFiltroDebitos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnFiltroDebitos.setMaximumSize(new java.awt.Dimension(57, 26));
        btnFiltroDebitos.setMinimumSize(new java.awt.Dimension(57, 26));
        btnFiltroDebitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroDebitosActionPerformed(evt);
            }
        });
        getContentPane().add(btnFiltroDebitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 160, 40));

        btnTransferencia.setBackground(new java.awt.Color(128, 0, 0));
        btnTransferencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        btnTransferencia.setText("TRANSFERÊNCIA");
        btnTransferencia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnTransferencia.setMaximumSize(new java.awt.Dimension(56, 26));
        btnTransferencia.setMinimumSize(new java.awt.Dimension(56, 26));
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciaActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 160, 40));

        btnAtualizarExtrato.setBackground(new java.awt.Color(128, 0, 0));
        btnAtualizarExtrato.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAtualizarExtrato.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizarExtrato.setText("ATUALIZAR");
        btnAtualizarExtrato.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        btnAtualizarExtrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarExtratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtualizarExtrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 90, 20));

        btnOlho.setBackground(new java.awt.Color(44, 47, 51));
        btnOlho.setForeground(new java.awt.Color(255, 255, 255));
        btnOlho.setText("👁️");
        btnOlho.setToolTipText("Ocultar ou Mostrar Saldo");
        btnOlho.setBorder(null);
        btnOlho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOlhoActionPerformed(evt);
            }
        });
        getContentPane().add(btnOlho, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 30, 20));

        lblRelogio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRelogio.setForeground(new java.awt.Color(255, 255, 255));
        lblRelogio.setText("00:00:00");
        getContentPane().add(lblRelogio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 130, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -260, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
           int resposta = JOptionPane.showConfirmDialog(this, 
                "Tem a certeza que deseja sair da sua conta?", 
                "Fazer Logout", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (resposta == JOptionPane.YES_OPTION) {
            Tela_Login login = new Tela_Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null); 
            this.dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnFiltroCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroCreditosActionPerformed
        carregarExtrato("CREDITO");
    }//GEN-LAST:event_btnFiltroCreditosActionPerformed

    private void btnFiltroPixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroPixActionPerformed
        carregarExtrato("PIX");
    }//GEN-LAST:event_btnFiltroPixActionPerformed

    private void btnNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoLancamentoActionPerformed
       String[] tipos = {"RECEITA", "DESPESA"};
        int escolhaTipo = JOptionPane.showOptionDialog(null, "O dinheiro está Entrando ou Saindo?", "Tipo de Movimentação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipos, tipos[0]);
        
        if (escolhaTipo == -1) return; 
        String tipo = tipos[escolhaTipo];

        String[] metodos = {"PIX", "CREDITO", "DEBITO"};
        Object escolhaMetodo = JOptionPane.showInputDialog(null, "Qual o meio de pagamento?", "Método",
                JOptionPane.QUESTION_MESSAGE, null, metodos, metodos[0]);
        
        if (escolhaMetodo == null) return;
        String metodo = escolhaMetodo.toString();

        String valorStr = JOptionPane.showInputDialog("Valor (Use ponto . para centavos):");
        if (valorStr == null || valorStr.isEmpty()) return;
        
        try {
            double valor = Double.parseDouble(valorStr);

            if (tipo.equals("DESPESA")) {
                if (valor > this.usuarioLogado.getBalance()) {
                    JOptionPane.showMessageDialog(this, 
                        "Saldo Insuficiente.\nVocê não pode gastar mais do que tem na conta.", 
                        "Operação Negada", JOptionPane.WARNING_MESSAGE);
                    return; 
                }
            }

            String descricao = JOptionPane.showInputDialog("Descrição da transação:");
            Transaction t = new Transaction();
            t.setUserId(usuarioLogado.getId());
            t.setType(tipo);      
            t.setMethod(metodo); 
            t.setAmount(valor);
            t.setDescription(descricao);
            
            TransactionDAO dao = new TransactionDAO();
            dao.createTransaction(t);
            
            Som.reproduzirSucesso();
            gerarComprovante(t);
            btnAtualizarExtratoActionPerformed(null); 
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido! Digite apenas números e ponto.");
        }
    }//GEN-LAST:event_btnNovoLancamentoActionPerformed

    private void btnFiltroTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroTodosActionPerformed
        carregarExtrato(null);
    }//GEN-LAST:event_btnFiltroTodosActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        if (this.usuarioLogado != null) {
            Tela_Cadastro telaEditar = new Tela_Cadastro(this.usuarioLogado);
            telaEditar.setVisible(true);
            telaEditar.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void btnFiltroDebitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroDebitosActionPerformed
        carregarExtrato("DEBITO");
    }//GEN-LAST:event_btnFiltroDebitosActionPerformed

    private void btnTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciaActionPerformed
        btnTransferencia.setEnabled(false);
        Tela_Extrato.this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        
        try {
            String chave = JOptionPane.showInputDialog(this, 
                    "Para quem você deseja transferir?\n(Digite o CPF, Email ou ID)", 
                    "Realizar Transferência (PIX)", JOptionPane.QUESTION_MESSAGE);

            if (chave == null || chave.trim().isEmpty()) return;

            UserDAO dao = new UserDAO();
            User destinatario = dao.buscarPorChavePix(chave);

            if (destinatario == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (destinatario.getId() == this.usuarioLogado.getId()) {
                JOptionPane.showMessageDialog(this, "Você não pode fazer um PIX para si mesmo.", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String valorStr = JOptionPane.showInputDialog(this, 
                    "Favorecido: " + destinatario.getName() + "\n\nQual o valor da transferência?", 
                    "Confirmar Favorecido", JOptionPane.QUESTION_MESSAGE);

            if (valorStr == null || valorStr.isEmpty()) return;

            double valor = Double.parseDouble(valorStr);

            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero.");
                return;
            }

            if (valor > this.usuarioLogado.getBalance()) {
                JOptionPane.showMessageDialog(this, 
                    "Saldo Insuficiente para realizar a transferência.\nSeu saldo atual: " + dinheiroBR.format(this.usuarioLogado.getBalance()), 
                    "Operação Negada", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            javax.swing.JPasswordField pf = new javax.swing.JPasswordField();
            int acao = JOptionPane.showConfirmDialog(this, pf, 
                    "SEGURANÇA: Digite sua senha para confirmar o envio de " + dinheiroBR.format(valor), 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (acao != JOptionPane.OK_OPTION) return; 

            String senhaDigitada = new String(pf.getPassword());
            if (!senhaDigitada.equals(this.usuarioLogado.getPassword())) {
                JOptionPane.showMessageDialog(this, "Senha incorreta! Transação cancelada.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TransactionDAO tDao = new TransactionDAO();
            Transaction tOrigem = new Transaction();
            tOrigem.setUserId(this.usuarioLogado.getId());
            tOrigem.setType("DESPESA"); 
            tOrigem.setMethod("PIX");
            tOrigem.setAmount(valor);
            tOrigem.setDescription("PIX enviado para " + destinatario.getName());
            tDao.createTransaction(tOrigem);

            Transaction tDestino = new Transaction();
            tDestino.setUserId(destinatario.getId());
            tDestino.setType("RECEITA");
            tDestino.setMethod("PIX");
            tDestino.setAmount(valor);
            tDestino.setDescription("PIX recebido de " + this.usuarioLogado.getName());
            tDao.createTransaction(tDestino);

            Som.reproduzirSucesso();
            
            if (valor == 666.00) {
                JOptionPane.showMessageDialog(this, "🔥 TRANSFERÊNCIA INFERNAL REALIZADA! 🔥\nO valor da besta foi enviado.", "HellBank 666", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
            }

            gerarComprovante(tOrigem);
            btnAtualizarExtratoActionPerformed(null);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite apenas números e ponto.");
        } finally {
            btnTransferencia.setEnabled(true);
            Tela_Extrato.this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_btnTransferenciaActionPerformed

    private void btnAtualizarExtratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarExtratoActionPerformed
        carregarExtrato(null); 
        atualizarCabecalho();
    }//GEN-LAST:event_btnAtualizarExtratoActionPerformed

    private void btnOlhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOlhoActionPerformed
       saldoVisivel = !saldoVisivel;
        atualizarCabecalho();
    }//GEN-LAST:event_btnOlhoActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // Como o tamanho é fixo (680x480), usamos esses valores direto
        int w = 680;
        int h = 480;
        
        // 1. AJUSTA O FUNDO (Para cobrir tudo)
        try {
            javax.swing.ImageIcon iconOriginal = new javax.swing.ImageIcon(getClass().getResource("/Imagens/Hellbank2.jpg"));
            java.awt.Image img = iconOriginal.getImage();
            java.awt.Image imgRedimensionada = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            
            jLabel1.setIcon(new javax.swing.ImageIcon(imgRedimensionada));
            jLabel1.setBounds(0, 0, w, h);
            
        } catch (Exception e) {
            System.out.println("Erro imagem: " + e.getMessage());
        }

        // 2. AJUSTA A LATERAL DIREITA (Reposiciona botões baseado no tamanho fixo)
        int larguraBotoes = 160;
        int margemDireita = 30;
        int xColunaDireita = w - larguraBotoes - margemDireita;
        
        if (lblSaldo != null) lblSaldo.setLocation(xColunaDireita, lblSaldo.getY());
        if (lblFiltros != null) lblFiltros.setLocation(xColunaDireita, lblFiltros.getY());
        
        if (btnFiltroTodos != null) btnFiltroTodos.setLocation(xColunaDireita, btnFiltroTodos.getY());
        if (btnFiltroCreditos != null) btnFiltroCreditos.setLocation(xColunaDireita, btnFiltroCreditos.getY());
        if (btnFiltroDebitos != null) btnFiltroDebitos.setLocation(xColunaDireita, btnFiltroDebitos.getY());
        if (btnFiltroPix != null) btnFiltroPix.setLocation(xColunaDireita, btnFiltroPix.getY());
        if (btnNovoLancamento != null) btnNovoLancamento.setLocation(xColunaDireita, btnNovoLancamento.getY());
        if (btnTransferencia != null) btnTransferencia.setLocation(xColunaDireita, btnTransferencia.getY());
        
        if (btnOlho != null) btnOlho.setLocation(xColunaDireita + 130, lblSaldo.getY());
        if (lblRelogio != null) lblRelogio.setLocation(w - 150, h - 30);

        int yTopo = 10;
        if (btnSair != null) btnSair.setLocation(w - btnSair.getWidth() - 10, yTopo);
        if (btnAtualizarExtrato != null) btnAtualizarExtrato.setLocation(btnSair.getX() - btnAtualizarExtrato.getWidth() - 10, yTopo);
        if (btnEditarUsuario != null) btnEditarUsuario.setLocation(btnAtualizarExtrato.getX() - btnEditarUsuario.getWidth() - 10, yTopo);

        // 3. AJUSTA A TABELA
        int larguraTabela = xColunaDireita - 30; 
        int alturaTabela = h - jScrollPane1.getY() - 20;
        
        if (larguraTabela > 0 && alturaTabela > 0) {
            jScrollPane1.setSize(larguraTabela, alturaTabela);
        }
        
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_formComponentResized

    private void tblExtratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExtratoMouseClicked
        if (evt.getClickCount() == 2) {
            int linha = tblExtrato.getSelectedRow();
            if (linha != -1) {
                String valor = tblExtrato.getValueAt(linha, 3).toString();
                String desc = tblExtrato.getValueAt(linha, 4).toString();
                
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Detalhes da Transação:\n\n" + 
                    "Valor: " + valor + "\n" +
                    "Descrição: " + desc, 
                    "Detalhes", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tblExtratoMouseClicked

    public static void main(String args[]) {
           try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Tela_Extrato().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizarExtrato;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnFiltroCreditos;
    private javax.swing.JButton btnFiltroDebitos;
    private javax.swing.JButton btnFiltroPix;
    private javax.swing.JButton btnFiltroTodos;
    private javax.swing.JButton btnNovoLancamento;
    private javax.swing.JButton btnOlho;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTransferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltros;
    private javax.swing.JLabel lblRelogio;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblUsuarioInfo;
    private javax.swing.JTable tblExtrato;
    // End of variables declaration//GEN-END:variables
}
