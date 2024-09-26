package controller;

import java.io.IOException;
import java.util.List;

import dao.ConsultaDAO;
import dao.UsuarioDAO;

import model.Consulta;
import model.Paciente;

public class PacienteController extends UsuarioController {

    public PacienteController(UsuarioDAO usuarioDAO, ConsultaDAO consultaDAO) throws IOException {
        super(usuarioDAO, consultaDAO);

    }

    /*  FUNÇOES A SEREM IMPLEMENTADAS
     *      ver consultas dele mesmo
     *      visualizar dados da consulta
     *  
    */

    public void exibirConsultas(Paciente paciente) throws IOException {
        List<Consulta> consultas;

        int idPac = paciente.getId();

        consultas = consultaDAO.getConsultasByIdPaciente(idPac);

        for ( Consulta consulta : consultas ) {
            System.out.println("Consulta dia " + consulta.getData());
        }
    }

    public void visualizarDadosConsulta(Consulta consulta) throws IOException {
        System.out.println("Dados da consulta");
        System.out.println("Id: " + consulta.getIdConsulta());
        System.out.println("Paciente: " + consulta.getPaciente());
        System.out.println("Medico: " + consulta.getPaciente());
        System.out.println("Data e hora: " + consulta.getData());
    }
}
