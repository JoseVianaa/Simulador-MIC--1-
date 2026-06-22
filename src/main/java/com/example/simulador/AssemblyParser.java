package com.example.simulador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class AssemblyParser {

    private String nomeArquivo;

    private static final Map<String, Integer> opcode12 = criarOpcodes12();
    private static final Map<String, Integer> opcode08 = criarOpcodes08();
    private static final Map<String, Integer> opcodeFixo = criarOpcodesFixos();

    private final ArrayList<Short> parsedLines = new ArrayList<>();

    public AssemblyParser(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    // Primeira leitura para mapear labels.
    private HashMap<String, Integer> mapearLabels() throws FileNotFoundException {
        int linhaAtual = 0;
        HashMap<String, Integer> symbols = new HashMap<>();

        try (Scanner arquivo = new Scanner(new File(nomeArquivo))) {
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine().trim();

                if (linha.isEmpty() || linha.charAt(0) == '/') { //Verificação de comentário e linha vazia
                    continue;
                }

                String[] linhaParts = linha.split("\\s+");
                int indiceInicio = 0;

                //Detecta labels, considera que pode haver no máximo uma label declarada por linha;
                if (linhaParts[0].charAt(linhaParts[0].length() - 1) == ':') {

                    if (symbols.containsKey(linhaParts[0].replace(":", ""))) {
                        throw new IllegalArgumentException("Label duplicada: " + linhaParts[0].replace(":", ""));
                    }
                    symbols.put(linhaParts[0].replace(":", ""), linhaAtual);
                    indiceInicio = 1;
                }

                if (indiceInicio < linhaParts.length) {
                    linhaAtual = linhaAtual + 1;
                }
            }
        }

        return symbols;
    }

    // Segunda leitura para gerar os binários
    public ArrayList<Short> parser() throws FileNotFoundException {
        HashMap<String, Integer> symbols = mapearLabels();
        int linhaAtual = 0;

        try (Scanner arquivo = new Scanner(new File(nomeArquivo))) {
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine().trim();

                //Se a linha for vazia, comentário, não considera a linha; não incrementa linhaAtual; não adiciona a lista de palavras que serão adicionadas a mémoria
                if (linha.isEmpty() || linha.charAt(0) == '/') {
                    continue;
                }

                String[] linhaParts = linha.split("\\s+");
                int indiceInicio = 0;

                // Se houver label no início, remove-a da parte a ser traduzida
                if (linhaParts[0].charAt(linhaParts[0].length() - 1) == ':') {
                    indiceInicio = 1;

                    // label-only: nada a traduzir
                    if (indiceInicio >= linhaParts.length) {
                        continue;
                    }
                }

                // Recorta a linha sem a label
                String[] linhaTraducao = Arrays.copyOfRange(linhaParts, indiceInicio, linhaParts.length);
                String mnemonico = linhaTraducao[0].toUpperCase();

                if (!opcode12.containsKey(mnemonico) && !opcode08.containsKey(mnemonico) && !opcodeFixo.containsKey(mnemonico)) {
                    throw new IllegalArgumentException("Instrução inválida. Mnemônico não encontrado. Linha: " + linhaAtual + "; Mnemônico: " + mnemonico);
                }

                String mnemonicoBin;
                String operando = "";

                if (opcode12.containsKey(mnemonico)) {
                    int valor = opcode12.get(mnemonico);
                    mnemonicoBin = Integer.toBinaryString(valor);
                    mnemonicoBin = String.format("%4s", mnemonicoBin).replace(" ", "0");

                    operando = tratarOperando(linhaTraducao, symbols, linhaAtual, 12);
                }
                else if (opcode08.containsKey(mnemonico)) {
                    int valor = opcode08.get(mnemonico);
                    mnemonicoBin = Integer.toBinaryString(valor);
                    mnemonicoBin = String.format("%8s", mnemonicoBin).replace(" ", "0");

                    operando = tratarOperando(linhaTraducao, symbols, linhaAtual, 8);
                }
                else {
                    int valor = opcodeFixo.get(mnemonico);
                    mnemonicoBin = Integer.toBinaryString(valor);
                    mnemonicoBin = String.format("%16s", mnemonicoBin).replace(" ", "0");
                    operando = "";
                }

                String linhaBin = mnemonicoBin + operando;
                parsedLines.add((short) Integer.parseUnsignedInt(linhaBin, 2));

                linhaAtual = linhaAtual + 1;
            }
        }

        return parsedLines;
    }

    private String tratarOperando(String[] linhaParts, HashMap<String, Integer> symbols, int iterador, int bits) {
        if (linhaParts.length < 2) {
            throw new IllegalArgumentException("Operando ausente. Linha: " + iterador);
        }

        String operando = linhaParts[1];

        if (!(symbols.containsKey(operando))) {
            try {
                int valorOperando = Integer.parseInt(operando);

                if (bits == 12) {
                    if (valorOperando > 4095 || valorOperando < 0) {
                        throw new IllegalArgumentException("Operando inválido. Linha: " + iterador + "; Operando: " + valorOperando);
                    }
                }
                else if (bits == 8) {
                    if (valorOperando > 255 || valorOperando < 0) {
                        throw new IllegalArgumentException("Operando inválido. Linha: " + iterador + "; Operando: " + valorOperando);
                    }
                }

                operando = Integer.toBinaryString(valorOperando);
                operando = String.format("%" + bits + "s", operando).replace(" ", "0");
            }
            catch (Exception e) {
                System.out.println("Operando inválido");
                operando = String.format("%" + bits + "s", "0").replace(" ", "0");
            }
        }
        else {
            int valorOperando = symbols.get(operando);
            operando = Integer.toBinaryString(valorOperando);
            operando = String.format("%" + bits + "s", operando).replace(" ", "0");
        }

        return operando;
    }

    private static Map<String, Integer> criarOpcodes12() {
        Map<String, Integer> map = new HashMap<>();
        map.put("LODD", 0);
        map.put("STOD", 1);
        map.put("ADDD", 2);
        map.put("SUBD", 3);
        map.put("JPOS", 4);
        map.put("JZER", 5);
        map.put("JUMP", 6);
        map.put("LOCO", 7);
        map.put("LODL", 8);
        map.put("STOL", 9);
        map.put("ADDL", 10);
        map.put("SUBL", 11);
        map.put("JNEG", 12);
        map.put("JNZE", 13);
        map.put("CALL", 14);
        return map;
    }

    private static Map<String, Integer> criarOpcodes08() {
        Map<String, Integer> map = new HashMap<>();
        map.put("INSP", 252);
        map.put("DESP", 254);
        return map;
    }

    private static Map<String, Integer> criarOpcodesFixos() {
        Map<String, Integer> map = new HashMap<>();
        map.put("PSHI", 61440);
        map.put("POPI", 61952);
        map.put("PUSH", 62464);
        map.put("POP", 62976);
        map.put("RETN", 63488);
        map.put("SWAP", 64000);
        map.put("HALT", 64512);
        return map;
    }

    public void setNomeArquivo(String nomeArquivo){
        this.nomeArquivo=nomeArquivo;
    }
}
