package model;

import java.time.*;
import java.time.format.*;

/*
 * classe que representa uma consulta
 *
 * made by Gabriel && Marcos :P
*/

public class Consulta {

    private int idPaciente;
    private int idMedico;
    private LocalDateTime data;
    private String diagnostico;

    public Consulta(int idPaciente, int idMedico, LocalDateTime data, String diagnostico) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = data;
        this.diagnostico = diagnostico;
    }

    public Consulta(int idPaciente, int idMedico, String data, String diagnostico) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.diagnostico = diagnostico;
    }

    // getters and setters
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
        return getPaciente() + ";" + 
               getMedico()   + ";" +
               getData()     + ";" +
               getDiagnostico();
    }

}
