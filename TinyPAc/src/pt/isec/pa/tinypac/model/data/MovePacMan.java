package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.GameEngineState;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.EDirections;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;
import pt.isec.pa.tinypac.ui.text.TextInterface;

public class MovePacMan implements IGameEngineEvolve {
    private final PacMan pacMan;
    private final TextInterface textInterface;
    public MovePacMan(PacMan pacMan, TextInterface textInterface){
        this.pacMan=pacMan;
        this.textInterface=textInterface;
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        //System.out.printf("\nPacman Antes de mover %d,%d\t",pacMan.getxCoord(),pacMan.getyCoord());
            switch (textInterface.ReadArrow()) {
                case "ArrowUp" -> pacMan.setNexDirection(EDirections.UP);
                case "ArrowDown" -> pacMan.setNexDirection(EDirections.DOWN);
                case "ArrowLeft" -> pacMan.setNexDirection(EDirections.LEFT);
                case "ArrowRight" -> pacMan.setNexDirection(EDirections.RIGHT);
                case "Escape" -> gameEngine.stop();
            }
      //  System.out.printf("Pacman Depois de mover %d,%d\n",pacMan.getxCoord(),pacMan.getyCoord());

    }
}
