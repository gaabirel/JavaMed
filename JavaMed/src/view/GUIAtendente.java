package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUIAtendente extends JFrame implements ActionListener {

    // Criando os objetos a serem usados
        // Label
    final JLabel label;

        // painel
    final JPanel painel;

        // Botao
    final JButton botaoMarcarConsulta;
    final JButton botaoExcluirPaciente;
    final JButton botaoRegistrarPaciente;
    final JButton botaoVerConsultas;
    final JButton botaoListarPacientes;
    final JButton botaoAlterarPaciente;
    final JButton botaoVoltar;

    // Construtor
    public GUIAtendente(String nomeAtendente) {

        // Configuracoes iniciais
        this.setTitle("Atendente");
        this.setIconImage(new ImageIcon("icones/medical-symbol.png").getImage());
        this.setLayout(new BorderLayout());
        this.setSize(750, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Configurando a label
        label = new JLabel("Seja Bem-vindo, " + nomeAtendente);
        label.setFont(new Font("Arial", Font.BOLD, 42));

        // Configurando os botoes
            // botaoMarcarConsulta
        botaoMarcarConsulta = new JButton();
        botaoMarcarConsulta.setFocusable(false);
        botaoMarcarConsulta.setText("Marcar Consulta");
        botaoMarcarConsulta.setFont(new Font("Arial", Font.BOLD, 34));
        botaoMarcarConsulta.addActionListener(this);

            // botaoExcluirPaciente
        botaoExcluirPaciente = new JButton();
        botaoExcluirPaciente.setFocusable(false);
        botaoExcluirPaciente.setText("Excluir Paciente");
        botaoExcluirPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoExcluirPaciente.addActionListener(this);

            // botaoRegistrarPaciente
        botaoRegistrarPaciente = new JButton();
        botaoRegistrarPaciente.setFocusable(false);
        botaoRegistrarPaciente.setText("Registrar Paciente");
        botaoRegistrarPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoRegistrarPaciente.addActionListener(this);

            // botaoVerConsultas
        botaoVerConsultas = new JButton();
        botaoVerConsultas.setFocusable(false);
        botaoVerConsultas.setText("Ver Consultas");
        botaoVerConsultas.setFont(new Font("Arial", Font.BOLD, 34));
        botaoVerConsultas.addActionListener(this);

            // botaoListarPacientes
        botaoListarPacientes = new JButton();
        botaoListarPacientes.setFocusable(false);
        botaoListarPacientes.setText("Listar Pacientes");
        botaoListarPacientes.setFont(new Font("Arial", Font.BOLD, 34));
        botaoListarPacientes.addActionListener(this);

            // botaoAlterarPaciente
        botaoAlterarPaciente = new JButton();
        botaoAlterarPaciente.setFocusable(false);
        botaoAlterarPaciente.setText("Alterar Paciente");
        botaoAlterarPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoAlterarPaciente.addActionListener(this);

            // botaoVoltar
        botaoVoltar = new JButton();
        botaoVoltar.setFocusable(false);
        botaoVoltar.setText("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoVoltar.setIcon(new ImageIcon("icones/de-volta.png"));
        botaoVoltar.setHorizontalTextPosition(SwingConstants.RIGHT);
        botaoVoltar.setVerticalTextPosition(SwingConstants.CENTER);
        botaoVoltar.setPreferredSize(new Dimension(150, 50));
        botaoVoltar.addActionListener(this);

        // Configurando o painel
        painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1, 20, 20));
        painel.add(botaoMarcarConsulta);
        painel.add(botaoExcluirPaciente);
        painel.add(botaoRegistrarPaciente);
        painel.add(botaoVerConsultas);
        painel.add(botaoListarPacientes);
        painel.add(botaoAlterarPaciente);

        // Adicionando os componentes ao frame
        this.add(label, BorderLayout.NORTH);
        this.add(painel, BorderLayout.CENTER);
        this.add(botaoVoltar, BorderLayout.SOUTH);
        this.setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoMarcarConsulta) {

        }

        if (e.getSource() == botaoExcluirPaciente) {

        }

        if (e.getSource() == botaoRegistrarPaciente) {

        }

        if (e.getSource() == botaoVerConsultas) {

        }

        if (e.getSource() == botaoListarPacientes) {

        }

        if (e.getSource() == botaoAlterarPaciente) {

        }

        if (e.getSource() == botaoVoltar) {

            try {
                new GUI();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();

        }

    }

}
