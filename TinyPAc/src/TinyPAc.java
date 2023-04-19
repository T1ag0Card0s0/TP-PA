import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.ui.text.TextInterface;

public class TinyPAc{
    public static void main(String[] args) {
        GameEngine gameEngine=new GameEngine();
        GameContext game=new GameContext();
        gameEngine.registerClient(game);
        gameEngine.start(300);
        TextInterface textInterface=new TextInterface(game);
        textInterface.start();
        gameEngine.waitForTheEnd();

    }
}