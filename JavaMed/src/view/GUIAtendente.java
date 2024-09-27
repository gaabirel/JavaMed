package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.AtendenteController;
import controller.ConsultaController;
import controller.PacienteController;
import dao.ConsultaDAO;
import dao.PacienteDAO;
import model.Consulta;
import model.Paciente;
import model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GUIAtendente extends JFrame implements ActionListener {

    // Criando os objetos a serem usados
    ConsultaController consultaController = new ConsultaController(new ConsultaDAO());

        // Label
    final JLabel label;

        // painel
    final JPanel painel;

        // Botao
    final JButton botaoMarcarConsulta;
    final JButton botaoExcluirPaciente;
    final JButton botaoRegistrarPaciente;
    final JButton botaoVerConsultas;
    final JButton botaoListarPacientes;
    final JButton botaoAlterarPaciente;
    final JButton botaoVoltar;

    // Construtor
    public GUIAtendente(String nomeAtendente) {

        // Configuracoes iniciais
        this.setTitle("Atendente");
        this.setIconImage(new ImageIcon("icones/medical-symbol.png").getImage());
        this.setLayout(new BorderLayout());
        this.setSize(750, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Configurando a label
        label = new JLabel("Seja Bem-vindo, " + nomeAtendente);
        label.setFont(new Font("Arial", Font.BOLD, 42));

        // Configurando os botoes
            // botaoMarcarConsulta
        botaoMarcarConsulta = new JButton();
        botaoMarcarConsulta.setFocusable(false);
        botaoMarcarConsulta.setText("Marcar Consulta");
        botaoMarcarConsulta.setFont(new Font("Arial", Font.BOLD, 34));
        botaoMarcarConsulta.addActionListener(this);

            // botaoExcluirPaciente
        botaoExcluirPaciente = new JButton();
        botaoExcluirPaciente.setFocusable(false);
        botaoExcluirPaciente.setText("Excluir Paciente");
        botaoExcluirPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoExcluirPaciente.addActionListener(this);

            // botaoRegistrarPaciente
        botaoRegistrarPaciente = new JButton();
        botaoRegistrarPaciente.setFocusable(false);
        botaoRegistrarPaciente.setText("Registrar Paciente");
        botaoRegistrarPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoRegistrarPaciente.addActionListener(this);

            // botaoVerConsultas
        botaoVerConsultas = new JButton();
        botaoVerConsultas.setFocusable(false);
        botaoVerConsultas.setText("Ver Consultas");
        botaoVerConsultas.setFont(new Font("Arial", Font.BOLD, 34));
        botaoVerConsultas.addActionListener(this);

            // botaoListarPacientes
        botaoListarPacientes = new JButton();
        botaoListarPacientes.setFocusable(false);
        botaoListarPacientes.setText("Listar Pacientes");
        botaoListarPacientes.setFont(new Font("Arial", Font.BOLD, 34));
        botaoListarPacientes.addActionListener(this);

            // botaoAlterarPaciente
        botaoAlterarPaciente = new JButton();
        botaoAlterarPaciente.setFocusable(false);
        botaoAlterarPaciente.setText("Alterar Paciente");
        botaoAlterarPaciente.setFont(new Font("Arial", Font.BOLD, 34));
        botaoAlterarPaciente.addActionListener(this);

            // botaoVoltar
        botaoVoltar = new JButton();
        botaoVoltar.setFocusable(false);
        botaoVoltar.setText("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoVoltar.setIcon(new ImageIcon("icones/de-volta.png"));
        botaoVoltar.setHorizontalTextPosition(SwingConstants.RIGHT);
        botaoVoltar.setVerticalTextPosition(SwingConstants.CENTER);
        botaoVoltar.setPreferredSize(new Dimension(150, 50));
        botaoVoltar.addActionListener(this);

        // Configurando o painel
        painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1, 20, 20));
        painel.add(botaoMarcarConsulta);
        painel.add(botaoExcluirPaciente);
        painel.add(botaoRegistrarPaciente);
        painel.add(botaoVerConsultas);
        painel.add(botaoListarPacientes);
        painel.add(botaoAlterarPaciente);

        // Adicionando os componentes ao frame
        this.add(label, BorderLayout.NORTH);
        this.add(painel, BorderLayout.CENTER);
        this.add(botaoVoltar, BorderLayout.SOUTH);
        this.setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoMarcarConsulta) {
            // Abre um JOptionPane para obter os detalhes da consulta
            String idConsulta = JOptionPane.showInputDialog("Digite o ID da consulta:");
            try {
                while(consultaController.verificarConsultaId(Integer.parseInt(idConsulta))){
                    idConsulta = JOptionPane.showInputDialog("Digite o ID da consulta:");
                }
            } catch (HeadlessException | NumberFormatException | IOException e1) {
                e1.printStackTrace();
            }
            
            String idPaciente = JOptionPane.showInputDialog("Digite o ID do Paciente:");
            String idMedico = JOptionPane.showInputDialog("Digite o ID do Médico:");
            String data = JOptionPane.showInputDialog("Digite a data da consulta (dd/MM/yyyy):");
            String hora = JOptionPane.showInputDialog("Digite a hora da consulta (HH:mm):");
            String dataHora = data + " " + hora;
            // Validar entrada
            if (idPaciente == null || idMedico == null || data == null || hora == null) {
                JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                return; // Sair do método se a entrada for inválida
            }
            
            // Convertendo data e hora para Date
            LocalDateTime dataConsulta = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            // Criar um novo objeto Consulta
            Consulta newConsulta = new Consulta(Integer.parseInt(idConsulta), Integer.parseInt(idPaciente), Integer.parseInt(idMedico), dataConsulta, null);
            // Salvar a consulta
            try {
                consultaController.salvarConsulta(newConsulta);
                JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
            }
        }

        if (e.getSource() == botaoExcluirPaciente) {

        }

        if (e.getSource() == botaoRegistrarPaciente) {
            try {
                PacienteController pacienteController = new PacienteController(new PacienteDAO());
                // Abre um JOptionPane para obter os detalhes do paciente
                String idPaciente = JOptionPane.showInputDialog("Digite o ID do paciente que deseja adicionar:");
                String telefone = JOptionPane.showInputDialog("Digite o telefone:");
                String cpf = JOptionPane.showInputDialog("Digite o CPF:");
                String nome = JOptionPane.showInputDialog("Digite o nome:");
                String login = JOptionPane.showInputDialog("Digite o login:");
                JPasswordField campoSenha = new JPasswordField();
                int option = JOptionPane.showConfirmDialog(null, campoSenha, "Digite a senha", JOptionPane.OK_CANCEL_OPTION);
                String senha = (option == JOptionPane.OK_OPTION) ? new String(campoSenha.getPassword()) : null;




                // Validar entrada
                if (idPaciente == null || telefone == null || cpf == null || nome == null || login == null || senha == null) {
                    JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                    return; // Sair do método se a entrada for inválida
                }

                int id = Integer.parseInt(idPaciente);

                Paciente paciente = new Paciente(id, telefone, cpf, nome, login, senha);

            
                pacienteController.adicionar(paciente);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso!");
        }

        if (e.getSource() == botaoVerConsultas) {

            try {
                ConsultaController consultaController = new ConsultaController(new ConsultaDAO());               

                java.util.List<Consulta> consultas = consultaController.carregarConsultas();

                    // head da tabela
                    String[] colunasConsultas = {"Consulta", "Paciente", "Médico", "Data/Hora", "Diagnóstico"};
                    DefaultTableModel modeloConsultas = new DefaultTableModel(colunasConsultas, 0);

                    if (consultas != null && !consultas.isEmpty()) {
                        for (Consulta consulta : consultas) {
                            int nomePaciente = consulta.getPaciente();
                            int nomeMedico = consulta.getMedico();
                            String dataHora = consulta.getData().toString();
                            int idConsulta = consulta.getIdConsulta();
                            String diagnostico = consulta.getDiagnostico();

                            modeloConsultas.addRow(new Object[]{idConsulta, nomePaciente, nomeMedico, dataHora, diagnostico});
                        }
                
                        // tabela
                        JTable tabelaConsultas = new JTable(modeloConsultas);
                        JScrollPane painelRolagemConsultas = new JScrollPane(tabelaConsultas);
                        JOptionPane.showMessageDialog(null, painelRolagemConsultas, "Consultas", JOptionPane.INFORMATION_MESSAGE);
                
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Não existem consultas registradas.");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            

        }

        if (e.getSource() == botaoListarPacientes) {
            try {
                PacienteController pacienteController = new PacienteController(new PacienteDAO());               

                java.util.List<Usuario> pacientes = pacienteController.printAll();

                    // head da tabela
                    String[] colunasConsultas = {"Id", "Nome", "CPF", "Telefone"};
                    DefaultTableModel modeloConsultas = new DefaultTableModel(colunasConsultas, 0);

                    if (pacientes != null && !pacientes.isEmpty()) {
                        for (Usuario paciente : pacientes) {
                            int idPaciente = paciente.getId();
                            String nomePaciente = paciente.getNome();
                            String cpf = paciente.getCpf();
                            String telefone = paciente.getTelefone();

                            modeloConsultas.addRow(new Object[]{idPaciente, nomePaciente, cpf, telefone});
                        }
                
                        // tabela
                        JTable tabelaConsultas = new JTable(modeloConsultas);
                        JScrollPane painelRolagemConsultas = new JScrollPane(tabelaConsultas);
                        JOptionPane.showMessageDialog(null, painelRolagemConsultas, "Pacientes", JOptionPane.INFORMATION_MESSAGE);
                
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Não existem pacientes registrados.");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        if (e.getSource() == botaoAlterarPaciente) {
            try {
                PacienteController pacienteController = new PacienteController(new PacienteDAO());
                // Abre um JOptionPane para obter os detalhes do paciente
                String idPaciente = JOptionPane.showInputDialog("Digite o ID do paciente que deseja alterar:");
                String telefone = JOptionPane.showInputDialog("Digite o novo telefone:");
                String cpf = JOptionPane.showInputDialog("Digite o novo CPF:");
                String nome = JOptionPane.showInputDialog("Digite o novo nome:");
                String login = JOptionPane.showInputDialog("Digite o novo login:");
                JPasswordField campoSenha = new JPasswordField();
                int option = JOptionPane.showConfirmDialog(null, campoSenha, "Digite a nova senha", JOptionPane.OK_CANCEL_OPTION);
                String senha = (option == JOptionPane.OK_OPTION) ? new String(campoSenha.getPassword()) : null;

                // Validar entrada
                if (idPaciente == null || telefone == null || cpf == null || nome == null || login == null || senha == null) {
                    JOptionPane.showMessageDialog(null, "Erro! Todos os campos devem ser preenchidos.");
                    return; // Sair do método se a entrada for inválida
                }

                int id = Integer.parseInt(idPaciente);

                Paciente pacienteAtualizado = new Paciente(id, telefone, cpf, nome, login, senha);

            
                pacienteController.atualizar(id, pacienteAtualizado);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso!");
        }

        if (e.getSource() == botaoVoltar) {

            try {
                new GUI();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();

        }

    }

}
