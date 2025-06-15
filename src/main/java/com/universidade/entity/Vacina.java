package com.universidade.entity;

import java.util.Date;

public class Vacina {
    private String codVacina;
    private String nome;
    private String lote;
    private int dosesRecomendadas;
    private int intervaloDoses;
    private String fabricante;
    private String registroAnvisa;
    private String tipo;
    private String observacoes;
    private Date validade;

    // Getters e Setters
    public String getCodVacina() { return codVacina; }
    public void setCodVacina(String codVacina) { this.codVacina = codVacina; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public int getDosesRecomendadas() { return dosesRecomendadas; }
    public void setDosesRecomendadas(int dosesRecomendadas) { this.dosesRecomendadas = dosesRecomendadas; }

    public int getIntervaloDoses() { return intervaloDoses; }
    public void setIntervaloDoses(int intervaloDoses) { this.intervaloDoses = intervaloDoses; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public String getRegistroAnvisa() { return registroAnvisa; }
    public void setRegistroAnvisa(String registroAnvisa) { this.registroAnvisa = registroAnvisa; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Date getValidade() { return validade; }
    public void setValidade(Date validade) { this.validade = validade; }
}