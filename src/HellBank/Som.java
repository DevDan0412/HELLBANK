package HellBank;

import java.awt.Toolkit;

public class Som {
    
    // Método simples que usa o 'beep' do sistema operativo
    public static void reproduzirSucesso() {
        // Toca um som de aviso padrão do Windows/Mac/Linux
        Toolkit.getDefaultToolkit().beep();
        
        // DICA: Se quiser imprimir no console só para confirmar que funcionou:
        System.out.println("Transacao Concluida");
    }

        static class reproduzirSucesso {

        public reproduzirSucesso() {
        }
    }
}