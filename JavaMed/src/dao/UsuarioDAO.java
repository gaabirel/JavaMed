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
        if (verificarRegistroID(usuario.getId())) {
            System.out.println("Usuário já cadastrado");
            return;
        }

        // verificando se o login ja esta cadastrado no banco
        if (verificarRegistroLogin(usuario.getLogin())) {
            System.out.println("Login ja cadastrado salavr unico");
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

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo, true));

        for (Usuario usuario : usuarios) {
            // verificando se o usuario nao esta cadastrado
            if (!verificarRegistroID(usuario.getId()) || !verificarRegistroLogin(usuario.getLogin())) {
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

        while ((linha = bfr.readLine()) != null) {

            String[] partes = linha.split(";");

            // id;telefone;cpf;nome;senha
            int id = Integer.parseInt(partes[0]);
            String tel = partes[1];
            String cpf = partes[2];
            String nome = partes[3];
            String login = partes[4];
            String senha = partes[5];

            Usuario usuario;

            // verificando qual o tipo de usuario (paciente, medico ou atendente)
            if (nomeArquivo.contains("pacientes")) {
                usuario = new Paciente(id, tel, cpf, nome, login, senha);
            } else if (nomeArquivo.contains("medicos")) {
                usuario = new Medico(id, tel, cpf, nome, login, senha);
            } else {
                usuario = new Atendente(id, tel, cpf, nome, login, senha);
            }

            usuarios.add(usuario);

        }

        bfr.close();
        return usuarios;

    }

    /*
     * Método que exclui um único usuario do arquivo, recebendo o ID
     */
    public void excluirUnico(int idExc) throws IOException {

        // verificando se o usuario existe
        if (!verificarRegistroID(idExc)) {
            return;
        }
        
        // verificando se o login esta cadastrado no banco
        if (!verificarRegistroLogin(getInfo(idExc).split(";")[4])) {
            return;
        }

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        List<Usuario> usuariosAtualizados = new ArrayList<>();

        String linha;

        while ((linha = bfr.readLine()) != null) {

            String[] partes = linha.split(";");
            // id;telefone;cpf;nome;senha
            int id = Integer.parseInt(partes[0]);
            String tel = partes[1];
            String cpf = partes[2];
            String nome = partes[3];
            String login = partes[4];
            String senha = partes[5];

            Usuario usuario;

            // caso não seja o usuario a ser excluido
            if (id != idExc) {
                if (nomeArquivo.contains("pacientes")) {
                    usuario = new Paciente(id, tel, cpf, nome, login, senha);
                } else if (nomeArquivo.contains("medicos")) {
                    usuario = new Medico(id, tel, cpf, nome, login, senha);
                } else {
                    usuario = new Atendente(id, tel, cpf, nome, login, senha);
                }
                // adiciono no novo array
                usuariosAtualizados.add(usuario);
            }

        }

        bfr.close();

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        // salvo esse novo array no arquivo
        for (Usuario usuarios : usuariosAtualizados) {
            bfw.write(usuarios.toString() + "\n");
        }

        bfw.close();
    }

    /*
     * Método que exclui todos os usuarios
     */
    public void excluirTodos() throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(nomeArquivo));

        bfw.write("");

        bfw.close();

    }

    /*
     * Método que modifica um usuario no arquivo, recebendo o ID
     */
    public void modificar(int id, Usuario usuarioNovo) throws IOException {

        // verificando se o usuario esta no banco
        if (!verificarRegistroID(id)) {
            return;
        }

        // verificando se o login ja esta cadastrado no banco
        if (verificarRegistroLogin(usuarioNovo.getLogin())) {
            return;
        }

        // verificando se os usuarios têm o msm ID
        if (id != usuarioNovo.getId()) {
            return;
        }

        // excluindo o antigo e salvando o novo

        excluirUnico(id);
        salvarUnico(usuarioNovo);

    }

    /*
     * Método que verifica se determinado Usuário ja esta no banco, recebendo o ID
     */
    public Boolean verificarRegistroID(int idBusca) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ((linha = bfr.readLine()) != null) {
            String[] partes = linha.split(";");

            int id = Integer.parseInt(partes[0]);

            if (idBusca == id) {
                bfr.close();
                return true;
            }
        }

        bfr.close();
        return false;

    }

    /*
     * Método que verifica se determinado login ja esta no banco, recebendo o Login
     */
    public Boolean verificarRegistroLogin(String loginBusca) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(nomeArquivo));

        String linha;

        while ((linha = bfr.readLine()) != null) {
            String[] partes = linha.split(";");

            String login = partes[4];
        
            if (loginBusca.equals(login)) {
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

        while ((linha = bfr.readLine()) != null) {
            String[] partes = linha.split(";");
            int id = Integer.parseInt(partes[0]);

            if (idBusca == id) {
                bfr.close();
                return linha;
            }
        }

        bfr.close();
        return "null";

    }

}
