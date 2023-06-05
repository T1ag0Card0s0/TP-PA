package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.control.ListView;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;

public class ListPane extends ListView<MoveableElement> {
    private final GameManager gameManager;
    public ListPane(GameManager gameManager){
        this.gameManager=gameManager;
        createViews();
        registerHadlers();
        update();
    }
    public void createViews(){
    }

    public void registerHadlers(){
        gameManager.addPropertyChangeListener( evt -> update());
    }
    public void update(){
        this.getItems().clear();
        if(gameManager.getList()!=null)
            this.getItems().addAll(gameManager.getList());
    }

}
