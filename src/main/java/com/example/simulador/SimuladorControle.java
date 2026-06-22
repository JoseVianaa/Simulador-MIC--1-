package com.example.simulador;

import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SimuladorControle {

    private BancoRegistradores bancoRegistradores;
    private TextField[] camposRegistradores;
    private CPU processador;
    private TextField campoFlagN;
    private TextField campoFlagZ;
    private TextField[] camposMemoria;
    private TextArea areaEntrada;

    private String caminhoPrograma = "ProgramaInterface.txt";

    public SimuladorControle(
            BancoRegistradores bancoRegistradores,
            TextField[] camposRegistradores,
            TextField campoFlagN,
            TextField campoFlagZ,
            TextField[] camposMemoria,
            TextArea areaEntrada) {

        this.bancoRegistradores = bancoRegistradores;
        this.camposRegistradores = camposRegistradores;
        this.campoFlagN = campoFlagN;
        this.campoFlagZ = campoFlagZ;
        this.camposMemoria = camposMemoria;
        this.areaEntrada = areaEntrada;
    }
    

    public void carregarPrograma(String codigo) {
        try {
        	
        	System.out.println(
        		    new java.io.File(caminhoPrograma).getAbsolutePath()
        		);
        	
            FileWriter writer = new FileWriter(caminhoPrograma);
            writer.write(codigo);
            writer.close();

            processador = new CPU();

            System.out.println("Programa carregado em: " + caminhoPrograma);
            System.out.println(codigo);

            atualizarRegistradoresNaTela();

        } catch (IOException e) {
            System.out.println("Erro ao carregar programa.");
            e.printStackTrace();
        }
    }
    
    public void executarPrograma() {
        System.out.println("Executando programa...");

        try {
        	if (processador == null) {
        	    processador = new CPU();
        	}

            processador.executar(caminhoPrograma);
            
            System.out.println("FLAG N = " + processador.getFlagN());
            System.out.println("FLAG Z = " + processador.getFlagZ());
            
            atualizarFlags();
            atualizarRegistradoresNaTela();
            atualizarMemoriaNaTela();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo do programa não encontrado.");
            e.printStackTrace();
        }
    }
    

    public void resetar() {
        processador = new CPU();

        atualizarRegistradoresNaTela();
        atualizarFlags();
        limparMemoriaNaTela();

        areaEntrada.clear();

        System.out.println("Resetando simulador...");
    }
    
    private void limparMemoriaNaTela() {
        for (int i = 0; i < camposMemoria.length; i++) {
            camposMemoria[i].setText("0000");
        }
    }
    
    private void atualizarFlags() {

        campoFlagN.setText(
                processador.getFlagN() ? "1" : "0"
        );

        campoFlagZ.setText(
                processador.getFlagZ() ? "1" : "0"
        );
    }
    
    private void atualizarMemoriaNaTela() {
        Memoria memoria = processador.getMemoriaMP();

        for (int i = 0; i < camposMemoria.length; i++) {
            short valor = memoria.getAddress(i);

            String hexadecimal = String.format("%04X", valor & 0xFFFF);

            camposMemoria[i].setText(hexadecimal);
        }
    }

    private void atualizarRegistradoresNaTela() {
        for (int i = 0; i < 16; i++) {
            camposRegistradores[i].setText(
                    String.valueOf(
                    		processador.getBancoRegistradores().getRegistrador(i).getValor()
                    )
            );
        }
    }
}