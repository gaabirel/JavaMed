package view;

import java.io.IOException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ConsultaController;
import dao.ConsultaDAO;
import model.Consulta;

public class OperacoesMedico extends JOptionPane {

    // Criando a label para retornar as consultas
    private JLabel label;

    public OperacoesMedico(int opcao, int codigo) {

        label = new JLabel();

        if (opcao == 1){
            try {
                    ConsultaController consultaController = new ConsultaController(new ConsultaDAO());               

                    List<Consulta> consultas = consultaController.getConsultasByIdMedico(codigo);

                    // head da tabela
                    String[] colunasConsultas = {"Consulta", "Paciente", "Médico", "Data/Hora", "Diagnóstico"};
                    DefaultTableModel modeloConsultas = new DefaultTableModel(colunasConsultas, 0);

                    if (consultas != null && !consultas.isEmpty()) {
                        for (Consulta consulta : consultas) {
                            int nomePaciente = consulta.getPaciente();
                            int nomeMedico = consulta.getMedico();
                            String dataHora = consulta.getData().toString();
                            int idConsulta = consulta.getIdConsulta();
                            String diagnostico = consulta.getDiagnostico();

                            modeloConsultas.addRow(new Object[]{idConsulta, nomePaciente, nomeMedico, dataHora, diagnostico});
                        }
                
                        // tabela
                        JTable tabelaConsultas = new JTable(modeloConsultas);
                        JScrollPane painelRolagemConsultas = new JScrollPane(tabelaConsultas);
                        JOptionPane.showMessageDialog(null, painelRolagemConsultas, "Consultas", JOptionPane.INFORMATION_MESSAGE);
                
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "O medico não possui consultas registradas.");
                    }
                    
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        else if (opcao == 2){
            label.setText("Pacientes em breve...");
            this.add(label);
            JOptionPane.showMessageDialog(null, label, "Consultas", JOptionPane.INFORMATION_MESSAGE);
        }


    }

}
