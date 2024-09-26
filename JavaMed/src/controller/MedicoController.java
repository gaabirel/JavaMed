package controller;

import java.io.IOException;
import java.util.List;

import dao.ConsultaDAO;
import dao.UsuarioDAO;
import model.Consulta;
import model.Medico;

public class MedicoController extends UsuarioController {

    public MedicoController
    (UsuarioDAO usuarioDAO, ConsultaDAO consultaDAO) throws IOException {
        super(usuarioDAO, consultaDAO);
    }

    /*  FUNÇOES A SEREM IMPLEMENTADAS
     *      ver consultas marcadas com ele
     *      dar o diagnostico da consulta
     *        
    */

    public void exibirConsultas(Medico medico) throws IOException {
        List<Consulta> consultas;

        int idMed = medico.getId();

        consultas = consultaDAO.getConsultasByIdMedico(idMed);

        for ( Consulta consulta : consultas ) {
            System.out.println("Consulta dia " + consulta.getData());
        }
    }

    public void visualizarDadosConsulta(Consulta consulta) throws IOException {
        System.out.println("Dados da consulta");
        System.out.println("Id: " + consulta.getIdConsulta());
        System.out.println("Paciente: " + consulta.getPaciente());
        System.out.println("Data e hora: " + consulta.getData());
        System.out.println("Diagnostico: " + consulta.getDiagnostico());
    }

    public void registrarDiagnostico(Consulta consulta, String diagnostico) throws IOException {
        consulta.setDiagnostico(diagnostico);
    }
}
