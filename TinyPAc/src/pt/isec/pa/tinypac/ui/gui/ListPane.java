package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.control.ListView;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;

public class ListPane extends ListView<String> {
    private final GameManager gameManager;
    public ListPane(GameManager gameManager){
        this.gameManager=gameManager;
        createViews();
        registerHadlers();
        update();
    }
    public void createViews(){}
    public void registerHadlers(){
        gameManager.addPropertyChangeListener( evt -> update());
    }
    public void update(){
        this.getItems().clear();
        this.getItems().addAll(ModelLog.getInstance().getLog());
    }

}
