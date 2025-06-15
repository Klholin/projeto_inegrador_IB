package com.universidade.entity;

import java.util.Date;

public class PessoaTomouVacina {
    private String pessoaCpf;
    private String vacinaCodVacina;
    private int dose;
    private Date data;
    private String estadoVacina;
    private String bairroVacina;
    private String unidade;

    // Getters e Setters
    public String getPessoaCpf() { return pessoaCpf; }
    public void setPessoaCpf(String pessoaCpf) { this.pessoaCpf = pessoaCpf; }

    public String getVacinaCodVacina() { return vacinaCodVacina; }
    public void setVacinaCodVacina(String vacinaCodVacina) { this.vacinaCodVacina = vacinaCodVacina; }

    public int getDose() { return dose; }
    public void setDose(int dose) { this.dose = dose; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getEstadoVacina() { return estadoVacina; }
    public void setEstadoVacina(String estadoVacina) { this.estadoVacina = estadoVacina; }

    public String getBairroVacina() { return bairroVacina; }
    public void setBairroVacina(String bairroVacina) { this.bairroVacina = bairroVacina; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
}