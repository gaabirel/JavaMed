package controller;

import java.io.IOException;
import java.util.*;

import dao.UsuarioDAO;
import model.Usuario;

/*
 * Classe UsuarioController
 *      classe responsável pela comunicação entre a view e o DAO
 * 
 * made by gabirel (por enquanto)
*/

public class UsuarioController {
 
    UsuarioDAO usuarioDAO;
    List<Usuario> usuarios;
    
    public UsuarioController(UsuarioDAO usuarioDAO) throws IOException {
        this.usuarioDAO = usuarioDAO;
        this.usuarios = usuarioDAO.carregarTodos();
    }

    /*
     * Método para fazer login, recebendo o login e a senha
     *  retorna true se os parametros estiverem corretos e false caso contrario
    */
    public Boolean login(String login, String senha) {

        // percorrerndo a lista de usuarios
        for ( Usuario users : usuarios ) {
            // verificando o login e senha do usuario
            if ( users.getLogin().equals(login) && users.getSenha().equals(senha)) {
                return true;
            }
        }
        // após percorrido o banco e nao ter encontrado uma ocorrencia do login e senha
        return false;

    }

    /*
     * Método para adicionar um usuário no banco, recebendo um usuario
    */
    public void adicionar(Usuario usuario) throws IOException {
        usuarioDAO.salvarUnico(usuario);
    }

    /*
     * Método para excluir um usuário, recebendo um id, ou um usuario
    */
    public void excluir(int id) throws IOException {
        usuarioDAO.excluirUnico(id);
    }
    public void excluir(Usuario usuario) throws IOException {
        usuarioDAO.excluirUnico(usuario.getId());
    }

    /*
     * Método para printar todos os usuarios do banco
    */
    public List<Usuario> printAll() throws IOException {
        return usuarios;
    }

    public Boolean verificarRegistroID(int idPac)throws IOException {
        return usuarioDAO.verificarRegistroID(idPac);

    }
    public int getInfoLogin(String Login) throws IOException {
        return usuarioDAO.getInfoLogin(Login);
    }

    public void atualizar(int id, Usuario usuarioNovo) throws IOException {
        usuarioDAO.modificar(id, usuarioNovo);
    }
}
