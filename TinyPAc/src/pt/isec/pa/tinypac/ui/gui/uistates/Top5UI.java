package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameManager;

public class Top5UI extends BorderPane {
    GameManager gameManager;

    public Top5UI(GameManager gameManager){
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }
    public void createViews(){

    }
    public void registerHandlers(){

    }
    public void update(){
        this.setVisible(false);
    }

}
