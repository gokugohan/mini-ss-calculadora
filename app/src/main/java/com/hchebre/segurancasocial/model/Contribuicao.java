package com.hchebre.segurancasocial.model;

import androidx.annotation.NonNull;

public class Contribuicao {

    private static final Double TAXA_DE_SEGURANCA_SOCIAL_TRABALHADOR = 0.04;
    private static final Double TAXA_DE_SEGURANCA_SOCIAL_EMPREGADOR = 0.06;

    private Double salarioMensal;
    private int mes,ano;

    public Contribuicao(String salarioMensal, String mes, String ano) {
        this.setSalarioMensal(salarioMensal);
        this.setAno(ano);
        this.setMes(mes);
    }


    private Double getSalarioMensal() {
        return salarioMensal;
    }

    private void setSalarioMensal(Double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    private void setSalarioMensal(String salarioMensal) {
        try {
            this.setSalarioMensal(Double.parseDouble(salarioMensal));
        }catch (Exception e){
            this.setSalarioMensal(0.0);
        }
    }

    private void setMes(int mes) {
        this.mes = mes;
    }

    private void setMes(String mes) {
        try {
            this.setMes(Integer.parseInt(mes));
        }catch (Exception e){
            this.setMes(0);
        }
    }

    private void setAno(String ano) {
        try{
            this.ano = Integer.parseInt(ano);
        }catch(Exception e){
            this.ano = 0;
        }
    }


    private   Double getContribuicaoMensalTrabalhador(){
        return this.getSalarioMensal() * TAXA_DE_SEGURANCA_SOCIAL_TRABALHADOR;
    }

    private  Double getContribuicaoMensalEmpregador(){
        return this.getSalarioMensal() * TAXA_DE_SEGURANCA_SOCIAL_EMPREGADOR;
    }

    private Double getTotalContribuicaoMensal(){
        return this.getContribuicaoMensalTrabalhador() + this.getContribuicaoMensalEmpregador();
    }

    private Double getTotalContribuicao(){
        return this.getTotalContribuicaoMensal() * this.getTotalMes();
    }


    private int getTotalMes(){
        return (this.ano *12) + mes;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Salário Mensal: ").append(this.getSalarioMensal()).append("\n")
                .append("Valor Nebe Trabalhador Contribui Mensalmente: ")
                .append(this.getContribuicaoMensalTrabalhador()).append("\n")
                .append("Valor Nebe Empregador Contribui ba Trabalhador Mensalmente: ")
                .append(this.getContribuicaoMensalEmpregador()).append("\n")
                .append("Total Valor Mensal Nebe Contribui: ").append(this.getTotalContribuicaoMensal()).append("\n")
                .append("Total Tinan Serbisu: ").append(ano).append("\n")
                .append("Total Fulan Serbisu: ").append(mes).append(" mes(es)").append("\n")
                .append("Total Tinan e Fulan Serbisu: ").append(this.getTotalMes()).append(" mes(es)").append("\n")
                .append("Total Valor Contribui Até Agora: ").append(this.getTotalContribuicao());

        return sb.toString();
    }
}
