package HellBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UserDAO {

    // =========================================================================
    // 1. LOGIN: BUSCAR POR CPF
    // =========================================================================
    public User getUserByCpf(String cpf) {
        String sql = "SELECT * FROM users WHERE cpf = ?";
        User usuario = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = mapUser(rs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por CPF: " + e.getMessage());
        }
        return usuario;
    }

    // =========================================================================
    // 2. TRANSFERÊNCIA: BUSCAR POR CHAVE PIX
    // =========================================================================
    public User buscarPorChavePix(String chaveInput) {
        String chaveOriginal = chaveInput.trim();
        String apenasNumeros = chaveOriginal.replaceAll("[^0-9]", "");
        
        String buscaEmail = chaveOriginal;
        String buscaCpf = chaveOriginal;
        
        if (apenasNumeros.length() == 11) {
            buscaCpf = apenasNumeros.substring(0, 3) + "." +
                       apenasNumeros.substring(3, 6) + "." +
                       apenasNumeros.substring(6, 9) + "-" +
                       apenasNumeros.substring(9, 11);
        }
        
        String sql = "SELECT * FROM users WHERE email = ? OR cpf = ?";
        int idBusca = -1;
        boolean buscarPorId = false;

        if (!apenasNumeros.isEmpty() && apenasNumeros.length() < 11) {
            try {
                idBusca = Integer.parseInt(apenasNumeros);
                sql += " OR id = ?";
                buscarPorId = true;
            } catch (NumberFormatException e) {}
        }

        User usuarioEncontrado = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, buscaEmail);
            stmt.setString(2, buscaCpf);

            if (buscarPorId) {
                stmt.setInt(3, idBusca);
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuarioEncontrado = mapUser(rs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar favorecido: " + e.getMessage());
        }

        return usuarioEncontrado;
    }

    // =========================================================================
    // 3. UTILITÁRIO: BUSCAR POR ID
    // =========================================================================
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User usuario = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = mapUser(rs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return usuario;
    }

    // =========================================================================
    // 4. ADMIN: LISTAR TODOS OS USUÁRIOS
    // =========================================================================
    public List<User> readUsers() {
        List<User> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User usuario = mapUser(rs);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // =========================================================================
    // 5. CRUD: CRIAR USUÁRIO
    // =========================================================================
    public void createUser(User u) {
        String sql = "INSERT INTO users (name, cpf, email, phone, dob, password, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getDob());
            stmt.setString(6, u.getPassword());
            stmt.setDouble(7, 0.0);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, 
                    "Erro: Este CPF ou E-mail já estão cadastrados no sistema!", 
                    "Dados Duplicados", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
            }
        }
    }

    // =========================================================================
    // 6. CRUD: ATUALIZAR USUÁRIO
    // =========================================================================
    public void updateUser(User u) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ?, dob = ?, password = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getDob());
            stmt.setString(5, u.getPassword());
            stmt.setInt(6, u.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e.getMessage());
        }
    }

    // =========================================================================
    // 7. CRUD: EXCLUIR USUÁRIO (Individual)
    // =========================================================================
    public void deleteUser(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            String sqlTrans = "DELETE FROM transactions WHERE user_id = ?";
            try (PreparedStatement stmtT = conn.prepareStatement(sqlTrans)) {
                stmtT.setInt(1, id);
                stmtT.executeUpdate();
            }
            
            String sqlUser = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement stmtU = conn.prepareStatement(sqlUser)) {
                stmtU.setInt(1, id);
                stmtU.executeUpdate();
            }
            
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + e.getMessage());
        }
    }

    // =========================================================================
    // 8. CRUD: EXCLUIR TUDO (COM PROTEÇÃO DO MASTER ADMIN)
    // =========================================================================
    public void deleteAllUsers() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            // CPF DO MASTER ADMIN (Daniel Barbosa)
            String cpfMaster = "389.593.408-98";
            
            // 1. Limpa transações de quem NÃO é o chefe
            // (Deleta onde o user_id pertence a alguém com CPF diferente do Master)
            String sqlT = "DELETE FROM transactions WHERE user_id IN (SELECT id FROM users WHERE cpf <> ?)";
            try(PreparedStatement stmt = conn.prepareStatement(sqlT)) { 
                stmt.setString(1, cpfMaster);
                stmt.executeUpdate(); 
            }

            // 2. Limpa usuários que NÃO são o chefe
            String sqlU = "DELETE FROM users WHERE cpf <> ?";
            try(PreparedStatement stmt = conn.prepareStatement(sqlU)) { 
                stmt.setString(1, cpfMaster);
                stmt.executeUpdate(); 
            }

            JOptionPane.showMessageDialog(null, 
                "Limpeza concluída!\n\n" + 
                "Todos os usuários foram removidos,\n" +
                "EXCETO o Administrador Mestre.",
                "Limpeza de Sistema",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar banco: " + e.getMessage());
        }
    }
    
    // =========================================================================
    // ALTERAR HIERARQUIA
    // =========================================================================
    public void atualizarPermissao(int idUsuario, String novaPermissao) {
        String sql = "UPDATE users SET tipo_permissao = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, novaPermissao);
            stmt.setInt(2, idUsuario);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Permissão alterada para: " + novaPermissao);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao mudar permissão: " + e.getMessage());
        }
    }
    
    // =========================================================================
    // MÉTODO AUXILIAR: Mapear ResultSet
    // =========================================================================
    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setCpf(rs.getString("cpf"));
        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("phone"));
        u.setDob(rs.getString("dob"));
        u.setPassword(rs.getString("password"));
        u.setBalance(rs.getDouble("balance"));
        
        try {
            u.setTipoPermissao(rs.getString("tipo_permissao"));
        } catch (Exception e) {
            u.setTipoPermissao("USUARIO");
        }

        try {
            String data = rs.getString("data_cadastro");
            if (data != null) u.setDataCadastro(data);
            else u.setDataCadastro("-");
        } catch (SQLException e) { u.setDataCadastro("-"); }
        
        return u;
    }
}