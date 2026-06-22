package com.example.simulador;

public class UnidadeControle {
    MemoriaControle mc;
    Microinstrucao MIR;
    Registrador MPC;

    public UnidadeControle (){
        this.mc = new MemoriaControle();
        this.MIR = new Microinstrucao();
        this.MPC = new Registrador("MPC");
    }

    public void printarMC(){
        this.mc.printarMemoriaMC();
    }
    
    public Microinstrucao buscarMicroinstrucao() {
        return mc.getMicroinstrucao(MPC.getValor());
    }

    public Registrador getMPC() {
        return MPC;
    }

    public Microinstrucao getMIR() {
        return MIR;
    }

    public void setMIR(Microinstrucao microinstrucao) {
        this.MIR = microinstrucao;
    }
}
