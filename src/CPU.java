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
        memoriaMP.carregarArquivo(parsedLines);
        memoriaMP.printMemoriaBin();
    }

    public void executar(String nomeArquivo) throws FileNotFoundException {
        this.declararPrograma(nomeArquivo);
        this.carregarProgramaMP();
        for (int i = 0; i<4096; i++){
            this.primeiroSubCiclo();
            this.segundoSubCiclo();
            this.terceiroSubCiclo();
            this.quartoSubCiclo();
        }


    }

    public void primeiroSubCiclo(){
        System.out.println("Primeiro subciclo...");
    }

    public void segundoSubCiclo(){
        System.out.println("Segundo subciclo...");
    }

    public void terceiroSubCiclo(){
        System.out.println("Terceiro subciclo...");
    }

    public void quartoSubCiclo(){
        System.out.println("Quarto subciclo...");
    }


    public void printarMP(){
        this.memoriaMP.printMemoriaBin();
    }
    public void printarBancoBarramentos(){
        this.bancoRegistradores.printarBancoRegistradores();
    }
    public void printarMC(){
        this.uc.printarMC();
    }
}
