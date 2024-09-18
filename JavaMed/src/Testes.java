import model.*;
// import view.*;
// import controller.*;
import dao.*;

import java.io.*;
import java.util.*;
import java.time.*;

/*
 * TESTES 
 *      Model       [X] 
 *      View        []
 *      Controller  []
 *      DAO         [X]
*/

/********************************** MODEL **********************************

Admin admin = new Admin(1, "987654321", "12312312312", "admin", "admin");
Paciente paciente = new Paciente(1, "984050860", "45645645645", "paciente", "soupaciente123");
Medico medico = new Medico(1, "987654321", "78978978978", "medico", "soumedico123");
Atendente atendente = new Atendente(1, "987654321", "98798798798", "atendente", "souatendente123");

Usuario[] usuarios = {admin, paciente, medico, atendente};

// printar todos os tipos de usuarios instanciados
for ( Usuario usuario : usuarios ) {
    System.out.println(usuario.toString());
}

*/

/********************************** DAO **********************************

Paciente paciente = new Paciente(1, "984050860", "45645645645", "paciente", "soupaciente123");
Medico medico = new Medico(1, "987654321", "78978978978", "medico", "soumedico123");
Atendente atendente = new Atendente(1, "987654321", "98798798798", "atendente", "souatendente123");

// bota a data
Consulta consulta = new Consulta(paciente.getId(), medico.getId(), LocalDateTime.now(), "virose");

PacienteDAO pacienteDAO = new PacienteDAO("C:\\Users\\gabri\\Desktop\\github\\JavaMed\\JavaMed\\data\\pacientes.csv");
MedicoDAO medicoDAO = new MedicoDAO("C:\\Users\\gabri\\Desktop\\github\\JavaMed\\JavaMed\\data\\medicos.csv");
AtendenteDAO atendenteDAO = new AtendenteDAO("C:\\Users\\gabri\\Desktop\\github\\JavaMed\\JavaMed\\data\\atendentes.csv");

ConsultaDAO consultaDAO = new ConsultaDAO("C:\\Users\\gabri\\Desktop\\github\\JavaMed\\JavaMed\\data\\consultas.csv");

// carreagar todos os usuarios
List<Usuario> pacientes = pacienteDAO.carregarTodos();
List<Usuario> medicos = medicoDAO.carregarTodos();
List<Usuario> atendentes = atendenteDAO.carregarTodos();

// carregando as consultas
List<Consulta> consultas = consultaDAO.carregarTodos();

// salvando os usuarios
pacienteDAO.salvarUnico(paciente);
medicoDAO.salvarUnico(medico);
atendenteDAO.salvarUnico(atendente);

// salvando a consulta
consultaDAO.salvarUnico(consulta);
consultas.add(consulta);

// adicionando mais usuarios
// pacientes.add(new Paciente(0, null, null, null, null));
// medicos.add(new Medico(0, null, null, null, null));
// atendentes.add(new Atendente(0, null, null, null, null));

// printando
for ( Usuario usuario : pacientes ) {
    System.out.println(usuario.toString());
}

for ( Usuario usuario : medicos ) {
    System.out.println(usuario.toString());
}

for ( Usuario usuario : atendentes ) {
    System.out.println(usuario.toString());
}

for ( Consulta cons : consultas ) {
    System.out.println(cons.toString());
}

// salvando todos os usuarios
pacienteDAO.salvarTodos(pacientes);
medicoDAO.salvarTodos(medicos);
atendenteDAO.salvarTodos(atendentes);

// salvando a consulta
consultaDAO.salvarTodos(consultas);

System.out.println(medicoDAO.getInfo(0));
*/

/********************************** VIEW **********************************
*/

/********************************** CONTROLLER **********************************
*/

public class Testes {

    public static void main(String[] args) throws IOException {

        Paciente paciente = new Paciente(3, "984050860", "45645645645", "paciente", "soupaciente123");
        Medico medico = new Medico(1, "987654321", "78978978978", "medico", "soumedico123");
        Atendente atendente = new Atendente(1, "987654321", "98798798798", "atendente", "souatendente123");

        // bota a data
        Consulta consulta = new Consulta(paciente.getId(), medico.getId(), LocalDateTime.now(), "virose");

        PacienteDAO pacienteDAO = new PacienteDAO("../data/pacientes.csv");
        MedicoDAO medicoDAO = new MedicoDAO("../data/medicos.csv");
        AtendenteDAO atendenteDAO = new AtendenteDAO("../data/atendentes.csv");

        ConsultaDAO consultaDAO = new ConsultaDAO("../data/consultas.csv");

        // carreagar todos os usuarios
        List<Usuario> pacientes = pacienteDAO.carregarTodos();
        List<Usuario> medicos = medicoDAO.carregarTodos();
        List<Usuario> atendentes = atendenteDAO.carregarTodos();

        // carregando as consultas    
        List<Consulta> consultas = consultaDAO.carregarTodos();

        // salvando os usuarios
        pacienteDAO.salvarUnico(paciente);
        medicoDAO.salvarUnico(medico);
        atendenteDAO.salvarUnico(atendente);

        // salvando a consulta
        consultaDAO.salvarUnico(consulta);
        consultas.add(consulta);

        // adicionando mais usuarios
        // pacientes.add(new Paciente(0, null, null, null, null));
        // medicos.add(new Medico(0, null, null, null, null));
        // atendentes.add(new Atendente(0, null, null, null, null));

        // printando
        for ( Usuario usuario : pacientes ) {
            System.out.println(usuario.toString());
        }

        for ( Usuario usuario : medicos ) {
            System.out.println(usuario.toString());
        }

        for ( Usuario usuario : atendentes ) {
            System.out.println(usuario.toString());
        }

        for ( Consulta cons : consultas ) {
            System.out.println(cons.toString());
        }

        // salvando todos os usuarios
        pacienteDAO.salvarTodos(pacientes);
        medicoDAO.salvarTodos(medicos);
        atendenteDAO.salvarTodos(atendentes);

        // salvando a consulta
        consultaDAO.salvarTodos(consultas);

        System.out.println(medicoDAO.getInfo(0));

    }

}
