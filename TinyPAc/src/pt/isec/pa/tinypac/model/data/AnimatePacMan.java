package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;
import pt.isec.pa.tinypac.ui.text.TextInterface;


public class AnimatePacMan  implements IGameEngineEvolve {
    private final int numOfFood;
    private final PacMan pacMan;
    private final TextInterface textInterface;
    public AnimatePacMan(PacMan pacMan,TextInterface textInterface,int numOfFood){
        this.textInterface =textInterface;
        this.pacMan=pacMan;
        this.numOfFood=numOfFood;
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        int x= pacMan.getxCoord();
        int y=pacMan.getyCoord();
        if(pacMan.move()){
            textInterface.DrawMazeElement(pacMan.getMazeElement(x,y),x,y);
            textInterface.DrawClientMazeElement(pacMan);
            if(pacMan.getNumOfFoodEaten()==numOfFood)gameEngine.pause();
        }


    }
}