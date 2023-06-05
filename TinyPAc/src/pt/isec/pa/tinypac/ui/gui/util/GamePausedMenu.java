package pt.isec.pa.tinypac.ui.gui.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;

import java.util.Timer;
import java.util.TimerTask;

public class GamePausedMenu {
    private GamePausedMenu() {}

    public static void show(Window owner) {
        final Popup popup = new Popup();
        Button btnResume = new Button("Resumir");
        Button btnExitSave = new Button("Salvar e sair");
        Label lbl = new Label("Jogo Pausado");
        lbl.setAlignment(Pos.CENTER);
        popup.setAutoHide(true);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_BOTTOM_LEFT);
        double x = owner.getX();
        double y = owner.getY();
        double w = owner.getWidth();
        double h = owner.getHeight();

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(
                new BackgroundFill(Color.color(0.5, 0.5, 0.5),
                        new CornerRadii(5), Insets.EMPTY))
        );
        VBox vBox = new VBox(10);
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(btnResume, btnExitSave);
        vBox.getChildren().addAll(lbl, buttonsBox);
        pane.setTop(vBox); // Define vBox como o componente do topo do BorderPane

        popup.getContent().addAll(pane);
        popup.show(owner, x + 0.25 * w, y + 0.50 * h);

        lbl.setMinWidth(300);
        btnResume.setOnAction(actionEvent -> {
            Platform.runLater(popup::hide);
        });
        btnExitSave.setOnAction(actionEvent -> {
            Platform.runLater(popup::hide);
        });
    }


}
