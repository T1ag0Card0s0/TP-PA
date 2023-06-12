package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class GameOverUI extends BorderPane {
    GameManager gameManager;
    ImageView gameOver;
    Label lblTitle,lblPts;
    TextField nameInput;
    Button btnOk;

    public GameOverUI(GameManager gameManager){
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }

    public void createViews(){
        gameOver = new ImageView(ImageManager.getImage("game-over.png"));
        lblPts = new Label("Pontuação: 0");
        lblTitle = new Label("Introduza um username");
        lblTitle.setId("game_overTitle");
        lblPts.setId("game_overTitle");
        nameInput = new TextField();
        nameInput.setId("game_overInput");
        btnOk = new Button("Submeter");
        btnOk.getStyleClass().add("btnInitScreen");
        VBox vBoxLabels = new VBox(lblPts,lblTitle);
        vBoxLabels.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(gameOver,vBoxLabels,nameInput,btnOk);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        this.setTop(vBox);
    }
    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt ->{
            update();
        });
        btnOk.setOnAction(event->{
            if(nameInput.getText().length()>0) {
                ModelLog.getInstance().reset();
                gameManager.addTop5(nameInput.getText());
                gameManager.saveTop5();
                gameManager.exit();
            }else{
                lblTitle.setStyle("-fx-font-size: 25px;-fx-text-fill: red");
            }
        });
    }

    public void update(){
        if (!gameManager.FSM_Is_Created()) {
            this.setVisible(false);
            return;
        }
        if(!gameManager.lostGame()){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
        lblPts.setText("Pontuação: "+gameManager.getPoints());
    }
}
