import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.ui.text.TextInterface;
import java.io.IOException;

public class TinyPAcMain {
    public static void main(String [] args) {
        try {
            GameManager gameManager=new GameManager();
            TextInterface textInterface = new TextInterface(gameManager);
            textInterface.createWindow();
            textInterface.startGame();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
