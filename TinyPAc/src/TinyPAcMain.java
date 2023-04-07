import pt.isec.pa.tinypac.ui.text.TextInterface;
import java.io.IOException;

public class TinyPAcMain {
    public static void main(String [] args) {
        try {
            TextInterface textInterface = new TextInterface();
            textInterface.createWindow();
            textInterface.startGame();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
