package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.util.InsertNamePopup;
import pt.isec.pa.tinypac.ui.gui.util.PausePopup;

public class GameUI extends BorderPane {
    GameManager gameManager;
    GridPane gridPane;
    Label lblPts,lblLevel,lblLives;
    Button btnPause;
    HBox hBoxTop,hBoxBottom;
    HBox hBoxLives;
    String pngFile;
    public GameUI(GameManager gameManager ){
        this.gameManager = gameManager;
        this.pngFile="pacman-left.png";

        createViews();
        registerHandlers();
        update();
    }
    public void createViews() {
        this.lblLevel = new Label();
        this.lblPts = new Label();
        this.lblLives = new Label("Vidas:");
        this.gridPane = new GridPane();
        this.btnPause = new Button();
        this.hBoxLives = new HBox();
        ImageView imageView = new ImageView(ImageManager.getImage("pause-button.png"));
        imageView.setFitHeight(45);
        imageView.setFitWidth(50);
        btnPause.setGraphic(imageView);
        btnPause.setId("pause-button");

        hBoxLives.setSpacing(5);

        lblPts.setText("Pontos: ");
        lblLevel.setText("Nivel: ");
        lblLevel.setId("label_gameInfo");
        lblPts.setId("label_gameInfo");
        lblLives.setId("label_gameInfo");
        gridPane.setAlignment(Pos.CENTER);
        VBox vBoxTopLabels = new VBox(lblLevel, lblPts);
        vBoxTopLabels.setAlignment(Pos.CENTER); // Alinhar à margem esquerda da janela
        vBoxTopLabels.setPadding(new Insets(10));
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        region.setMinWidth(10); // Adjust the width as needed

        hBoxTop = new HBox(vBoxTopLabels,region, btnPause);
        hBoxTop.setAlignment(Pos.CENTER);
        hBoxBottom = new HBox(hBoxLives);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBoxTop, gridPane, hBoxBottom);
        hBoxTop.setAlignment(Pos.CENTER);
        hBoxBottom.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        this.setCenter(vBox);
    }

    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt ->{
            update();
        });
        btnPause.setOnAction(evt->{
            gameManager.pause();
            PausePopup.show(getScene().getWindow(),gameManager);
        });
        setOnKeyPressed(KeyEvent->{
            switch (KeyEvent.getCode()){
                case UP ->  gameManager.Up();
                case DOWN -> gameManager.Down();
                case LEFT ->gameManager.Left();
                case RIGHT -> gameManager.Right();
                case SPACE ->{
                    if(gameManager.pause()){
                        PausePopup.show(getScene().getWindow(),gameManager);
                    }else{
                        gameManager.resume();
                    }
                }
            }
        });

    }
    public void update() {
        if (!gameManager.FSM_Is_Created()) {
            this.setVisible(false);
            return;
        }
        if(gameManager.lostGame()){
            gameManager.pause();
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
        hBoxLives.getChildren().clear();

        hBoxLives.getChildren().add(lblLives);
        for(int i = 0; i<gameManager.getLives();i++){
            ImageView imageView = new ImageView(ImageManager.getImage("pacman-left.png"));
            imageView.setFitWidth(15);imageView.setFitHeight(15);
            hBoxLives.getChildren().add(imageView);
        }
        hBoxTop.setPrefWidth(gridPane.getWidth());
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
