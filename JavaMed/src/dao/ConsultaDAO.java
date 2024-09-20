package dao;

import java.io.*;
import java.util.*;

import model.Consulta;

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
     * Método que salva uma único consulta no arquivo
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

        for (Consulta consulta : consultas) {
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

            // idPaciente;idMedico;data;diagnostico
            String[] partes = linha.split(";");
            int idCon = Integer.parseInt(partes[0]);
            int idPac = Integer.parseInt(partes[1]);
            int idMed = Integer.parseInt(partes[2]);
            String data = partes[3];
            String diagnostico = partes[4];

            consultas.add(new Consulta(idCon, idPac, idMed, data, diagnostico));
        }

        bfr.close();
        return consultas;

    }
    
    public void excluirUnico(int idExc) throws IOException {

        // verificando se o usuario existe
        if ( !verificarConsultaId(idExc) ) {
            return;
        }

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        List<Consulta> consultasAtualizadas = new ArrayList<>();

        String linha;

        while ( (linha = bfr.readLine()) != null ) {

            String[] partes = linha.split(";");
            // id;telefone;cpf;nome;senha
            int id = Integer.parseInt(partes[0]);
            int idPaciente = Integer.parseInt(partes[1]);
            int idMedico = Integer.parseInt(partes[2]);
            String data = partes[3];
            String diagnostico = partes[4];

            // caso não seja o usuario a ser excluido
            if ( id != idExc ) {
                // adiciono no novo array
                consultasAtualizadas.add(new Consulta(id, idPaciente, idMedico, data, diagnostico));
            }

        }

        bfr.close();

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        // salvo esse novo array no arquivo
        for ( Consulta consultas : consultasAtualizadas ) {
            bfw.write(consultas.toString() + "\n");
        }

        bfw.close();
    }

    /*
     *  Método que exclui todos os usuarios
    */
    public void excluirTodos() throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        bfw.write("");

        bfw.close();

    }
    
    /*
     * Método que verifica se determinada consulta já está no banco com base nos IDs do paciente e do médico
    */
    public Boolean verificarConsultaId(int idConsulta) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {
            String[] partes = linha.split(";");
            int id = Integer.parseInt(partes[0]);
            if (id == idConsulta) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;

    }

}
