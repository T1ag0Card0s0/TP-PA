package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class BeginUI extends BorderPane {
    GameManager gameManager;
    Button btnInitGame,btnTop5,btnExit;

    public BeginUI(GameManager gameManager){
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }
    public void createViews(){
        btnInitGame = new Button("Iniciar Jogo");
        btnTop5 = new Button("Top 5");
        btnExit = new Button("Sair");
        btnInitGame.setId("btnInitScreen");btnTop5.setId("btnInitScreen");btnExit.setId("btnInitScreen");
        ImageView imgView = new ImageView(ImageManager.getImage("title.png"));
        VBox vBox = new VBox(imgView,btnInitGame,btnTop5,btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }
    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt -> { update(); });
        btnInitGame.setOnAction( event -> {
            gameManager.start();
        });
        btnTop5.setOnAction( event -> {
        });
        btnExit.setOnAction( event -> {
            Platform.exit();
        });
    }
    public void update(){
        if(gameManager.FSM_Is_Created()){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }


}
