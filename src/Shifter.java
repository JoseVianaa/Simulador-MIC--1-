public class Shifter {

    public Shifter(){

    }

    public short calcular(short valor, short sinalControle){
        if (sinalControle == 2){
            return (short) (valor*2);
        }
        else if (sinalControle == 3){
            return (short) (valor/2);
        }
        else {
            return valor;
        }
    }
}
