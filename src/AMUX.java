public class AMUX {

    public AMUX(){}

    public short selecionarValor(short a, short b, short sinalControle){
        // a é o dado vindo do barramento a; b é o dado vindo do MBR
        if(sinalControle==0){
            return a;
        }
        else{
            return b;
        }
    }
}
