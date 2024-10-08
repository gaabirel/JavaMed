package model;

/*
 * super classe abstrata que representa um Usuario
 * 
 * made by Gabriel && Marcos :P
*/

public abstract class Usuario {

    private int id;
    private String telefone, cpf, nome, login, senha;

    public Usuario(int id, String telefone, String cpf, String nome, String login, String senha){
        this.id = id;
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        //id;telefone;cpf;nome;senha
        return getId()       + ";" +
               getTelefone() + ";" +
               getCpf()      + ";" +
               getNome()     + ";" +
               getLogin()    + ";" +
               getSenha();
    }

}
