package HellBank;

public class User {

    // Campos (variáveis)
    private int id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String dob; // Data de Nascimento
    private String password;
    private double balance;
    
    // Campo para armazenar a data de registro
    private String dataCadastro;
    
    // --- NOVO: Campo para a Hierarquia (Admin ou Usuario) ---
    private String tipoPermissao;
    // -------------------------------------------------------

    // ==========================
    // GETTERS (Ler dados)
    // ==========================
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }
    
    public double getBalance() {
        return balance;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    // --- NOVO GETTER ---
    public String getTipoPermissao() {
        return tipoPermissao;
    }
    // -------------------

    // ==========================
    // SETTERS (Gravar dados)
    // ==========================
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // --- NOVO SETTER ---
    public void setTipoPermissao(String tipoPermissao) {
        this.tipoPermissao = tipoPermissao;
    }
    // -------------------

    // Método auxiliar para compatibilidade
    public void setSaldo(double saldo) {
        this.balance = saldo; 
    }
}