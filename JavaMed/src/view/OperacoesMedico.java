package view;

import javax.swing.*;

public class OperacoesMedico extends JOptionPane {

    // Criando a label para retornar as consultas
    private JLabel label;

    public OperacoesMedico(int opcao) {

        label = new JLabel();

        if (opcao == 1){
            label.setText("Consultas...");
        }

        else if (opcao == 2){
            label.setText("Pacientes...");
        }

        // Adicionando o label ao JOptionPane
        this.add(label);

        // Especificando o tipo de OptionPane
        JOptionPane.showMessageDialog(null, label, "Consultas", JOptionPane.INFORMATION_MESSAGE);

    }

}
