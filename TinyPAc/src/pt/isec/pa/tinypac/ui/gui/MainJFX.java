package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.GameManager;

public class MainJFX extends Application {
    GameManager gameManager;
    GameEngine gameEngine;

    @Override
    public void init() throws Exception {
        super.init();
        gameManager=new GameManager();
        gameEngine = new GameEngine();
    }
    private void newStage(Stage stage,String title,double x,double y){
        RootPane root = new RootPane(gameManager);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        stage.setScene(new Scene(root,600,600));
        stage.setTitle(title);
        stage.setUserData(gameManager);
        stage.setX(x);
        stage.setY(y);
        stage.setMinWidth(600);
        stage.setMinHeight(700);
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
        newStage(stage,"TinyPac",100,100);
        newStage(new Stage(),"TinyPac#1",stage.getX(),stage.getY()+50);
        newStage(new Stage(),"TinyPac#2",stage.getX(),stage.getY()+100);
        createListStage(stage.getX()+stage.getWidth(),stage.getY());
        gameEngine.registerClient((g,t)-> Platform.runLater(()->{
            gameManager.evolve(t/1_000_000_000.0);
        }));
        gameEngine.start(200);
    }

    @Override
    public void stop() throws Exception {
        gameEngine.stop();
        gameEngine.waitForTheEnd();
    }
}
