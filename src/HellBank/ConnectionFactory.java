package HellBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane; 

public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
 
    private static final String URL = "jdbc:mysql://localhost:3306/hellbank_db";
    
    private static final String USER = "root";
    
    // PROFESSOR: Por favor, insira sua senha do MySQL abaixo:
    private static final String PASS = "Dam&0412"; 

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            // AVISA o usuário sobre o erro crítico com um pop-up
            JOptionPane.showMessageDialog(
                null, // null para centralizar na tela
                "Erro fatal de conexão com o Banco de Dados.\n" + ex.getMessage(), 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE
            );
            
            // Lança a exceção para parar a execução, 
            // pois o app não pode funcionar sem o banco.
            throw new RuntimeException("Erro na conexão com o banco de dados: ", ex);
        }
    }
}