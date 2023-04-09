package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.ClientElement;
import pt.isec.pa.tinypac.ui.text.TextInterface;


public class AnimateGhosts  implements IGameEngineEvolve {

    private final ClientElement element;
    private final TextInterface textInterface;
    public AnimateGhosts(ClientElement element,TextInterface textInterface ){
        this.textInterface =textInterface;
        this.element=element;
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        int x= element.getxCoord();
        int y=element.getyCoord();
        if(element.move()){
            textInterface.DrawMazeElement(element.getMazeElement(x,y),x,y);
            textInterface.DrawClientMazeElement(element);
        }
    }
}