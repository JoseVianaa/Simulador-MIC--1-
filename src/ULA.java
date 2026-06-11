public class ULA {

    // Flags de status acessíveis após a última operação
    private boolean flagN; // True se o resultado da operação for negativo
    private boolean flagZ; // True se o resultado da operação for zero

    public ULA() {
        this.flagN = false;
        this.flagZ = false;
    }

    public short calcular(short a, short b, int sinalControle) {
        // a => resuktado do barramento a
        //b => resultado do barramento b

        short resultado = 0;

        switch (sinalControle) {
            case 0: // Exemplo: A + B
                resultado = (short) (a + b);
                break;
            case 1: // Exemplo: A AND B
                resultado = (short) (a & b);
                break;
            case 2: // Passa A direto (útil para mover dados entre registradores)
                resultado = a;
                break;
            case 3: // Exemplo: Inversor (NOT A)
                resultado = (short) (~a);
                break;

            default:
                throw new IllegalArgumentException("Sinal de controle ULA inválido");
        }

        // Atualiza as flags baseadas no resultado
        atualizarFlags(resultado);

        return resultado;
    }

    private void atualizarFlags(short resultado) {
        this.flagZ = (resultado == 0);
        this.flagN = (resultado < 0);
    }

    public boolean getFlagN() { return flagN; }
    public boolean getFlagZ() { return flagZ; }
}