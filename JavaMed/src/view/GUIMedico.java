import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMedico extends JFrame implements ActionListener {

    // Criando objetos presentes no Frame
        // Paineis
    final JPanel painelMedico;

    // Label
    final JLabel labelMedico;

    // Botoes
    final JButton botaoConsultas;
    final JButton botaoPacientes;
    final JButton botaoVoltar;

    // Construtor
    GUIMedico(String nomeMedico) {

        // Configurando o frame
        this.setTitle("Medico");
        this.setIconImage(new ImageIcon("icones/medical-symbol.png").getImage());
        this.setLayout(new BorderLayout());
        this.setSize(750, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Criando a Label
        labelMedico = new JLabel("Seja Bem-vindo, doutor " + nomeMedico);
        labelMedico.setFont(new Font("Arial", Font.BOLD, 42));

        // Configurando os botoes
            // botaoConsultas
        botaoConsultas = new JButton();
        botaoConsultas.setText("Ver consultas");
        botaoConsultas.setFont(new Font("Arial", Font.BOLD, 34));
        botaoConsultas.setIcon(new ImageIcon("icones/consultando.png"));
        botaoConsultas.setVerticalTextPosition(SwingConstants.BOTTOM);
        botaoConsultas.setHorizontalTextPosition(SwingConstants.CENTER);
        botaoConsultas.setFocusable(false);
        botaoConsultas.addActionListener(this);

            // botaoPacientes
        botaoPacientes = new JButton();
        botaoPacientes.setText("Ver pacientes");
        botaoPacientes.setFont(new Font("Arial", Font.BOLD, 34));
        botaoPacientes.setIcon(new ImageIcon("icones/patient.png"));
        botaoPacientes.setVerticalTextPosition(SwingConstants.BOTTOM);
        botaoPacientes.setHorizontalTextPosition(SwingConstants.CENTER);
        botaoPacientes.setFocusable(false);
        botaoPacientes.addActionListener(this);

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


        // Configurando os paineis
        painelMedico = new JPanel();
        painelMedico.setLayout(new GridLayout(2, 1, 20, 20));
        painelMedico.add(botaoConsultas);
        painelMedico.add(botaoPacientes);

        // Adicionando os componentes ao frame
        this.add(labelMedico, BorderLayout.NORTH);
        this.add(painelMedico, BorderLayout.CENTER);
        this.add(botaoVoltar, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoConsultas) {
            new OperacoesMedico(1);
        }

        if (e.getSource() == botaoPacientes) {
            new OperacoesMedico(2);
        }

        if (e.getSource() == botaoVoltar) {
            new GUI();
            this.dispose();
        }

    }

}
