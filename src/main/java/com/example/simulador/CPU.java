package com.example.simulador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CPU {

    Memoria memoriaMP;
    AssemblyParser montador;
    Registrador mar;
    Registrador mbr;
    BancoRegistradores bancoRegistradores;
    AMUX Amux;
    Shifter shifter;
    ULA ula;
    short barramentoA;
    short barramentoB;
    short barramentoC;
    UnidadeControle uc;
    
    private int tamanhoPrograma;

    public CPU (){
        this.memoriaMP =  new Memoria();
        this.montador = new AssemblyParser("");
        this.mar = new Registrador("MAR");
        this.mbr = new Registrador("MBR");
        this.bancoRegistradores = new BancoRegistradores();
        this.Amux = new AMUX();
        this.shifter = new Shifter();
        this.ula = new ULA();
        this.barramentoA = 0;
        this.barramentoB = 0;
        this.barramentoC = 0;
        this.uc = new UnidadeControle();
    }

    private void declararPrograma(String nomeArquivo){
        montador.setNomeArquivo(nomeArquivo);
    }

    private void carregarProgramaMP() throws FileNotFoundException {
        ArrayList<Short> parsedLines = montador.parser();
        this.tamanhoPrograma = parsedLines.size();
        memoriaMP.carregarArquivo(parsedLines);
    }

    public void executar(String nomeArquivo) throws FileNotFoundException {
        this.declararPrograma(nomeArquivo);
        this.carregarProgramaMP();

        int limiteSeguranca = 1000;
        int ciclos = 0;

        while (ciclos < limiteSeguranca) {
            this.primeiroSubCiclo();
            this.segundoSubCiclo();
            this.terceiroSubCiclo();
            this.quartoSubCiclo();

            ciclos++;

            int pc = bancoRegistradores.getRegistrador(0).getValor();
            int mpc = uc.MPC.getValor();

            if (pc >= tamanhoPrograma && mpc == 0) {
                break;
            }
        }
    }


    public void primeiroSubCiclo(){
        // Subciclo 1: Buscar a Microinstrução
        // O registo MPC (Micro Program Counter) indica qual a linha da Memória de Controle devemos ler.
        int enderecoMPC = uc.MPC.getValor();
        uc.MIR = uc.mc.getMicroinstrucao(enderecoMPC);
    }

    public void segundoSubCiclo(){
        // Subciclo 2: Carregar Barramentos
        // Carregar para os barramentos A e B os dados dos registradores
        // indicados pelos sinais de controle A e B do MIR

        int indiceA = uc.MIR.getA();
        int indiceB = uc.MIR.getB();

        this.barramentoA = bancoRegistradores.getRegistrador(indiceA).getValor();
        this.barramentoB = bancoRegistradores.getRegistrador(indiceB).getValor();
    }

    public void terceiroSubCiclo(){
        // Subciclo 3: Execução

        // 1. O Amux decide se a ULA recebe o Barramento A ou o MBR do lado esquerdo, baseado no sinal de controle
        short operandoEsquerdo = Amux.selecionarValor(this.barramentoA, this.mbr.getValor(), uc.MIR.getAmux());

        // 2. A ULA faz o cálculo e atualiza as flags internas (Zero e Negativo) (Recebe o sinal de
        // controle para determinar qual ação deve ser feita)
        short resultadoULA = ula.calcular(operandoEsquerdo, this.barramentoB, uc.MIR.getAlu());

        // 3. O Shifter faz o deslocamento (se houver) e coloca o dado final no Barramento C
        this.barramentoC = shifter.calcular(resultadoULA, uc.MIR.getSh());

        // 4. Calcular o próximo MPC
        short proximoMPC = (short) (uc.MPC.getValor() + 1); // Caminho padrão (No Branch)
        short condicao = uc.MIR.getCond();

        if (condicao == 1) { // JUMP if Zero
            if (ula.getFlagZ()){
                proximoMPC = uc.MIR.getAddr();
            }
        } else if (condicao == 2) { // JUMP if Negative
            if (ula.getFlagN()){
                proximoMPC = uc.MIR.getAddr();
            }
        } else if (condicao == 3) { // Unconditional JUMP
            proximoMPC = uc.MIR.getAddr();
        }

        // Atualiza o MPC para o próximo ciclo de clock
        uc.MPC.setValor(proximoMPC);
    }

    public void quartoSubCiclo(){
        // Subciclo 4: Gravação
        // 1. Gravação no Banco de Registradores (Se ENC estiver ativo)
        if (uc.MIR.getEnc() == 1) {
            int indiceC = uc.MIR.getC();
            bancoRegistradores.getRegistrador(indiceC).setValor(this.barramentoC);
        }

        // 2. Carregar o MAR (Memory Address Register)
        // o MAR é carregado a partir do Barramento B, faz a mascara para tirar os bits do opcode.
        if (uc.MIR.getMar() == 1) {
            //System.out.println("Barramento B = " + barramentoB);
            this.mar.setValor((short) (this.barramentoB & 0x0FFF));
            //System.out.println("MAR = " + this.mar.getValor());
        }

        // 3. Carregar o MBR a partir da ULA (Resultado da ULA fica no Barramento C) se solicitado
        if (uc.MIR.getMbr() == 1) {
            this.mbr.setValor(this.barramentoC);
        }

        // 4. Operações de MP (RD e WR)
        if (uc.MIR.getRd() == 1) {
            int enderecoMemoria = Short.toUnsignedInt(this.mar.getValor());
            if (memoriaMP.validarAddress(enderecoMemoria)) {
                this.mbr.setValor(memoriaMP.getAddress(enderecoMemoria));
            }
        }

        if (uc.MIR.getWr() == 1) {
            int enderecoMemoria = Short.toUnsignedInt(this.mar.getValor());
            if (memoriaMP.validarAddress(enderecoMemoria)) {
                memoriaMP.setAddress(enderecoMemoria, this.mbr.getValor());
            }
        }
    }


    public void printarMP(){
        this.memoriaMP.printMemoriaBin();
    }
    public void printarBancoRegistradores(){
        this.bancoRegistradores.printarBancoRegistradores();
    }
    public void printarMC(){
        this.uc.printarMC();
    }
    
    public boolean getFlagN() {
        return ula.getFlagN();
    }

    public boolean getFlagZ() {
        return ula.getFlagZ();
    }

    public BancoRegistradores getBancoRegistradores() {
        return bancoRegistradores;
    }

    public Memoria getMemoriaMP() {
        return memoriaMP;
    }
}
