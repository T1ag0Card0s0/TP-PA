
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.ui.text.TextInterface;
public class TinyPAc{
    public static void main(String[] args) {
        GameContext game=new GameContext();
        TextInterface textInterface=new TextInterface(game);
        textInterface.start();
    }
}