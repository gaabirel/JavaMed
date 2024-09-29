package dao;

/*
 * classe que eh usada para manipular os pacientes no banco de dados
 *
 * made by Gabriel && Marcos :P
*/

public class PacienteDAO extends UsuarioDAO{
    
    public PacienteDAO(String nomeArquivo) {
        super(nomeArquivo);
    }
    public PacienteDAO() {
        super(Config.CAMINHO_PACIENTES);
    }

}
