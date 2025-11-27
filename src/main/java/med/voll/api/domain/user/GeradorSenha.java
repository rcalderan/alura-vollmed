package med.voll.api.domain.user;
import java.security.SecureRandom;


public abstract class GeradorSenha {
    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS = "0123456789";
    private static final String SIMBOLOS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final String TODOS = LETRAS + DIGITOS + SIMBOLOS;

    private static final SecureRandom random = new SecureRandom();

    public static String gerarSenha(int tamanho) {
        StringBuilder senha = new StringBuilder(tamanho);

        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(TODOS.length());
            senha.append(TODOS.charAt(index));
        }

        sendPasswordToEmail(senha.toString());

        return senha.toString();
    }

    private static void sendPasswordToEmail(String password){

    }

}
