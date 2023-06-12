package pt.isec.pa.tinypac.ui.gui.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class ExitAlert {
    private ExitAlert() {}

    public static void show(Window owner) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText(null);
        alert.setContentText("Deseja sair?");

        alert.initOwner(owner);

        ButtonType buttonYes = new ButtonType("Sim");
        ButtonType buttonNo = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonYes) {
                Platform.exit();
            } else {
                alert.close();
            }
        });
    }
}
