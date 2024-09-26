package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.MedicoController;

public class OperacoesMedico extends JOptionPane {

    private JLabel label;

    public OperacoesMedico(int opcao) {

        // Criando a label para retornar as consultas
        label = new JLabel();

        if (opcao == 1){
            // exemplo de teste
            String[] colunasConsultas = {"id", "Paciente", "Médico", "Data/Hora", "diagnostico"};
            Object[][] dadosConsultas = {
                {"1", "paciente", "medico", "26/09/2024 14:00", "nao tem"},
            };

            // tabela de consultas
            DefaultTableModel modeloConsultas = new DefaultTableModel(dadosConsultas, colunasConsultas);
            JTable tabelaConsultas = new JTable(modeloConsultas);

            // add a tabela de consultas em um JScrollPane para rolagem
            JScrollPane painelRolagemConsultas = new JScrollPane(tabelaConsultas);

            // exibir tabela
            JOptionPane.showMessageDialog(null, painelRolagemConsultas, "Consultas", JOptionPane.INFORMATION_MESSAGE);

            
        }

        else if (opcao == 2){
            // teste
            String[] colunasPacientes = {"id", "Nome", "CPF", "Telefone"};
            Object[][] dadosPacientes = {
                {"2", "nome", "123123123", "987654321"},
            };

            // tabela de pacientes
            DefaultTableModel modeloPacientes = new DefaultTableModel(dadosPacientes, colunasPacientes);
            JTable tabelaPacientes = new JTable(modeloPacientes);

            // add a tabela de pacientes em um JScrollPane para rolagem
            JScrollPane painelRolagemPacientes = new JScrollPane(tabelaPacientes);

            // exibir tabela
            JOptionPane.showMessageDialog(null, painelRolagemPacientes, "Pacientes", JOptionPane.INFORMATION_MESSAGE);
        
        }

        // Adicionando o label ao JOptionPane
        this.add(label);

        // Especificando o tipo de OptionPane
        JOptionPane.showMessageDialog(null, label, "Consultas", JOptionPane.INFORMATION_MESSAGE);

    }

}
