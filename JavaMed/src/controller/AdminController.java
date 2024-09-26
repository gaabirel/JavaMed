package controller;

import java.io.IOException;

import dao.AtendenteDAO;
import dao.ConsultaDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.UsuarioDAO;
import model.Atendente;
import model.Medico;

public class AdminController extends AtendenteController {
    AtendenteDAO atendenteDAO;
    
    public AdminController(UsuarioDAO usuarioDAO, ConsultaDAO consultaDAO, PacienteDAO pacienteDAO, MedicoDAO medicoDAO, AtendenteDAO atendenteDAO) throws IOException {
        super(usuarioDAO, consultaDAO, pacienteDAO, medicoDAO);

        this.atendenteDAO = atendenteDAO;
    }

    @Override
    // login padrao do admin: admin admin
    public Boolean login(String login, String senha) {
        return ( login.equals("admin") && senha.equals("admin") );
    }

    /*  FUNÇOES A SEREM IMPLEMENTADAS
     *      tudo que um atendente faz +
     *      incluir, modificar e excluir os médicos e os atendentes
     * 
    */

    public void incluirMedico(int id, String telefone, String cpf, String nome, String login, String senha) throws IOException {
        Medico medico = new Medico(id, telefone, cpf, nome, login, senha);

        medicoDAO.salvarUnico(medico);
    }

    public void modificarMedico(int idAntigo, int idNovo, String telefone, String cpf, String nome, String login, String senha) throws IOException {
        Medico medico = new Medico(idNovo, telefone, cpf, nome, login, senha);

        medicoDAO.modificar(idAntigo, medico);
    }

    public void excluirMedico(int idExc) throws IOException {
        medicoDAO.excluirUnico(idExc);
    }

    public void incluirAtendente(int id, String telefone, String cpf, String nome, String login, String senha) throws IOException {
        Atendente atendente = new Atendente(id, telefone, cpf, nome, login, senha);

        atendenteDAO.salvarUnico(atendente);
    }

    public void modificarAtendente(int idAntigo, int idNovo, String telefone, String cpf, String nome, String login, String senha) throws IOException {
        Atendente atendente = new Atendente(idNovo, telefone, cpf, nome, login, senha);

        atendenteDAO.modificar(idAntigo, atendente);
    }

    public void excluirAtendente(int idExc) throws IOException {
        atendenteDAO.excluirUnico(idExc);
    }

}
