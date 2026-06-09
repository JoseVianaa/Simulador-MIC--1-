import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Memoria memoria = new Memoria();
        AssemblyParser assemblyParser = new AssemblyParser("src\\Programa.txt");
        ArrayList<Short> fileLines = assemblyParser.parser();
        memoria.carregarArquivo(fileLines);
        memoria.printMemoriaBin();
    }
}