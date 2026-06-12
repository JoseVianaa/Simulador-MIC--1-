public class Microinstrucao {
    private short amux;   // 1 bit
    private short cond;   // 2 bits
    private short alu;    // 2 bits
    private short sh;     // 2 bits
    private short mbr;    // 1 bit
    private short mar;    // 1 bit
    private short rd;     // 1 bit
    private short wr;     // 1 bit
    private short enc;    // 1 bit
    private short c;      // 4 bits
    private short b;      // 4 bits
    private short a;      // 4 bits
    private short addr;   // 8 bits

    public Microinstrucao(){}

    public short getAmux() {
        return amux;
    }

    public void setAmux(short amux) {
        this.amux = amux;
    }

    public short getCond() {
        return cond;
    }

    public void setCond(short cond) {
        this.cond = cond;
    }

    public short getAlu() {
        return alu;
    }

    public void setAlu(short alu) {
        this.alu = alu;
    }

    public short getSh() {
        return sh;
    }

    public void setSh(short sh) {
        this.sh = sh;
    }

    public short getMbr() {
        return mbr;
    }

    public void setMbr(short mbr) {
        this.mbr = mbr;
    }

    public short getMar() {
        return mar;
    }

    public void setMar(short mar) {
        this.mar = mar;
    }

    public short getRd() {
        return rd;
    }

    public void setRd(short rd) {
        this.rd = rd;
    }

    public short getWr() {
        return wr;
    }

    public void setWr(short wr) {
        this.wr = wr;
    }

    public short getEnc() {
        return enc;
    }

    public void setEnc(short enc) {
        this.enc = enc;
    }

    public short getC() {
        return c;
    }

    public void setC(short c) {
        this.c = c;
    }

    public short getB() {
        return b;
    }

    public void setB(short b) {
        this.b = b;
    }

    public short getA() {
        return a;
    }

    public void setA(short a) {
        this.a = a;
    }

    public short getAddr() {
        return addr;
    }

    public void setAddr(short addr) {
        this.addr = addr;
    }

    private String paraBinario(short valor, int bits) {
        String bin = Integer.toBinaryString(valor & ((1 << bits) - 1));
        return String.format("%" + bits + "s", bin).replace(' ', '0');
    }

    public void printMicroinstrucaoBin() {
        System.out.println(
                paraBinario(amux, 1) + " " +
                        paraBinario(cond, 2) + " " +
                        paraBinario(alu, 2) + " " +
                        paraBinario(sh, 2) + " " +
                        paraBinario(mbr, 1) + " " +
                        paraBinario(mar, 1) + " " +
                        paraBinario(rd, 1) + " " +
                        paraBinario(wr, 1) + " " +
                        paraBinario(enc, 1) + " " +
                        paraBinario(c, 4) + " " +
                        paraBinario(b, 4) + " " +
                        paraBinario(a, 4) + " " +
                        paraBinario(addr, 8)
        );
    }
}