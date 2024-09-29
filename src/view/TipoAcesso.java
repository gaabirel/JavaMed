package view;

import java.io.IOException;
import javax.swing.*;
import controller.*;
import dao.UsuarioDAO;
import dao.Config;

public class TipoAcesso {

    // Classe para verificacao tipo de usuario

    // Criando os componentes a serem usados
    final String[] opcoes = {"Atendente", "Medico", "Administrador"};

    final JLabel label;
    final JPanel painel;

    private int resultado;

    // Construtor
    public TipoAcesso(){

        label = new JLabel("Você é...");
        painel = new JPanel();
        painel.add(label);

        // Mostrando o optionPane
        resultado = JOptionPane.showOptionDialog(
                null, painel, "Selecione uma opção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opcoes, opcoes[0]
        );

    }

    // Metodo para acessar a opcao escolhida
    public void abrirGuia(String login, String senha) throws IOException{
        UsuarioController usuarioController;
  
        if (resultado == 0){
            usuarioController = new UsuarioController(new UsuarioDAO(Config.CAMINHO_ATENDENTES));
            if(usuarioController.login(login, senha)){
                new GUIAtendente(login);
            }
        }

        else if (resultado == 1){
            usuarioController = new UsuarioController(new UsuarioDAO(Config.CAMINHO_MEDICOS));
            if(usuarioController.login(login, senha)){
                new GUIMedico(login);
            }
        }
    
        else if (resultado == 2){

            if(login.equals("admin") && senha.equals("admin")){
                new GUIAdm(login);
            }
        }

    }
}
