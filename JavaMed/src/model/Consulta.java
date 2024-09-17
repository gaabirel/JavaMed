package model;

import java.time.*;

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
        this.diagnostico = diagnostico;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = data;
    }

    // setters and getters
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

    public LocalDateTime getData() {
        return data;
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
