package controller;

import java.io.IOException;
import java.util.List;

import model.Consulta;
import model.Paciente;

import dao.*;

public class AtendenteController extends UsuarioController {

    PacienteDAO pacienteDAO;
    MedicoDAO medicoDAO;
    ConsultaDAO consultaDAO;

    public AtendenteController(UsuarioDAO usuarioDAO) throws IOException {
        super(usuarioDAO);
        this.consultaDAO = new ConsultaDAO();
        this.pacienteDAO = new PacienteDAO();
        this.medicoDAO = new MedicoDAO();
    }

    public void incluirPaciente(int id, String telefone, String cpf, String nome, String login, String senha) throws IOException {
        Paciente paciente = new Paciente(id, telefone, cpf, nome, login, senha);

        pacienteDAO.salvarUnico(paciente);
    }

    public void modificarPaciente(int idAntigo, int idNovo, String telefone, String cpf, String nome, String login, String senha) throws IOException {
        Paciente paciente = new Paciente(idNovo, telefone, cpf, nome, login, senha);

        pacienteDAO.modificar(idAntigo, paciente);
    }

    public void excluirPaciente(int idExc) throws IOException {
        pacienteDAO.excluirUnico(idExc);
    }

    public void incluirConsulta(int idConsulta, int idPaciente, int idMedico, String data, String diagnostico) throws IOException {
        Consulta consulta = new Consulta(idConsulta, idPaciente, idMedico, data, diagnostico);

        consultaDAO.salvarUnico(consulta);
    }

    public void excluirConsulta(int idExc) throws IOException {
        consultaDAO.excluirUnico(idExc);
    }

    public List<Consulta> visualizarConsultas() throws IOException {
        List<Consulta> consultas = consultaDAO.carregarTodos();

        return(consultas);
    }

    public List<Consulta> visualizarConsultasDoPaciente(int idPac) throws IOException {
        List<Consulta> consultasPaciente = consultaDAO.getConsultasByIdPaciente(idPac);

        return(consultasPaciente);
    }

    public List<Consulta> visualizarConsultasDoMedico(int idMed) throws IOException {
        List<Consulta> consultasMedico = consultaDAO.getConsultasByIdMedico(idMed);

        return(consultasMedico);
    }
}