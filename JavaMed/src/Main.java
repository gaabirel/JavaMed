import dao.*;
import model.*;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        UsuarioDAO medicoDAO = new MedicoDAO("JavaMed/data/medicos.csv");
        UsuarioDAO pacienteDAO = new PacienteDAO("JavaMed/data/pacientes.csv");
        UsuarioDAO atendenteDAO = new AtendenteDAO("JavaMed/data/atendentes.csv");
        ConsultaDAO consultaDAO = new ConsultaDAO("JavaMed/data/consultas.csv");

        List<Usuario> medicos = medicoDAO.carregarTodos();
        List<Usuario> pacientes = pacienteDAO.carregarTodos();
        List<Consulta> consultas = consultaDAO.carregarTodos();
        List<Usuario> atendenetes = atendenteDAO.carregarTodos();

        // printando os dados coletados
        System.out.println("\nLISTA DE MEDICOS: ");
        for ( Usuario medico : medicos ) {
            System.out.println(medico.toString());
        }

        System.out.println("\nLISTA DE PACIENTES: ");
        for ( Usuario paciente : pacientes ) {
            System.out.println(paciente.toString());
        }

        System.out.println("\nLISTA DE ATENDENTES: ");
        for ( Usuario atendente : atendenetes ) {
            System.out.println(atendente.toString());
        }

        System.out.println("\nLISTA DE CONSULTAS");
        for ( Consulta consulta : consultas ) {
            System.out.println(consulta.toString());
        }
        
    }

}
