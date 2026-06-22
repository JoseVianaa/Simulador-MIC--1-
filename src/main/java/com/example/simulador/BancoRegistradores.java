package com.example.simulador;

public class BancoRegistradores {
    private final Registrador[] bancoRegistradores;

    public BancoRegistradores() {
        this.bancoRegistradores = new Registrador[16];

        for (int i = 0; i < bancoRegistradores.length; i++) {
            bancoRegistradores[i] = new Registrador("");
        }

        inicializaRegistradores();
    }

    public void inicializaRegistradores() {
        bancoRegistradores[0].setNome("PC");    	    // PC
        bancoRegistradores[0].setValor((short) 0);    	// PC

        bancoRegistradores[1].setNome("AC");    	    // AC
        bancoRegistradores[1].setValor((short) 0);    	// AC

        bancoRegistradores[2].setNome("SP"); 	        // SP
        bancoRegistradores[2].setValor((short) 4095); 	// SP

        bancoRegistradores[3].setNome("IR");    	    // IR
        bancoRegistradores[3].setValor((short) 0);    	// IR

        bancoRegistradores[4].setNome("TIR");    	    // TIR
        bancoRegistradores[4].setValor((short) 0);    	// TIR

        bancoRegistradores[5].setNome("0");    	        // 0
        bancoRegistradores[5].setValor((short) 0);    	// 0

        bancoRegistradores[6].setNome("+1");    	    // +1
        bancoRegistradores[6].setValor((short) 1);    	// +1

        bancoRegistradores[7].setNome("-1");   	        // -1
        bancoRegistradores[7].setValor((short) -1);   	// -1

        bancoRegistradores[8].setNome("AMASK"); 	    // AMASK
        bancoRegistradores[8].setValor((short) 4095); 	// AMASK

        bancoRegistradores[9].setNome("SMASK");  	    // SMASK
        bancoRegistradores[9].setValor((short) 255);  	// SMASK

        bancoRegistradores[10].setNome("A");	        //A
        bancoRegistradores[10].setValor((short) 0);	    //A

        bancoRegistradores[11].setNome("B");	        //B
        bancoRegistradores[11].setValor((short) 0); 	//B

        bancoRegistradores[12].setNome("C");	        //C
        bancoRegistradores[12].setValor((short) 0);	    //C

        bancoRegistradores[13].setNome("D");	        //D
        bancoRegistradores[13].setValor((short) 0);	    //D

        bancoRegistradores[14].setNome("E");	        //E
        bancoRegistradores[14].setValor((short) 0);	    //E

        bancoRegistradores[15].setNome("F");	        //F
        bancoRegistradores[15].setValor((short) 0);	    //F
    }

    public Registrador getRegistrador(int indice) {
        return bancoRegistradores[indice];
    }

    public void printarBancoRegistradores(){
        for (int i=0; i<16; i++){
            bancoRegistradores[i].printRegistrador();
        }
    }

}
