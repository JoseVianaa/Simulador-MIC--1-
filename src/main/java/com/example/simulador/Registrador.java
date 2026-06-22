package com.example.simulador;

public class Registrador {
    private String nome;
    private short valor;

    public Registrador(String nome) {
        this.nome=nome;
        this.valor = 0;
    }

    public short getValor() {
        return valor;
    }

    public String getNome(){
        return nome;
    }

    public void setValor(short valor) {
        this.valor = valor;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void printRegistrador(){
        System.out.println(nome + " = " + valor);
    }
}
