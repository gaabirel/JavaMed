import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIAdm extends GUIAtendente {

    // Criando os componentes a serem usados
        // Botoes adicionais
    JButton botaoIncluirFuncionario;
    JButton botaoExcluirFuncionario;
    JButton botaoAlterarFuncionario;

    // Construtor
    GUIAdm(String nome) {

        // Puxando todas as configuracoes do atendente
        super(nome); // Puxando o construtor do atendente
        this.setTitle("Administrador"); // Adicionando um novo titulo

        // Configurando os botoes novos
            // botaoIncluirFuncionario
        botaoIncluirFuncionario = new JButton();
        botaoIncluirFuncionario.setFocusable(false);
        botaoIncluirFuncionario.setText("Incluir Funcionario");
        botaoIncluirFuncionario.setFont(new Font("Arial", Font.BOLD, 34));
        botaoIncluirFuncionario.addActionListener(this);

            // botaoExcluirFuncionario
        botaoExcluirFuncionario = new JButton();
        botaoExcluirFuncionario.setFocusable(false);
        botaoExcluirFuncionario.setText("Excluir Funcionario");
        botaoExcluirFuncionario.setFont(new Font("Arial", Font.BOLD, 34));
        botaoExcluirFuncionario.addActionListener(this);

            // botaoAlterarFuncionario
        botaoAlterarFuncionario = new JButton();
        botaoAlterarFuncionario.setFocusable(false);
        botaoAlterarFuncionario.setText("Alterar Funcionario");
        botaoAlterarFuncionario.setFont(new Font("Arial", Font.BOLD, 34));
        botaoAlterarFuncionario.addActionListener(this);

        // Configurando o layout do painel
        super.painel.setLayout(new GridLayout(9, 1, 20, 20));

        // Adicionando os botoes extras
        super.painel.add(botaoIncluirFuncionario);
        super.painel.add(botaoExcluirFuncionario);
        super.painel.add(botaoAlterarFuncionario);

    }

    // Funcionalidade dos botoes
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e); // Puxando as funcionalidades dos botoes do atendente

        // Adicionando as funcionalidades dos botoes extras
        if (e.getSource() == botaoIncluirFuncionario){

        }

        if (e.getSource() == botaoExcluirFuncionario){

        }

        if (e.getSource() == botaoAlterarFuncionario){

        }
    }

}
