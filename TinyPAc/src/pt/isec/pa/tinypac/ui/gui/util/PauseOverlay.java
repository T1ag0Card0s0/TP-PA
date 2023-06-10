package pt.isec.pa.tinypac.ui.gui.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;

public class PauseOverlay {
    private PauseOverlay() {}

    public static void show(Window owner, GameManager gameManager) {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);

        BorderPane toastPane = new BorderPane();
        CSSManager.applyCSS(toastPane,"styles.css");
        toastPane.setPrefSize(300, 100);
        toastPane.setStyle("-fx-background-color: #808080;-fx-border-color: blue;-fx-border-radius: 20px;-fx-border-width: 8px;-fx-background-radius: 21px");

        Label titleLabel = new Label("Jogo pausado");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER); // Centralizar o título
        toastPane.setTop(titleLabel);

        Button resumeButton = new Button("Resumir");
        Button saveAndExitButton = new Button("Salvar e Sair");
        resumeButton.setId("btnInitScreen");
        saveAndExitButton.setId("btnInitScreen");
        HBox buttonPane = new HBox(10, resumeButton, saveAndExitButton);
        buttonPane.setPadding(new Insets(10));
        buttonPane.setAlignment(Pos.CENTER);
        toastPane.setCenter(buttonPane);

        TextField nameTextField = new TextField();
        Button okButton = new Button("OK");
        VBox namePane = new VBox(new Label("Nome:"), nameTextField, okButton);
        namePane.setPadding(new Insets(10));
        resumeButton.setId("btnPauseOverlay");
        saveAndExitButton.setId("btnPauseOverlay");
        okButton.setId("btnPauseOverlay");
        saveAndExitButton.setOnAction(event -> {
            buttonPane.getChildren().removeAll(saveAndExitButton, resumeButton);
            buttonPane.getChildren().add(namePane);
            nameTextField.requestFocus();
        });

        okButton.setOnAction(event -> {
            if(nameTextField.getText().length()>0) {
                buttonPane.getChildren().remove(namePane);
                gameManager.addTop5(nameTextField.getText());
                gameManager.saveTop5();
                gameManager.save();
                gameManager.exit();
                popup.hide();
            }
        });
        resumeButton.setOnAction(event->{
            gameManager.resume();
            popup.hide();
        });

        popup.getContent().add(toastPane);
        popup.show(owner);
        Platform.runLater(() -> {
            popup.setX(owner.getX() + (owner.getWidth() - toastPane.getWidth()) / 2);
            popup.setY(owner.getY() + (owner.getHeight() - toastPane.getHeight()) / 2);
        });
    }
}

