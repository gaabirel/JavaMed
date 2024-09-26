package dao;

import java.io.*;
import java.util.*;

import model.Consulta;
import model.Usuario;

/*
 * classe que eh usada para manipular as consultas no banco de dados
 *
 * made by Gabriel && Marcos :P
*/

public class ConsultaDAO {

    private String nomeArquivo;

    public ConsultaDAO(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    public ConsultaDAO() {
        this.nomeArquivo = "../data/consultas.csv";
    }

    // getters and setters
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    /*
     * Método que salva uma única consulta no arquivo
    */
    public void salvarUnico(Consulta consulta) throws IOException{

        // verificando se a consulta ja existe
        if ( verificarConsultaId(consulta.getIdConsulta()) ) {
            return;
        }

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo, true));

        bfw.write(consulta.toString() + "\n");

        bfw.close();

    }

    /*
     * Método que salva todas as consultas no arquivo, recebendo um array de consultas
    */
    public void salvarTodos(List<Consulta> consultas) throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo, true));

        for ( Consulta consulta : consultas ) {
            // verificando se a consulta nao existe
            if ( !verificarConsultaId(consulta.getIdConsulta()) ){
                bfw.write(consulta.toString() + "\n");
            }
        }

        bfw.close();

    }

    /*
     * Método que carrega todas as consultas do arquivo, retornando um array de consultas
    */
    public List<Consulta> carregarTodos() throws IOException{

        List<Consulta> consultas = new ArrayList<>();

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));
        String linha;

        while( ( linha = bfr.readLine() ) != null ) {

            consultas.add(stringToConsulta(linha));

        }

        bfr.close();
        return consultas;

    }

    /*
     * Método que carrega uma consulta, recebendo o ID da consulta
    */
    public Consulta carregaConsultaById(int idBus) throws IOException {

        // verificando se a consulta existe
        if ( !verificarConsultaId(idBus) ) return null;

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {

            Consulta consulta = stringToConsulta(linha);

            if ( consulta.getIdConsulta() == idBus ) {
                bfr.close();
                return consulta;
            }

        }

        bfr.close();
        return null;

    }

    /*
     * Método que retorna as consultas com base no ID do paciente
    */
    public List<Consulta> getConsultasByIdPaciente(int idPac) throws IOException {

        // verificando se existe alguma ocorrencia do ID do paciente, caso nao exista retorna null
        if ( !verificaPacienteId(idPac) ) return null;

        List<Consulta> consultas = new ArrayList<>();
        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {
    
            Consulta consulta = stringToConsulta(linha);

            if ( consulta.getPaciente() == idPac ) {
                consultas.add(consulta);
            }

        }

        bfr.close();
        return consultas;
    }

    /*
     * Método que retorna as consultas com base no ID do medico, caso nao exista retorna null
    */
    public List<Consulta> getConsultasByIdMedico(int idMed) throws IOException {

        // verificando se existe alguma ocorrencia do ID do medico
        if ( !verificaMedicoId(idMed) ) return null;

        List<Consulta> consultas = new ArrayList<>();
        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {
    
            Consulta consulta = stringToConsulta(linha);

            if ( consulta.getMedico() == idMed ) {
                consultas.add(consulta);
            }

        }

        bfr.close();
        return consultas;

    }

    /*
     * Método que exclui uma única consulta, recebendo um ID
    */
    public void excluirUnico(int idExc) throws IOException {

        // verificando se o usuario existe
        if ( !verificarConsultaId(idExc) ) {
            return;
        }

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        List<Consulta> consultasAtualizadas = new ArrayList<>();

        String linha;

        while ( (linha = bfr.readLine()) != null ) {

            Consulta consulta = stringToConsulta(linha);

            // caso não seja o usuario a ser excluido, adiciona no novo array
            if ( consulta.getIdConsulta() != idExc ) {
                consultasAtualizadas.add(consulta);
            }

        }

        bfr.close();

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        // salvar esse novo array no arquivo
        for ( Consulta consultas : consultasAtualizadas ) {
            bfw.write(consultas.toString() + "\n");
        }

        bfw.close();
    }

    /*
     *  Método que exclui todas as consultas
    */
    public void excluirTodos() throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        bfw.write("");

        bfw.close();

    }
    
    /*
     * Método que modifica uma consulta no arquivo, recebendo o ID
    */
    public void modificar(int idCon, Consulta novaConsulta) throws IOException {

        // verificando se a consulta esta no banco, baseado no ID da consulta
        if (!verificarConsultaId(idCon)) {
            return;
        }

        // verificando se as consultas têm o mesmo ID
        if (idCon != novaConsulta.getIdConsulta()) {
            return;
        }

        // excluindo a antiga e salvando a nova
        excluirUnico(idCon);
        salvarUnico(novaConsulta);

    }

    /*
     * Método que verifica se determinada consulta já está no banco com base no ID da consulta
    */
    public Boolean verificarConsultaId(int idConsulta) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {

            Consulta consulta = stringToConsulta(linha);

            if (consulta.getIdConsulta() == idConsulta) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;

    }

    /*
     * Método que verifica se o paciente tem alguma consulta, recebendo o ID do paciente
    */
    public Boolean verificaPacienteId(int idPac) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {

            Consulta consulta = stringToConsulta(linha);

            if (consulta.getPaciente() == idPac) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;
    }
    /*
     * Método que verifica se o medico tem alguma consulta, recebendo o ID do medico
    */
    public Boolean verificaMedicoId(int idMed) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {

            Consulta consulta = stringToConsulta(linha);

            if (consulta.getMedico() == idMed) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;
    }

    /*
     * Método que recebe uma string padronizada e retorna uma consulta
    */
    public Consulta stringToConsulta(String consulta) {

        // idConsulta;idPaciente;idMedico;data;diagnostico 
        String[] partes = consulta.split(";");
        int idCon = Integer.parseInt(partes[0]);
        int idPac = Integer.parseInt(partes[1]);
        int idMed = Integer.parseInt(partes[2]);
        String data = partes[3];
        String diag = partes[4];

        return new Consulta(idCon, idPac, idMed, data, diag);

    }

}
