package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;
import pt.isec.pa.tinypac.ui.gui.uistates.BeginUI;
import pt.isec.pa.tinypac.ui.gui.uistates.GameUI;
import pt.isec.pa.tinypac.ui.gui.uistates.Top5UI;


public class RootPane extends BorderPane {
    GameManager gameManager;

    public RootPane(GameManager gameManager){
        this.gameManager=gameManager;
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        StackPane stackPane = new StackPane(
                new BeginUI(gameManager),
                new GameUI(gameManager),
                new Top5UI(gameManager)
        );
        stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setCenter(stackPane);
    }

    private void registerHandlers() {


    }
    private void update() { }

}
