/*
 * Classe de interface gráfica geral
 *
 * made by Davi
 */
package view;

 import javax.swing.*;


import controller.PacienteController;
import controller.UsuarioController;
import dao.PacienteDAO;
import dao.UsuarioDAO;
import java.io.IOException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    // Definindo atributos para a interface grafica
        // Paineis
    final JPanel painelCentral;

        // Labels
    final JLabel labelCentral;

        // Botoes
    final JButton botaoFuncionario;
    final JButton botaoPaciente;

    // Componentes do controller
    UsuarioController usuarioController;
    PacienteDAO pacienteController;
        
        // Construtor
        public GUI() throws IOException {
            
        usuarioController = new UsuarioController(new UsuarioDAO("C:\\Users\\gabri\\Desktop\\github\\JavaMed\\JavaMed\\data\\medicos.csv"));
        pacienteController = new PacienteDAO("C:\\Users\\gabri\\Desktop\\github\\JavaMed\\JavaMed\\data\\pacientes.csv");

        // Setando configuracoes padrao da interface grafica
        this.setSize(750, 800); // Configurando as dimensoes do frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechando o frame ao apertar no x
        this.setTitle("JavaMED"); // Definindo um titulo para a interface
        this.setIconImage(new ImageIcon("icones/medical-symbol.png").getImage());
        this.setResizable(false); // Impedindo que o usuario redimensione o frame
        this.setLayout(new BorderLayout());

        // Configurando o label
        labelCentral = new JLabel();
        labelCentral.setText("<html><center>JavaMED</center>"
                + "<br> Seja bem-vindo! </html>"); // Adicionando texto a label
        labelCentral.setFont(new Font("Arial", Font.BOLD, 42)); // Setando uma fonte a label
        labelCentral.setIcon(new ImageIcon("icones/medical-symbol.png"));

            // Setando a posicao dos componentes
        labelCentral.setHorizontalTextPosition(JLabel.CENTER);
        labelCentral.setVerticalTextPosition(JLabel.BOTTOM);
        labelCentral.setHorizontalAlignment(JLabel.CENTER);
        labelCentral.setVerticalAlignment(JLabel.CENTER);

        // Configurando os botoes
            // Funcionario
        botaoFuncionario = new JButton();
        botaoFuncionario.setText("Sou Funcionário");
        botaoFuncionario.setFocusable(false);
        botaoFuncionario.setFont(new Font("Arial", Font.BOLD, 34));
        botaoFuncionario.setIcon(new ImageIcon("icones/funcionario.png"));
        botaoFuncionario.setHorizontalTextPosition(JLabel.CENTER);
        botaoFuncionario.setVerticalTextPosition(JLabel.BOTTOM);

            // Adicionando funcionalidade
        botaoFuncionario.addActionListener(this);

            // Paciente
        botaoPaciente = new JButton();
        botaoPaciente.setText("Sou Paciente");
        botaoPaciente.setFocusable(false);
        botaoPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoPaciente.setIcon(new ImageIcon("icones/patient.png"));
        botaoPaciente.setHorizontalTextPosition(JLabel.CENTER);
        botaoPaciente.setVerticalTextPosition(JLabel.BOTTOM);

            // Adicionando funcionalidade
        botaoPaciente.addActionListener(this);


        // Configurando o painelCentral
        painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(1, 2, 20, 20));
        painelCentral.add(botaoFuncionario);
        painelCentral.add(botaoPaciente);

        this.add(labelCentral, BorderLayout.NORTH);
        this.add(painelCentral, BorderLayout.CENTER);
        this.setVisible(true); // Deixando o frame

    }

    // Funcionalidades dos botoes
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoFuncionario) {

            TipoAcesso tipoAcesso = new TipoAcesso();

            // Abrindo a tela de login
            TelaAcesso login = new TelaAcesso();

            // definindo as infos
            String acesso;
            String senha;

            // verificar se os campos estao vazios
                // armazenando as infos
            acesso = login.getLogin();
            senha = new String(login.getSenha());

            // Verificando se os campos foram preenchidos
            if (acesso.strip() == "" || new String(senha).strip() == "" ) {
                JOptionPane.showMessageDialog(null, "Erro! Os campos não podem estar vazios!");
            }

            //conferindo login
            else{
                if (usuarioController.login(acesso, senha)) {
                    tipoAcesso.abrirGuia(acesso);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! Usuário não encontrado!");
                }
            }
            


            // Printando as infos
            System.out.println(acesso);
            System.out.println(senha);




        }

        if (e.getSource() == botaoPaciente) {

            // Abrindo um JOptionPane pra receber o codigo da consulta
            String codigo = JOptionPane.showInputDialog("Digite o codigo da Consulta");
            if (codigo.strip() == ""){
                JOptionPane.showMessageDialog(null, "Erro! Campo vazio!");
            }

            else{
                try {
                    if (!pacienteController.verificarRegistroID(Integer.parseInt(codigo))){
                        JOptionPane.showMessageDialog(null, "Erro! Paciente não está presente!");
                    }

                    else{
                        System.out.println("ola mundo");
                    }
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }

        }

    }

}
