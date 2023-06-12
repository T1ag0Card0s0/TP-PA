package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.util.ExitAlert;


public class BeginUI extends BorderPane {
    GameManager gameManager;
    Button btnInitGame,btnTop5,btnExit;
    public BeginUI(GameManager gameManager){
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }
    public void createViews() {
        ImageView imageView = new ImageView(ImageManager.getImage("isec-logo.png"));
        Label label = new Label("""
        DEIS-ISEC-IPC
        Licenciatura Engenharia Informática (LEI)
        Trabalho realizado no âmbito da Unidade Curricular Programação Avançada
        Autor:
            Tiago Rafael Santos Cardoso, 2011138999
        2022/2023""");

        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill:  grey;");
        VBox vBoxInfo = new VBox(imageView, label);
        vBoxInfo.setAlignment(Pos.CENTER);
        btnInitGame = new Button("Iniciar Jogo");
        btnTop5 = new Button("Top 5");
        btnExit = new Button("Sair");
        btnInitGame.getStyleClass().add("btnInitScreen");
        btnTop5.getStyleClass().add("btnInitScreen");
        btnExit.getStyleClass().add("btnInitScreen");

        ImageView imgView = new ImageView(ImageManager.getImage("title.png"));
        VBox vBox = new VBox(imgView, btnInitGame, btnTop5, btnExit, vBoxInfo);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt -> update());
        btnInitGame.setOnAction( event -> {
            if(gameManager.existSavedGame()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Iniciar Jogo");
                alert.setHeaderText(null);
                alert.setContentText("Existe um jogo guardado deseja carrega-lo?");

                ButtonType buttonTypeYes = new ButtonType("Sim");
                ButtonType buttonTypeNo = new ButtonType("Não");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                alert.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeNo) {
                        gameManager.start();
                    }else if(response == buttonTypeYes){
                        gameManager.load();
                    }
                });
            }else{
                gameManager.start();
            }
        });
        btnTop5.setOnAction( event -> {
            gameManager.loadTop5();
        });
        btnExit.setOnAction( event -> {
            ExitAlert.show(getScene().getWindow());
        });

    }
    public void update(){
        if(gameManager.FSM_Is_Created()||gameManager.top5IsLoaded()){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }


}
