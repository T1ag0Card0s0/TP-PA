import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;

class TestClient implements IGameEngineEvolve{
    int count = 0;

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        System.out.printf("[%d] %d\n",currentTime,++count);
        if(count>=20)gameEngine.stop();
    }
}
public class TinyPAcMain {
    public static void main(String [] args){
        IGameEngine gameEngine=new GameEngine();
        TestClient client= new TestClient();
        gameEngine.registerClient(client);
        gameEngine.start(300);
        gameEngine.waitForTheEnd();
    }
}
