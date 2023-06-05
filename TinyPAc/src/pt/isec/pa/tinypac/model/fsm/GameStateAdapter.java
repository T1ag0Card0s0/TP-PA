package pt.isec.pa.tinypac.model.fsm;


import pt.isec.pa.tinypac.model.data.game.Game;

public class GameStateAdapter implements IGameState{
    protected Game game;
    protected GameContext context;

    protected void changeState(EGameState eState){
        context.changeState(eState.createState(context,game));
    }

    public GameStateAdapter(GameContext context,Game game){
        this.game=game;
        this.context=context;
    }
    @Override
    public EGameState getGameState() {
        return null;
    }

    @Override
    public void Up() {

    }

    @Override
    public void Down() {

    }

    @Override
    public void Left() {

    }

    @Override
    public void Right() {

    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean resume() {
        return false;
    }

    @Override
    public boolean evolve(double time) {
        return false;
    }
}
