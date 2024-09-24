import javax.swing.*;

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
    public void abrirGuia(String nome){

        if (resultado == 0){
            new GUIAtendente(nome);
        }

        else if (resultado == 1){
            new GUIMedico(nome);
        }

        else{
            new GUIAdm(nome);
        }

    }

}
