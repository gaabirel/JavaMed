package dao;

import java.io.*;
import java.util.*;

import model.*;

/*
 * classe UsuarioDAO
 *    usada para salvar e carregar os usuarios no banco de dados (CSV)
 *    será utilizada pelo controller
 *
 * made by Gabriel && Marcos :P
*/

public class UsuarioDAO {

    private String nomeArquivo;
    
    UsuarioDAO(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    // getters and setters
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    public String getNomeArquivo() {
        return nomeArquivo;
    }
    
    /*
     * Método que salva um único usuário no arquivo
    */
    public void salvarUnico(Usuario usuario) throws IOException {

        // verificando se o usuario ja esta cadastrado no banco
        if ( verificarRegistroID(usuario.getId()) ) {
            return;
        }

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo, true));

        bfw.write(usuario.toString() + "\n");

        bfw.close();

    }
    
    /*
     * Método que salva todos os usuários no arquivo
    */
    public void salvarTodos(List<Usuario> usuarios) throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        for ( Usuario usuario : usuarios ) {
            // verificando se o usuario nao esta cadastrado
            if ( !verificarRegistroID(usuario.getId()) ) {
                bfw.write(usuario.toString() + "\n");
            }
        }

        bfw.close();
    }

    /*
     * Método que carrega todos os usuários do arquivo
    */
    public List<Usuario> carregarTodos() throws IOException {

        List<Usuario> usuarios = new ArrayList<>();
        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine() ) != null ) {

            String[] partes = linha.split(";");

            // id;telefone;cpf;nome;senha
            int id = Integer.parseInt(partes[0]);
            String tel = partes[1];
            String cpf = partes[2];
            String nome = partes[3];
            String senha = partes[4];

            Usuario usuario;

            // verificando qual o tipo de usuario (paciente, medico ou atendente)
            if ( nomeArquivo.contains("pacientes") ) {
               usuario = new Paciente(id, tel, cpf, nome, senha);
            } else if ( nomeArquivo.contains("medicos")) {
               usuario = new Medico(id, tel, cpf, nome, senha);
            } else {
                usuario = new Atendente(id, tel, cpf, nome, senha);
            }
            
            usuarios.add(usuario);

        }

        bfr.close();
        return usuarios;

    }

    /*
     * Método que verifica se determinado Usuário ja esta no banco
    */
    public Boolean verificarRegistroID(int idBusca) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));
        
        String linha;

        while ( (linha = bfr.readLine()) != null ) {
            String[] partes = linha.split(";");

            int id = Integer.parseInt(partes[0]);

            if ( idBusca == id ) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;

    }
    
    /*
     * Método que retorna as infos do usuário
    */ 
    public String getInfo(int idBusca) throws IOException {
        
        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ( (linha = bfr.readLine()) != null ) {
            String[] partes = linha.split(";");
            int id = Integer.parseInt(partes[0]);

            if ( idBusca == id ) {
                bfr.close();
                return linha;
            }
        }

        bfr.close();
        return "null";

    }
    
}
