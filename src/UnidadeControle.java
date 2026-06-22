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
}
