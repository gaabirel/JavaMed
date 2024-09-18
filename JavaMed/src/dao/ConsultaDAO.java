package dao;

import java.io.*;
import java.util.*;

import model.Consulta;

/*
 * classe que eh usada para manipular as consultas no banco de dados
 *
 * made by Gabriel && Marcos :P
*/

public class ConsultaDAO{

    private String nomeArquivo;
    
    public ConsultaDAO(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    public ConsultaDAO() {
        this.nomeArquivo = "data/consultas.csv";
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
        if ( verificarConsultaId(consulta.getPaciente(), consulta.getMedico()) ) {
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
            if ( !verificarConsultaId(consulta.getPaciente(), consulta.getMedico()) ){
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
            int idPac = Integer.parseInt(partes[0]);
            int idMed = Integer.parseInt(partes[1]);
            String data = partes[2];
            String diagnostico = partes[3];

            consultas.add(new Consulta(idPac, idMed, data, diagnostico));
        }
        
        bfr.close();
        return consultas;
        
    }

    /*
     * Método que verifica se determinada consulta já está no banco com base nos IDs do paciente e do médico
    */
    public Boolean verificarConsultaId(int idPacBusca, int idMedBusca) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {
            String[] partes = linha.split(";");
            int idPac = Integer.parseInt(partes[0]);
            int idMed = Integer.parseInt(partes[1]);
            if ( idPac == idPacBusca && idMed == idMedBusca ) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;

    }

}
