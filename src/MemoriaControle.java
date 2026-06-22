public class MemoriaControle {
    private Microinstrucao[] microprograma;

    public MemoriaControle(){
        this.microprograma = new Microinstrucao[256];
        inicializarMemoriaMC();
    }

    private void inicializarMemoriaMC(){
        String microprogramaText = "0 00 10 00 0 1 1 0 0 0000 0000 0000 00000000\n" +
                "0 00 00 00 0 0 1 0 1 0000 0110 0000 00000000\n" +
                "1 10 10 00 0 0 0 0 1 0011 0000 0000 00011100\n" +
                "0 10 00 10 0 0 0 0 1 0100 0011 0011 00010011\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 00001011\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00001001\n" +
                "0 00 10 00 0 1 1 0 0 0000 0011 0000 00000000\n" +
                "0 00 10 00 0 0 1 0 0 0000 0000 0000 00000000\n" +
                "1 11 10 00 0 0 0 0 1 0001 0000 0000 00000000\n" +
                "0 00 10 00 1 1 0 1 0 0000 0011 0001 00000000\n" +
                "0 11 10 00 0 0 0 1 0 0000 0000 0000 00000000\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00001111\n" +
                "0 00 10 00 0 1 1 0 0 0000 0011 0000 00000000\n" +
                "0 00 10 00 0 0 1 0 0 0000 0000 0000 00000000\n" +
                "1 11 00 00 0 0 0 0 1 0001 0001 0000 00000000\n" +
                "0 00 10 00 0 1 1 0 0 0000 0011 0000 00000000\n" +
                "0 00 00 00 0 0 1 0 1 0001 0110 0001 00000000\n" +
                "1 00 11 00 0 0 0 0 1 1010 0000 0000 00000000\n" +
                "0 11 00 00 0 0 0 0 1 0001 1010 0001 00000000\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 00011001\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00010111\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0001 00000000\n" +
                "0 11 01 00 0 0 0 0 1 0000 1000 0011 00000000\n" +
                "0 01 10 00 0 0 0 0 0 0000 0000 0001 00010110\n" +
                "0 11 10 00 0 0 0 0 0 0000 0000 0000 00000000\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00011011\n" +
                "0 11 01 00 0 0 0 0 1 0000 1000 0011 00000000\n" +
                "0 11 01 00 0 0 0 0 1 0001 1000 0011 00000000\n" +
                "0 10 00 10 0 0 0 0 1 0100 0011 0011 00101000\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 00100011\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00100001\n" +
                "0 00 00 00 0 0 0 0 1 1010 0010 0011 00000000\n" +
                "0 11 10 00 0 1 1 0 0 0000 1010 0000 00000111\n" +
                "0 00 00 00 0 0 0 0 1 1010 0010 0011 00000000\n" +
                "0 11 10 00 1 1 0 1 0 0000 1010 0001 00001010\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00100110\n" +
                "0 00 00 00 0 0 0 0 1 1010 0010 0011 00000000\n" +
                "0 11 10 00 0 1 1 0 0 0000 1010 0000 00001101\n" +
                "0 00 00 00 0 0 0 0 1 1010 0010 0011 00000000\n" +
                "0 11 10 00 0 1 1 0 0 0000 1010 0000 00010000\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 00101110\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00101100\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0001 00010110\n" +
                "0 11 10 00 0 0 0 0 0 0000 0000 0000 00000000\n" +
                "0 01 10 00 0 0 0 0 0 0000 0000 0001 00000000\n" +
                "0 11 01 00 0 0 0 0 1 0000 1000 0011 00000000\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 00110010\n" +
                "0 00 00 00 0 0 0 0 1 0010 0111 0010 00000000\n" +
                "0 00 10 00 1 1 0 1 0 0000 0010 0000 00000000\n" +
                "0 11 01 00 0 0 0 1 1 0000 1000 0011 00000000\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 01000001\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 00111011\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00111000\n" +
                "0 00 10 00 0 1 1 0 0 0000 0001 0000 00000000\n" +
                "0 00 00 00 0 0 1 0 1 0010 0111 0010 00000000\n" +
                "0 11 10 00 0 1 0 1 0 0000 0010 0000 00001010\n" +
                "0 00 00 00 0 1 1 0 1 0010 0110 0010 00000000\n" +
                "0 00 10 00 0 0 1 0 0 0000 0000 0000 00000000\n" +
                "0 11 10 00 0 1 0 1 0 0000 0001 0000 00001010\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 00111110\n" +
                "0 00 00 00 0 0 0 0 1 0010 0111 0010 00000000\n" +
                "0 11 10 00 1 1 0 1 0 0000 0010 0001 00001010\n" +
                "0 00 00 00 0 1 1 0 1 0010 0110 0010 00000000\n" +
                "0 00 10 00 0 0 1 0 0 0000 0000 0000 00000000\n" +
                "1 11 10 00 0 0 0 0 1 0001 0000 0000 00000000\n" +
                "0 10 10 10 0 0 0 0 1 0100 0000 0100 01001001\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 01000110\n" +
                "0 00 00 00 0 1 1 0 1 0010 0110 0010 00000000\n" +
                "0 00 10 00 0 0 1 0 0 0000 0000 0000 00000000\n" +
                "1 11 10 00 0 0 0 0 1 0000 0000 0000 00000000\n" +
                "0 00 10 00 0 0 0 0 1 1010 0000 0001 00000000\n" +
                "0 00 10 00 0 0 0 0 1 0001 0000 0010 00000000\n" +
                "0 11 10 00 0 0 0 0 1 0010 0000 1010 00000000\n" +
                "0 10 10 00 0 0 0 0 0 0000 0000 0100 01001100\n" +
                "0 00 01 00 0 0 0 0 1 1010 1001 0011 00000000\n" +
                "0 11 00 00 0 0 0 0 1 0010 1010 0010 00000000\n" +
                "0 00 01 00 0 0 0 0 1 1010 1001 0011 00000000\n" +
                "0 00 11 00 0 0 0 0 1 1010 0000 1010 00000000\n" +
                "0 11 00 00 0 0 0 0 1 1010 0110 1010 01001011";
        String[] microprogramaParts = microprogramaText.trim().split("\n");
        for (int i=0; i<256; i++){
            microprograma[i] = new Microinstrucao();
            if (i<microprogramaParts.length){
                String[] microinstrucaoParts = microprogramaParts[i].split("\\s+");
                microprograma[i].setAmux(Short.parseShort(microinstrucaoParts[0], 2));
                microprograma[i].setCond(Short.parseShort(microinstrucaoParts[1], 2));
                microprograma[i].setAlu(Short.parseShort(microinstrucaoParts[2], 2));
                microprograma[i].setSh(Short.parseShort(microinstrucaoParts[3], 2));
                microprograma[i].setMbr(Short.parseShort(microinstrucaoParts[4], 2));
                microprograma[i].setMar(Short.parseShort(microinstrucaoParts[5], 2));
                microprograma[i].setRd(Short.parseShort(microinstrucaoParts[6], 2));
                microprograma[i].setWr(Short.parseShort(microinstrucaoParts[7], 2));
                microprograma[i].setEnc(Short.parseShort(microinstrucaoParts[8], 2));
                microprograma[i].setC(Short.parseShort(microinstrucaoParts[9], 2));
                microprograma[i].setB(Short.parseShort(microinstrucaoParts[10], 2));
                microprograma[i].setA(Short.parseShort(microinstrucaoParts[11], 2));
                microprograma[i].setAddr(Short.parseShort(microinstrucaoParts[12], 2));
            }
            else {
                microprograma[i].setAmux((short) 0);
                microprograma[i].setCond((short) 0);
                microprograma[i].setAlu((short) 0);
                microprograma[i].setSh((short) 0);
                microprograma[i].setMbr((short) 0);
                microprograma[i].setMar((short) 0);
                microprograma[i].setRd((short) 0);
                microprograma[i].setWr((short) 0);
                microprograma[i].setEnc((short) 0);
                microprograma[i].setC((short) 0);
                microprograma[i].setB((short) 0);
                microprograma[i].setA((short) 0);
                microprograma[i].setAddr((short) 0);
            }
        }
    }

    public void printarMemoriaMC(){
        for (int i = 0; i<256; i++){
            microprograma[i].printMicroinstrucaoBin();
        }
    }

    public Microinstrucao[] getMicroprograma(){
        return microprograma;
    }

    public Microinstrucao getMicroinstrucao(int endereco){
        return microprograma[endereco];
    }

}
