package com.universidade.entity;

import java.util.List;

public class Pessoa {
    private String cpf;
    private String nome;
    private Integer idade;
    private String endereco;
    private List<String> telefone; // JSON no banco, List em Java
    private String estadoMoradia;
    private String bairroMoradia;

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<String> getTelefone() { return telefone; }
    public void setTelefone(List<String> telefone) { this.telefone = telefone; }

    public String getEstadoMoradia() { return estadoMoradia; }
    public void setEstadoMoradia(String estadoMoradia) { this.estadoMoradia = estadoMoradia; }

    public String getBairroMoradia() { return bairroMoradia; }
    public void setBairroMoradia(String bairroMoradia) { this.bairroMoradia = bairroMoradia; }
}