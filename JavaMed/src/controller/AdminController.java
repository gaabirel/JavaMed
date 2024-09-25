package controller;

import java.io.IOException;

import dao.UsuarioDAO;

public class AdminController extends AtendenteController {
    
    public AdminController(UsuarioDAO usuarioDAO) throws IOException {
        super(usuarioDAO);
    }

    @Override
    // login padrao do admin: admin admin
    public Boolean login(String login, String senha) {
        return ( login.equals("admin") && senha.equals("admin") );
    }

    /*  FUNÇOES A SEREM IMPLEMENTADAS
     *      tudo que um atendente faz +
     *      incluir, modificar e excluir os médicos e os atendentes
     * 
    */

}
