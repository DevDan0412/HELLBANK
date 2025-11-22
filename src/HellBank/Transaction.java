package HellBank;

public class Transaction {
    
    private int id;
    private int userId;
    private String type;        // "RECEITA" ou "DESPESA"
    private String method;      // "PIX", "CREDITO", "DEBITO"
    private double amount;
    private String description;
    private String dateTime;

    public Transaction() {
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    // --- AQUI ESTAVA O ERRO (AGORA CORRIGIDO) ---
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method; // Agora ele salva o valor corretamente!
    }
    // -------------------------------------------

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}