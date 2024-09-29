package view;
import javax.swing.*;
import controller.*;
import dao.AtendenteDAO;
import dao.MedicoDAO;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUIAdm extends GUIAtendente {

    // Criando os componentes a serem usados
        // Botoes adicionais
    JButton botaoIncluirFuncionario;
    JButton botaoExcluirFuncionario;
    JButton botaoAlterarFuncionario;

    // Construtor
    GUIAdm(String nome) {

        // Puxando todas as configuracoes do atendente
        super(nome); // Puxando o construtor do atendente
        this.setTitle("Administrador"); // Adicionando um novo titulo

        // Configurando os botoes novos
            // botaoIncluirFuncionario
        botaoIncluirFuncionario = new JButton();
        botaoIncluirFuncionario.setFocusable(false);
        botaoIncluirFuncionario.setText("Incluir Funcionario");
        botaoIncluirFuncionario.setFont(new Font("Arial", Font.BOLD, 24));
        botaoIncluirFuncionario.addActionListener(this);

            // botaoExcluirFuncionario
        botaoExcluirFuncionario = new JButton();
        botaoExcluirFuncionario.setFocusable(false);
        botaoExcluirFuncionario.setText("Excluir Funcionario");
        botaoExcluirFuncionario.setFont(new Font("Arial", Font.BOLD, 24));
        botaoExcluirFuncionario.addActionListener(this);

            // botaoAlterarFuncionario
        botaoAlterarFuncionario = new JButton();
        botaoAlterarFuncionario.setFocusable(false);
        botaoAlterarFuncionario.setText("Alterar Funcionario");
        botaoAlterarFuncionario.setFont(new Font("Arial", Font.BOLD, 24));
        botaoAlterarFuncionario.addActionListener(this);

        // Configurando o layout do painel
        super.painel.setLayout(new GridLayout(9, 1, 20, 20));

        // Adicionando os botoes extras
        super.painel.add(botaoIncluirFuncionario);
        super.painel.add(botaoExcluirFuncionario);
        super.painel.add(botaoAlterarFuncionario);

    }

    // Funcionalidade dos botoes
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e); // Puxando as funcionalidades dos botoes do atendente

        // Adicionando as funcionalidades dos botoes extras
        if (e.getSource() == botaoIncluirFuncionario){
            try {
                // Perguntar se é Atendente ou Médico
                String[] opcoes = {"Atendente", "Médico"};
                int escolha = JOptionPane.showOptionDialog(null, 
                        "Selecione o tipo de funcionário:",
                        "Tipo de Funcionário",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);
                if (escolha == JOptionPane.CLOSED_OPTION) {
                    return; // Se o usuário cancelar, sair
                }
                String tipoFuncionario = opcoes[escolha];

                // Abre um JOptionPane para obter os detalhes do paciente
                String idFuncionaio = JOptionPane.showInputDialog("Digite o ID do funcionario que deseja adicionar:");
                String telefone = JOptionPane.showInputDialog("Digite o telefone:");
                String cpf = JOptionPane.showInputDialog("Digite o CPF:");
                String nome = JOptionPane.showInputDialog("Digite o nome:");
                String login = JOptionPane.showInputDialog("Digite o login:");
                JPasswordField campoSenha = new JPasswordField();
                int option = JOptionPane.showConfirmDialog(null, campoSenha, "Digite a senha", JOptionPane.OK_CANCEL_OPTION);
                String senha = (option == JOptionPane.OK_OPTION) ? new String(campoSenha.getPassword()) : null;
                
                if (tipoFuncionario.equals("Atendente")) {
                    AdminController adminController = new AdminController(new AtendenteDAO());
                    // Validar entrada
                    if (idFuncionaio == null || telefone == null || cpf == null || nome == null || login == null || senha == null) {
                        JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                        return; // Sair do método se a entrada for inválida
                    }

                    int id = Integer.parseInt(idFuncionaio);

                
                    adminController.incluirAtendente(id, telefone,cpf, nome, login, senha);

                
                } else if (tipoFuncionario.equals("Médico")) {
                    AdminController adminController = new AdminController(new MedicoDAO());

                    // Validar entrada
                    if (idFuncionaio == null || telefone == null || cpf == null || nome == null || login == null || senha == null) {
                        JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                        return; // Sair do método se a entrada for inválida
                    }

                    int id = Integer.parseInt(idFuncionaio);

                
                    adminController.incluirMedico(id, telefone,cpf, nome, login, senha);
                }
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Funcionario adicionado com sucesso!");

        }

        if (e.getSource() == botaoExcluirFuncionario) {
            // Solicitar o ID do funcionário a ser excluído
            String funcionarioIdInput = JOptionPane.showInputDialog("Digite o ID do Funcionário a ser excluído:");
            if (funcionarioIdInput == null) {
                return; // Sair se o usuário cancelar
            }
            
            try {
                int funcionarioId = Integer.parseInt(funcionarioIdInput);
                
                // Perguntar se é Atendente ou Médico
                String[] opcoes = {"Atendente", "Médico"};
                int escolha = JOptionPane.showOptionDialog(null, 
                        "Selecione o tipo de funcionário:",
                        "Tipo de Funcionário",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);
                if (escolha == JOptionPane.CLOSED_OPTION) {
                    return; // Se o usuário cancelar, sair
                }
                
                String tipoFuncionario = opcoes[escolha];
                // Controladores para Atendente e Médico
                AtendenteController atendenteController = new AtendenteController(new AtendenteDAO());
                MedicoController medicoController = new MedicoController(new MedicoDAO());
                // Verificar e excluir Atendente ou Médico com base na escolha
                if (tipoFuncionario.equals("Atendente")) {
                    if (atendenteController.verificarRegistroID(funcionarioId)) {
                        int option = JOptionPane.showConfirmDialog(null,
                                "Tem certeza que deseja excluir o atendente com ID " + funcionarioId + "?",
                                "Confirmação de Exclusão",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            atendenteController.excluir(funcionarioId);
                            JOptionPane.showMessageDialog(null, "Atendente excluído com sucesso!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Atendente não encontrado.");
                    }
                } else if (tipoFuncionario.equals("Médico")) {
                    if (medicoController.verificarRegistroID(funcionarioId)) {
                        int option = JOptionPane.showConfirmDialog(null,
                                "Tem certeza que deseja excluir o médico com ID " + funcionarioId + "?",
                                "Confirmação de Exclusão",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            medicoController.excluir(funcionarioId);
                            JOptionPane.showMessageDialog(null, "Médico excluído com sucesso!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Médico não encontrado.");
                    }
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro! ID do Funcionário deve ser um número.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o funcionário. Tente novamente.");
            }
        }

        if (e.getSource() == botaoAlterarFuncionario){
            try {
                // Perguntar se é Atendente ou Médico
                String[] opcoes = {"Atendente", "Médico"};
                int escolha = JOptionPane.showOptionDialog(null, 
                        "Selecione o tipo de funcionário:",
                        "Tipo de Funcionário",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);
                if (escolha == JOptionPane.CLOSED_OPTION) {
                    return; // Se o usuário cancelar, sair
                }
                String tipoFuncionario = opcoes[escolha];

                // Abre um JOptionPane para obter os detalhes do paciente
                String idFuncionaio = JOptionPane.showInputDialog("Digite o ID do funcionario que deseja alterar:");
                String telefone = JOptionPane.showInputDialog("Digite o telefone:");
                String cpf = JOptionPane.showInputDialog("Digite o CPF:");
                String nome = JOptionPane.showInputDialog("Digite o nome:");
                String login = JOptionPane.showInputDialog("Digite o login:");
                JPasswordField campoSenha = new JPasswordField();
                int option = JOptionPane.showConfirmDialog(null, campoSenha, "Digite a senha", JOptionPane.OK_CANCEL_OPTION);
                String senha = (option == JOptionPane.OK_OPTION) ? new String(campoSenha.getPassword()) : null;
                
                if (tipoFuncionario.equals("Atendente")) {
                    AdminController adminController = new AdminController(new AtendenteDAO());
                    // Validar entrada
                    if (idFuncionaio == null || telefone == null || cpf == null || nome == null || login == null || senha == null) {
                        JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                        return; // Sair do método se a entrada for inválida
                    }

                    int id = Integer.parseInt(idFuncionaio);

                
                    adminController.modificarAtendente(id, id, telefone, cpf, nome, login, senha);

                
                } else if (tipoFuncionario.equals("Médico")) {
                    AdminController adminController = new AdminController(new MedicoDAO());

                    // Validar entrada
                    if (idFuncionaio == null || telefone == null || cpf == null || nome == null || login == null || senha == null) {
                        JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                        return; // Sair do método se a entrada for inválida
                    }

                    int id = Integer.parseInt(idFuncionaio);

                
                    adminController.modificarMedico(id, id, telefone,cpf, nome, login, senha);
                }
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso!");


        }
    
    }
}
