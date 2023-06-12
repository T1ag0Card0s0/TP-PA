package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.log.ModelLog;

public class GameOverUI extends BorderPane {
    GameManager gameManager;
    Label lblTitle;
    TextField nameInput;
    Button btnOk;

    public GameOverUI(GameManager gameManager){
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }

    public void createViews(){
        lblTitle = new Label("GAME OVER");
        lblTitle.setId("game_over");
        nameInput = new TextField();
        nameInput.setStyle("-fx-pref-width: 200px;-fx-pref-height: 50px");
        btnOk = new Button("Submit");
        btnOk.getStyleClass().add("btnInitScreen");
        VBox vBox = new VBox(lblTitle,nameInput,btnOk);
        vBox.setAlignment(Pos.CENTER);
        this.setCenter(vBox);
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

    }
}
