package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.GameManager;

public class MainJFX extends Application {
    GameManager gameManager;

    @Override
    public void init() throws Exception {
        super.init();
        gameManager=new GameManager();
    }
    private void newStage(Stage stage,double x,double y){
        RootPane root = new RootPane(gameManager);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        stage.setScene(new Scene(root,600,600));
        stage.setTitle("Adicionei stage");
        stage.setUserData(gameManager);
        stage.setX(x);
        stage.setY(y);
        stage.setMinHeight(600);stage.setMinWidth(600);
        stage.show();
    }
    public void createListStage(double x,double y){
        Stage stage=new Stage();
        ListPane listPane = new ListPane(gameManager);
        Scene scene = new Scene(listPane,300,400);
        stage.setScene(scene);
        stage.setTitle("Drawing list");
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }
    @Override
    public void start(Stage stage) throws Exception {
        newStage(stage,100,100);
       // newStage(new Stage(),stage.getX(),stage.getY()+50);
        //newStage(new Stage(),stage.getX(),stage.getY()+100);
        createListStage(stage.getX()+stage.getWidth(),stage.getY());
    }
}
