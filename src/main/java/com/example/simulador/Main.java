package com.example.simulador;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
    	SimuladorControle controle;
    	
    	//cria e fundo
        BorderPane root = new BorderPane();
        Image imagemFundo = new Image(
        	    getClass().getResource("/fundo3.jpg").toExternalForm()
        	);
        
        BackgroundImage bg = new BackgroundImage(
                imagemFundo,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        100, 100,
                        true, true,
                        true, false
                )
        );

        root.setBackground(new Background(bg));
        
        //titulo no topo c img
        Image imagemTitulo = new Image(
        	    getClass().getResource("/titulotrabalho2.png").toExternalForm()
        	);

        ImageView titulo = new ImageView(imagemTitulo);

        titulo.setFitWidth(1100);
        titulo.setPreserveRatio(true);

        StackPane topo = new StackPane(titulo);
        root.setTop(topo);
        
        //entrada
        TextArea entradaPrograma = new TextArea();

        entradaPrograma.setPromptText("Digite aqui o programa MIC-1...");
        entradaPrograma.setPrefSize(450, 300);
        entradaPrograma.setMaxWidth(600);

        entradaPrograma.setStyle(
                "-fx-control-inner-background: #202020;" +
                "-fx-text-fill: #00ff66;" +
                "-fx-font-family: Consolas;"
        );

        //titulo entrada
        Label tituloPrograma = new Label("PROGRAMA");

        tituloPrograma.setStyle(
                "-fx-text-fill: #00ff66;" +
                "-fx-font-weight: bold;"
        );

        //barra sup entrada
        HBox barraPrograma = new HBox();
        barraPrograma.getChildren().add(tituloPrograma);

        barraPrograma.setStyle(
                "-fx-background-color: #202020;"
        );

        barraPrograma.setPadding(new Insets(5));

        //painel entrada
        VBox painelPrograma = new VBox();

        painelPrograma.getChildren().addAll(
                barraPrograma,
                entradaPrograma
        );

        painelPrograma.setStyle(
                "-fx-background-color: #202020;" +
                "-fx-border-color: #555555;"
        );
  
        //botões
        
        //carregar
        Image imgCarregar = new Image(
        	    getClass().getResource("/carregar.png").toExternalForm()
        	);

        ImageView viewCarregar = new ImageView(imgCarregar);

        viewCarregar.setFitWidth(120);
        viewCarregar.setPreserveRatio(true);
        
        Button btnCarregar = new Button();
        btnCarregar.setGraphic(viewCarregar);
       
        btnCarregar.setStyle(
        	    "-fx-background-color: transparent;" +
        	    "-fx-padding: 0;"
        	);
        
        
        //executar
        Image imgExecutar = new Image(
        	    getClass().getResource("/executar.png").toExternalForm()
        	);

        ImageView viewExecutar = new ImageView(imgExecutar);

        viewExecutar.setFitWidth(120);
        viewExecutar.setPreserveRatio(true);
        
        Button btnExecutar = new Button();
        btnExecutar.setGraphic(viewExecutar);
       
        btnExecutar.setStyle(
        	    "-fx-background-color: transparent;" +
        	    "-fx-padding: 0;"
        	);
        
        //passo
        
        
        //resetar
        
        Image imgResetar = new Image(
        	    getClass().getResource("/resetar.png").toExternalForm()
        	);

        ImageView viewResetar = new ImageView(imgResetar);

        viewResetar.setFitWidth(120);
        viewResetar.setPreserveRatio(true);
        
        Button btnResetar = new Button();
        btnResetar.setGraphic(viewResetar);
       
        btnResetar.setStyle(
        	    "-fx-background-color: transparent;" +
        	    "-fx-padding: 0;"
        	);
        
        
        //painel botões
        HBox painelBotoes = new HBox(10);

        painelBotoes.getChildren().addAll(
                btnCarregar,
                btnExecutar,
                btnResetar
        );

        painelBotoes.setAlignment(Pos.CENTER_LEFT);

        //entrada
        VBox areaEntrada = new VBox(10);

        areaEntrada.getChildren().addAll(
                painelPrograma,
                painelBotoes
        );

        areaEntrada.setPadding(new Insets(-80, 20, 20, 65));

        root.setLeft(areaEntrada);
        
        
        //registradores
        GridPane gridRegistradores = new GridPane();
        gridRegistradores.setStyle(
        	    "-fx-background-color: #2b2b2b;"
        	);

        gridRegistradores.setHgap(30);
        gridRegistradores.setVgap(8);
        gridRegistradores.setPadding(new Insets(10));
        
        BancoRegistradores banco = new BancoRegistradores();
        TextField[] camposRegistradores = new TextField[16];
        
        // reg c1
        for (int i = 0; i < 5; i++) {

        	Label nome = new Label(
        	        banco.getRegistrador(i).getNome()
        	);
        	nome.setStyle("-fx-text-fill: white;");

        	TextField valor = new TextField(
        	        String.valueOf(
        	                banco.getRegistrador(i).getValor()
        	        )
        	);
        	
        	camposRegistradores[i] = valor;
        	
        	valor.setStyle(
        			"-fx-control-inner-background: #202020;" +
        			"-fx-text-fill: #00ff66;" +
        			"-fx-border-color: #555555;"
        			);

        	valor.setEditable(false);
        	valor.setPrefWidth(70);

        	gridRegistradores.add(nome, 0, i);
        	gridRegistradores.add(valor, 1, i);
        }

        //reg c2
        for (int i = 5; i < 10; i++) {

        	Label nome = new Label(
        	        banco.getRegistrador(i).getNome()
        	);
        	nome.setStyle("-fx-text-fill: white;");

        	TextField valor = new TextField(
        	        String.valueOf(
        	                banco.getRegistrador(i).getValor()
        	        )
        	);
        	
        	camposRegistradores[i] = valor;
        	
        	valor.setStyle(
        			"-fx-control-inner-background: #202020;" +
        			"-fx-text-fill: #00ff66;" +
        			"-fx-border-color: #555555;"
        			);

        	valor.setEditable(false);
        	valor.setPrefWidth(70);

        	gridRegistradores.add(nome, 2, i - 5);
        	gridRegistradores.add(valor, 3, i - 5);
        }

        //reg c3
        for (int i = 10; i < 16; i++) {
        	
        	Label nome = new Label(
        	        banco.getRegistrador(i).getNome()
        	);
         	nome.setStyle("-fx-text-fill: white;");

         	TextField valor = new TextField(
        	        String.valueOf(
        	                banco.getRegistrador(i).getValor()
        	        )
        	);
         	
         	camposRegistradores[i] = valor;
         	
         	valor.setStyle(
         			"-fx-control-inner-background: #202020;" +
         			"-fx-text-fill: #00ff66;" +
                    "-fx-border-color: #555555;"
         			);

         	valor.setEditable(false);
         	valor.setPrefWidth(70);

         	gridRegistradores.add(nome, 4, i - 10);
         	gridRegistradores.add(valor, 5, i - 10);
        }
        
     
     	// cria painel reg
        Label tituloRegs = new Label("REGISTRADORES");

        tituloRegs.setStyle(
            "-fx-text-fill: #00ff66;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );

        VBox painelRegistradores = new VBox(10);

        painelRegistradores.getChildren().addAll(
                tituloRegs,
                gridRegistradores
        );

        painelRegistradores.setStyle(
            "-fx-background-color: #202020;" +
            "-fx-border-color: #555555;"
        );
        
        painelRegistradores.setPrefSize(40, 20);
        
        BorderPane.setMargin(
        	    painelRegistradores,
        	    new Insets(-80, 100, 380, 0)
        	);
        
        
        // painel flags
        Label tituloFlags = new Label("FLAGS");

        tituloFlags.setStyle(
                "-fx-text-fill: #00ff66;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14px;"
        );

        Label nomeFN = new Label("N");
        nomeFN.setStyle("-fx-text-fill: white;");

        TextField valorFN = new TextField("0");
        valorFN.setEditable(false);
        valorFN.setPrefWidth(70);
        valorFN.setStyle(
                "-fx-control-inner-background: #202020;" +
                "-fx-text-fill: #00ff66;" +
                "-fx-border-color: #555555;"
        );

        Label nomeFZ = new Label("Z");
        nomeFZ.setStyle("-fx-text-fill: white;");

        TextField valorFZ = new TextField("0");
        valorFZ.setEditable(false);
        valorFZ.setPrefWidth(70);
        valorFZ.setStyle(
                "-fx-control-inner-background: #202020;" +
                "-fx-text-fill: #00ff66;" +
                "-fx-border-color: #555555;"
        );

        HBox linhaFlags = new HBox(15);
        linhaFlags.getChildren().addAll(
                nomeFN, valorFN,
                nomeFZ, valorFZ
        );

        VBox painelFlags = new VBox(10);
        painelFlags.getChildren().addAll(
                tituloFlags,
                linhaFlags
        );

        painelFlags.setPadding(new Insets(10));

        painelFlags.setStyle(
                "-fx-background-color: #2a2a2a;" +
                "-fx-border-color: #555555;"
        );

        //painel reg+flsgs
        VBox painelDireito = new VBox(15);

        painelDireito.getChildren().addAll(
                painelRegistradores,
                painelFlags
        );
        
        painelDireito.setPrefWidth(450);

        BorderPane.setMargin(
                painelDireito,
                new Insets(-80, 65, 0, 0)
        );
        
        
 
        //painel mem
        Label tituloMemoria = new Label("MEMÓRIA PRINCIPAL");

        tituloMemoria.setStyle(
                "-fx-text-fill: #00ff66;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14px;"
        );

        GridPane gridMemoria = new GridPane();

        gridMemoria.setHgap(6);
        gridMemoria.setVgap(6);
        gridMemoria.setPadding(new Insets(10));

        int totalMemoria = 4096;
        int colunasMemoria = 17;
        TextField[] camposMemoria = new TextField[totalMemoria];

        
        for (int i = 0; i < totalMemoria; i++) {

            if (i % colunasMemoria == 0) {

                Label endereco = new Label(
                        String.format("%04x", i)
                );

                endereco.setStyle(
                        "-fx-text-fill: #00ff66;" +
                        "-fx-font-weight: bold;"
                );

                gridMemoria.add(
                        endereco,
                        0,
                        i / colunasMemoria
                );
            }

            TextField celula = new TextField("0000");
            camposMemoria[i] = celula;

            celula.setEditable(false);
            celula.setPrefWidth(50);
            celula.setAlignment(Pos.CENTER);

            celula.setStyle(
                    "-fx-control-inner-background: #202020;" +
                    "-fx-text-fill: #00ff66;" +
                    "-fx-border-color: #555555;"
            );

            int coluna = (i % colunasMemoria) + 1;
            int linha = i / colunasMemoria;

            gridMemoria.add(celula, coluna, linha);
        }

        VBox painelMemoria = new VBox(10);

        ScrollPane scrollMemoria = new ScrollPane(gridMemoria);

        scrollMemoria.setPrefHeight(150);
        scrollMemoria.setFitToWidth(true);

        scrollMemoria.setStyle(
                "-fx-background-color: #202020;" +
                "-fx-control-inner-background: #202020;"+
                "-fx-background: #202020;" +
        	    "-fx-background-color: #202020;"
        );

        painelMemoria.getChildren().addAll(
                tituloMemoria,
                scrollMemoria
        );
        

        painelMemoria.setPadding(new Insets(10));

        painelMemoria.setStyle(
                "-fx-background-color: #202020;" +
                "-fx-border-color: #555555;"
        );
        
        
        BorderPane.setMargin(
                painelMemoria,
                new Insets(20, 65, 20, 65)
        );
        
        
        controle = new SimuladorControle(banco, camposRegistradores, valorFN, valorFZ, camposMemoria, entradaPrograma);
        
        
        //botoes acoes
        //Button btnPasso = new Button("Passo");
        
        btnCarregar.setOnAction(e ->
        controle.carregarPrograma(
            entradaPrograma.getText()
            )
        );

        btnExecutar.setOnAction(e ->
        	controle.executarPrograma()
        	);

        btnResetar.setOnAction(e ->
        	controle.resetar()
        	);

       // btnPasso.setOnAction(e ->
       //System.out.println("Passo clicado"));


        //mostrando
        root.setRight(painelDireito);
        root.setBottom(painelMemoria);
        
        //cena
        Scene scene = new Scene(root, 1200, 850);

        stage.setTitle("Simulador MIC-1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}