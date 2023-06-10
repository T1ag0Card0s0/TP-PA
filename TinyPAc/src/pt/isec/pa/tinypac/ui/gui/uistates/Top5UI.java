package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.top5.ScoreEntry;

public class Top5UI extends BorderPane {
    private final GameManager gameManager;
    private TableView<ScoreEntry> tableView;

    public Top5UI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {
    }

    public void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    public void update() {
        if (!gameManager.top5IsLoaded() || gameManager.FSM_Is_Created()) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);

    }


}
