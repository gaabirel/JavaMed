package view;

import javax.swing.*;
import controller.MedicoController;
import dao.MedicoDAO;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUIMedico extends JFrame implements ActionListener {

    MedicoController medicoController;
    int id;
    // Criando objetos presentes no Frame

    // Label
    final JLabel labelMedico;

    // Botoes
    final JButton botaoConsultas;
    final JButton botaoVoltar;

    // Construtor
    GUIMedico(String nomeMedico) {
        try {
            medicoController = new MedicoController(new MedicoDAO());
            id = medicoController.getInfoLogin(nomeMedico);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Configurando o frame
        this.setTitle("Medico");
        this.setIconImage(new ImageIcon("JavaMed/icones/medical-symbol.png").getImage());
        this.setLayout(new BorderLayout());
        this.setSize(600, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Criando a Label
        labelMedico = new JLabel("Seja Bem-vindo, doutor " + nomeMedico);
        labelMedico.setFont(new Font("Arial", Font.BOLD, 36));

        // Configurando os botoes
            // botaoConsultas
        botaoConsultas = new JButton();
        botaoConsultas.setText("Ver consultas");
        botaoConsultas.setFont(new Font("Arial", Font.BOLD, 24));
        botaoConsultas.setIcon(new ImageIcon("JavaMed/icones/consultando.png"));
        botaoConsultas.setVerticalTextPosition(SwingConstants.BOTTOM);
        botaoConsultas.setHorizontalTextPosition(SwingConstants.CENTER);
        botaoConsultas.setFocusable(false);
        botaoConsultas.addActionListener(this);

            // botaoVoltar
        botaoVoltar = new JButton();
        botaoVoltar.setFocusable(false);
        botaoVoltar.setText("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoVoltar.setIcon(new ImageIcon("JavaMed/icones/de-volta.png"));
        botaoVoltar.setHorizontalTextPosition(SwingConstants.RIGHT);
        botaoVoltar.setVerticalTextPosition(SwingConstants.CENTER);
        botaoVoltar.setPreferredSize(new Dimension(150, 50));
        botaoVoltar.addActionListener(this);

        // Adicionando os componentes ao frame
        this.add(labelMedico, BorderLayout.NORTH);
        this.add(botaoConsultas, BorderLayout.CENTER);
        this.add(botaoVoltar, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == botaoConsultas) {

            new OperacoesMedico(1, id);
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
