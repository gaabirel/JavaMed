package view;

import javax.swing.*;

import controller.AtendenteController;
import controller.MedicoController;
import dao.AtendenteDAO;
import dao.MedicoDAO;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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

        if (e.getSource() == botaoExcluirFuncionario) {
            // Solicitar o ID do funcionário a ser excluído
            String funcionarioIdInput = JOptionPane.showInputDialog("Digite o ID do Funcionário a ser excluído:");
            if (funcionarioIdInput == null) {
                return; // Sair se o usuário cancelar
            }
            
            try {
                int funcionarioId = Integer.parseInt(funcionarioIdInput);
                
                // Perguntar se é Atendente ou Médico
                String[] opcoes = {"Atendente", "Médico"};
                int escolha = JOptionPane.showOptionDialog(null, 
                        "Selecione o tipo de funcionário:",
                        "Tipo de Funcionário",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);

                if (escolha == JOptionPane.CLOSED_OPTION) {
                    return; // Se o usuário cancelar, sair
                }
                
                String tipoFuncionario = opcoes[escolha];

                // Controladores para Atendente e Médico
                AtendenteController atendenteController = new AtendenteController(new AtendenteDAO());
                MedicoController medicoController = new MedicoController(new MedicoDAO());

                // Verificar e excluir Atendente ou Médico com base na escolha
                if (tipoFuncionario.equals("Atendente")) {
                    if (atendenteController.verificarRegistroID(funcionarioId)) {
                        int option = JOptionPane.showConfirmDialog(null,
                                "Tem certeza que deseja excluir o atendente com ID " + funcionarioId + "?",
                                "Confirmação de Exclusão",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            atendenteController.excluir(funcionarioId);
                            JOptionPane.showMessageDialog(null, "Atendente excluído com sucesso!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Atendente não encontrado.");
                    }
                } else if (tipoFuncionario.equals("Médico")) {
                    if (medicoController.verificarRegistroID(funcionarioId)) {
                        int option = JOptionPane.showConfirmDialog(null,
                                "Tem certeza que deseja excluir o médico com ID " + funcionarioId + "?",
                                "Confirmação de Exclusão",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            medicoController.excluir(funcionarioId);
                            JOptionPane.showMessageDialog(null, "Médico excluído com sucesso!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Médico não encontrado.");
                    }
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro! ID do Funcionário deve ser um número.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o funcionário. Tente novamente.");
            }
        }

        if (e.getSource() == botaoAlterarFuncionario){

        }
    }

}
