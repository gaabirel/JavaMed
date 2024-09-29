package controller;

import dao.ConsultaDAO;
import model.Consulta;
import java.io.IOException;
import java.util.List;

public class ConsultaController {

    private ConsultaDAO consultaDAO;

    public ConsultaController(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    // Método para salvar uma consulta
    public void salvarConsulta(Consulta consulta) throws IOException {
        consultaDAO.salvarUnico(consulta);
    }

    // Método para salvar múltiplas consultas
    public void salvarConsultas(List<Consulta> consultas) throws IOException {
        consultaDAO.salvarTodos(consultas);
    }

    // Método para carregar todas as consultas
    public List<Consulta> carregarConsultas() throws IOException {
        return consultaDAO.carregarTodos();
    }

    // Método para carregar uma consulta por ID
    public Consulta carregarConsultaById(int idConsulta) throws IOException {
        return consultaDAO.carregaConsultaById(idConsulta);
    }

    // Método para obter consultas por ID do paciente
    public List<Consulta> getConsultasByIdPaciente(int idPaciente) throws IOException {
        return consultaDAO.getConsultasByIdPaciente(idPaciente);
    }

    // Método para obter consultas por ID do médico
    public List<Consulta> getConsultasByIdMedico(int idMedico) throws IOException {
        return consultaDAO.getConsultasByIdMedico(idMedico);
    }

    // Método para excluir uma consulta
    public void excluirConsulta(int idConsulta) throws IOException {
        consultaDAO.excluirUnico(idConsulta);
    }

    // Método para excluir todas as consultas
    public void excluirTodasConsultas() throws IOException {
        consultaDAO.excluirTodos();
    }

    // Método para verificar se uma consulta já existe
    public boolean verificarConsultaId(int idConsulta) throws IOException {
        return consultaDAO.verificarConsultaId(idConsulta);
    }

    // Método para verificar se um paciente tem consultas
    public boolean verificaPacienteId(int idPaciente) throws IOException {
        return consultaDAO.verificaPacienteId(idPaciente);
    }

    // Método para verificar se um médico tem consultas
    public boolean verificaMedicoId(int idMedico) throws IOException {
        return consultaDAO.verificaMedicoId(idMedico);
    }

}