package HellBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TransactionDAO {

    // --- 1. CRIAR TRANSAÇÃO E ATUALIZAR SALDO ---
    public void createTransaction(Transaction t) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmtBalance = null;

        try {
            // PASSO A: Gravar o histórico na tabela transactions
            String sql = "INSERT INTO transactions (user_id, type, method, amount, description) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, t.getUserId());
            stmt.setString(2, t.getType());      // RECEITA ou DESPESA
            stmt.setString(3, t.getMethod());    // PIX, CREDITO, DEBITO
            stmt.setDouble(4, t.getAmount());
            stmt.setString(5, t.getDescription());
            stmt.executeUpdate();

            // PASSO B: Atualizar o saldo na tabela users
            String sqlBalance;
            
            // VERIFICAÇÃO DE SINAL:
            if (t.getType().equals("RECEITA")) {
                // Se entrou dinheiro -> SOMA (+)
                sqlBalance = "UPDATE users SET balance = balance + ? WHERE id = ?";
            } else {
                // Se saiu dinheiro (DESPESA) -> SUBTRAI (-)
                sqlBalance = "UPDATE users SET balance = balance - ? WHERE id = ?";
            }
            
            stmtBalance = con.prepareStatement(sqlBalance);
            stmtBalance.setDouble(1, t.getAmount());
            stmtBalance.setInt(2, t.getUserId());
            stmtBalance.executeUpdate();
            
            // --- REMOVI A MENSAGEM DAQUI PARA NÃO REPETIR ---

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na transação: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (stmtBalance != null) stmtBalance.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // --- 2. LER EXTRATO COM FILTROS ---
    public List<Transaction> getFilteredTransactions(int userId, String filterValue) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Transaction> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM transactions WHERE user_id = ?";
            
            // Lógica de Filtro
            if (filterValue != null) {
                if (filterValue.equals("RECEITA") || filterValue.equals("DESPESA")) {
                    sql += " AND type = ?";
                } else {
                    sql += " AND method = ?";
                }
            }
            
            sql += " ORDER BY date_time DESC"; // Mais recentes primeiro

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            
            if (filterValue != null) {
                stmt.setString(2, filterValue);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt("id"));
                t.setUserId(rs.getInt("user_id"));
                t.setType(rs.getString("type"));
                t.setMethod(rs.getString("method")); 
                t.setAmount(rs.getDouble("amount"));
                t.setDescription(rs.getString("description"));
                t.setDateTime(rs.getString("date_time"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler extrato: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}