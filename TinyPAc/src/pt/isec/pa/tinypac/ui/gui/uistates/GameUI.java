package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.GameEngineState;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.util.GamePausedMenu;

public class GameUI extends BorderPane {
    GameManager gameManager;
    GridPane gridPane;
    Label lblPts,lblLives,lblLevel;
    Button btnPause;
    HBox barraInformacao;
    String pngFile;
    public GameUI(GameManager gameManager ){
        this.gameManager = gameManager;
        this.lblLevel=new Label();
        this.lblLives=new Label();
        this.lblPts=new Label();
        this.gridPane=new GridPane();
        this.btnPause = new Button();
        this.barraInformacao=new HBox();
        this.pngFile="pacman-left.png";
        createViews();
        registerHandlers();
        update();
    }
    public void createViews() {
        ImageView imageView = new ImageView(ImageManager.getImage("pause-button.png"));
        imageView.setFitHeight(45);
        imageView.setFitWidth(50);
        btnPause.setGraphic(imageView);
        btnPause.setId("pause-button");
        // Criação das Labels
        lblLives.setText("Vidas: ");
        lblPts.setText("Pontos: ");
        lblLevel.setText("Nivel: ");
        lblLives.setId("label_gameInfo");lblLevel.setId("label_gameInfo");lblPts.setId("label_gameInfo");
        gridPane.setAlignment(Pos.CENTER);

        // Criação da VBox e adição das Labels
        VBox vbox = new VBox(btnPause,lblLevel, lblPts, lblLives);
        HBox hBox = new HBox(gridPane,vbox);

        hBox.setAlignment(Pos.CENTER);
        this.setCenter(hBox);

    }

    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnPause.setOnAction(evt->{
            gameManager.pause();
            GamePausedMenu.show(getScene().getWindow());
        });
        setOnKeyPressed(KeyEvent->{
            switch (KeyEvent.getCode()){
                case UP ->  gameManager.Up();
                case DOWN -> gameManager.Down();
                case LEFT ->gameManager.Left();
                case RIGHT -> gameManager.Right();
                case SPACE ->gameManager.pause();
            }
        });

    }
    public void update() {
        if (!gameManager.FSM_Is_Created()) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);
        requestFocus();
        char[][] board = gameManager.getBoard();
        gridPane.getChildren().clear(); // Clear existing nodes

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ImageView imgView = getOrCreateImageView(board[i][j]);
                setDimensions(imgView, board[i][j]);
                GridPane.setHalignment(imgView, Pos.CENTER.getHpos());
                GridPane.setValignment(imgView, Pos.CENTER.getVpos());
                gridPane.add(imgView, j, i);
            }
        }
        lblLevel.setText("Nivel: "+gameManager.getLevel());
        lblPts.setText("Pontos: "+gameManager.getPoints());
        lblLives.setText("Vidas: "+gameManager.getLives());
    }

    private ImageView getOrCreateImageView(char c) {
        String filename;
        if (c == 'P') {
            filename = switch (gameManager.getDirection()) {
                case 0 -> pngFile="pacman-up.png";
                case 1 -> pngFile="pacman-right.png";
                case 2 -> pngFile="pacman-down.png";
                case 3 -> pngFile="pacman-left.png";
                default -> pngFile;
            };
        }else filename = getImageFilename(c);

        Image image = ImageManager.getImage(filename);
        ImageView imgView;
        if (image == null) {
            imgView = new ImageView(ImageManager.getExternalImage(filename));
        } else {
            imgView = new ImageView(image);
        }

        return imgView;
    }

    private void setDimensions(ImageView imgView,char c){
        switch (c){
            case 'x'->{imgView.setFitWidth(18);imgView.setFitHeight(18);}
            case '.'->{imgView.setFitWidth(7);imgView.setFitHeight(7);}
            default -> {imgView.setFitWidth(15);imgView.setFitHeight(15);}
        }
    }
    private String getImageFilename(char c) {
        return switch (c) {
            case 'P' -> "pacman.png";
            case 'p', 'i', 'c', 'b' -> gameManager.elementVulnerable(c) ? "vulnerable-ghost.png" :
                    switch (c) {
                        case 'p' -> "pinky.png";
                        case 'i' -> "inky.png";
                        case 'c' -> "clyde.png";
                        case 'b' -> "blinky.png";
                        default -> "empty.png";
                    };
            case 'F' -> "fruit.png";
            case '.', 'O' -> "food.png";
            case 'x' -> "wall.png";
            case 'W' -> "portal.png";
            default -> "empty.png";
        };
    }


}
