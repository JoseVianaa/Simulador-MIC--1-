package com.example.simulador;

import java.util.ArrayList;

public class Memoria {
    private short[] memoriaMP;
    private int indicador;
    private static final int tamMP=4096;

    public Memoria(){
        this.memoriaMP=new short[tamMP];
        this.indicador=0;
    }

    public boolean validarAddress(int endereco){
        return endereco < 4096 && endereco > -1;
    }

    public short getAddress (int endereco){
        if (validarAddress(endereco)){
            return (memoriaMP[endereco]);
        }
        else{
            throw new IndexOutOfBoundsException("Erro em GetAddress! Endereço inválido.");
        }
    }

    public void setAddress (int endereco, short conteudo){
        if (validarAddress(endereco)){
            memoriaMP[endereco]=conteudo;
        }
        else{
            throw new IndexOutOfBoundsException("Erro em SetAddress! Endereço inválido.");
        }
    }

    public void carregarArquivo(ArrayList<Short> parsedLines){
        for (int i=0; i<parsedLines.size(); i++){
            memoriaMP[i] = parsedLines.get(i);
        }
    }

    public void printMemoriaDecimal(){
        for (int i=0; i<tamMP; i++){
            System.out.println("Endereço de memória: " + i + "; valor: " + memoriaMP[i]);
        }
    }

    public void printMemoriaBin(){
        System.out.println("*******************************************************");
        System.out.println("MP Completa em binário:");
        System.out.println("*******************************************************");
        System.out.println("\n");
        for (int i=0; i<tamMP; i++){
            // & é um operador AND bit a bit; 0xFFFF é hexa para 1111111111111111; formata para uma palavra de 16 bits
            String binario = Integer.toBinaryString(memoriaMP[i] & 0xFFFF);
            binario = String.format("%16s", binario).replace(" ", "0");
            System.out.println("Endereço de memória: " + i + "; valor: " + binario);
        }
    }

    public int getIndicador(){
        return (indicador);
    }

}
