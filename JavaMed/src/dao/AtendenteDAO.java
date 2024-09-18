package dao;

/*
 * classe que eh usada para manipular os atendentes no banco de dados
 *
 * made by Gabriel && Marcos :P
*/

public class AtendenteDAO extends UsuarioDAO{

    public AtendenteDAO(String nomeArquivo) {
        super(nomeArquivo);
    }

    public AtendenteDAO() {
        super("data/atendentes.csv");
    }

}
