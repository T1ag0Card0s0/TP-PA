import javafx.application.Application;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.gui.MainJFX;
import pt.isec.pa.tinypac.ui.text.TextInterface;

public class TinyPAc {
    public static void main(String[] args) {
        Application.launch(MainJFX.class,args);

        /*GameContext game = new GameContext();
        TextInterface ui = new TextInterface(game);
        ui.createWindow();
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g,t) -> {
            double seconds=t/ 1_000_000_000.0;
            if (!game.evolve(seconds))//enviar o t de tempo em segundos
                g.stop();
        });
        gameEngine.registerClient((g,t) -> ui.show() );
        gameEngine.start(100);
        ui.readKeyBoard();
        gameEngine.waitForTheEnd();
        gameEngine.stop();*/

    }
}