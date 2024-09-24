package controller;

import model.Atendente;
import view.GUIAtendente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtendenteController implements ActionListener {
    private Atendente atendente;
    private GUIAtendente guiAtendente;

    // Construtor p/ model e view
    public AtendenteController(Atendente atendente, GUIAtendente guiAtendente) {
        this.atendente = atendente;
        this.guiAtendente = guiAtendente;

        // Controlador sendo listener dos eventos da GUI Atendente
        this.guiAtendente.botaoMarcarConsulta.addActionListener(this);
        this.guiAtendente.botaoExcluirPaciente.addActionListener(this);
        this.guiAtendente.botaoRegistrarPaciente.addActionListener(this);
        this.guiAtendente.botaoVerConsultas.addActionListener(this);
        this.guiAtendente.botaoListarPacientes.addActionListener(this);
        this.guiAtendente.botaoAlterarPaciente.addActionListener(this);
        this.guiAtendente.botaoVoltar.addActionListener(this);
    }

    // Action Events da GUI Atendente
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guiAtendente.botaoMarcarConsulta) {
            // Marcar consulta
            marcarConsulta();
        } else if (e.getSource() == guiAtendente.botaoExcluirPaciente) {
            // Excluir paciente
            excluirPaciente();
        } else if (e.getSource() == guiAtendente.botaoRegistrarPaciente) {
            // Registrar paciente
            registrarPaciente();
        } else if (e.getSource() == guiAtendente.botaoVerConsultas) {
            // Ver consultas
            verConsultas();
        } else if (e.getSource() == guiAtendente.botaoListarPacientes) {
            // Listar pacientes
            listarPacientes();
        } else if (e.getSource() == guiAtendente.botaoAlterarPaciente) {
            // Alterar paciente
            alterarPaciente();
        } else if (e.getSource() == guiAtendente.botaoVoltar) {
            // Voltar para tela anterior
            voltar();
        }
    }

    // Metodos p/ cada açao (teste)
    private void marcarConsulta() {
        JOptionPane.showMessageDialog(null, "Marcar consulta");
    }

    private void excluirPaciente() {
        JOptionPane.showMessageDialog(null, "Excluir paciente");
    }

    private void registrarPaciente() {
        JOptionPane.showMessageDialog(null, "Registrar paciente");
    }

    private void verConsultas() {
        JOptionPane.showMessageDialog(null, "Ver consultas");
    }

    private void listarPacientes() {
        JOptionPane.showMessageDialog(null, "Listar pacientes");
    }

    private void alterarPaciente() {
        JOptionPane.showMessageDialog(null, "Alterar paciente selecionado.");
    }

    private void voltar() {
        JOptionPane.showMessageDialog(null, "Voltar");

        // new GUI();
        guiAtendente.dispose();
    }
}
