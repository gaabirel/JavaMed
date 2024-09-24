/*
 * Componente responsavel pelo acesso dos usuarios
 *
 * made by Davi
 */

import javax.swing.*;
import java.awt.*;

public class TelaAcesso {

    // Variaveis para o JOptionPane
    private JTextField login;
    private JPasswordField senha;

    // Construtor
    public TelaAcesso(){

        // Instanciando os componentes de login
        login = new JTextField();
        senha = new JPasswordField();

        // Adicionando os componentes a tela de login
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(2,1));

        // Login
        painel.add(new JLabel("Login"));
        painel.add(login);

        // Senha
        painel.add(new JLabel("Senha"));
        painel.add(senha);

        // Setando o tipo de optionpane
        int option = JOptionPane.showConfirmDialog(null, painel, "Acesso ao Sistema",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    }

    // Métodos get
    public String getLogin(){
        return login.getText();
    }

    public char[] getSenha(){
        return senha.getPassword();
    }

}
