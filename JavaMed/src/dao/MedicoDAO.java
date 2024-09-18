package dao;

/*
 * classe que eh usada para manipular os medicos no banco de dados
 *
 * made by Gabriel && Marcos :P
*/

public class MedicoDAO extends UsuarioDAO{

    public MedicoDAO(String nomeArquivo) {
        super(nomeArquivo);
    }
    public MedicoDAO() {
        super("data/medicos.csv");
    }

}
