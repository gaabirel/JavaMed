package model;

import java.time.*;
import java.time.format.*;

/*
 * classe que representa uma consulta
 *
 * made by Gabriel && Marcos :P
*/

public class Consulta {

    private int idConsulta;
    private int idPaciente;
    private int idMedico;
    private LocalDateTime data;
    private String diagnostico;

    public Consulta(int idConsulta, int idPaciente, int idMedico, LocalDateTime data, String diagnostico) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = data;
        this.diagnostico = diagnostico;
    }

    public Consulta(int idConsulta, int idPaciente, int idMedico, String data, String diagnostico) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.diagnostico = diagnostico;
    }

    // getters and setters
    public int getIdConsulta() {
        return idConsulta;
    }
    
    public void setIdConsulta(int idConsulta){
        this.idConsulta = idConsulta;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getPaciente() {
        return idPaciente;
    }

    public void setPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getMedico() {
        return idMedico;
    }

    public void setMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return data.format(formatter);
    }

    @Override
    // idPaciente;idMedico;data;diagnostico
    public String toString() {
        return getIdConsulta() + ";" +
               getPaciente()   + ";" + 
               getMedico()     + ";" +
               getData()       + ";" +
               getDiagnostico();
    }

}
