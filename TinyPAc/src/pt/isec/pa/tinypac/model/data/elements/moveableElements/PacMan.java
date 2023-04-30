package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;

public class PacMan extends MoveableElement  {
    private final int[][] wraperCoordinates;
    private boolean power;
    private int powerTime;
    private int points;
    private int numOfFood;
    private int numOfGhost;
    public PacMan(MazeInfo maze) {
        super(maze.getInitPacManPosition()[0],maze.getInitPacManPosition()[1],'P',maze);
        this.power=false;
        this.wraperCoordinates= maze.getWrapperCoordinates();
        this.points=0;
        this.numOfFood=0;
        this.powerTime=5000;
        this.numOfGhost=0;
    }
    public void setPower(boolean value){this.power=value;}
    public void setPoints(int points){this.points=points;}
    public void setNumOfFood(int numOfFood){this.numOfFood=numOfFood;}
    private int wichWraperIsPacMan( ){
        for(int i = 0; i<wraperCoordinates.length;i++){
            if(getX()==wraperCoordinates[i][0]&&getY()==wraperCoordinates[i][1]){
                return i;
            }
        }
        return -1;
    }
    private void teleTransport(){
        switch (wichWraperIsPacMan()){
            case 0->{
                setX(wraperCoordinates[1][0]);
                setY(wraperCoordinates[1][1]);
            }
            case 1->{
                setX(wraperCoordinates[0][0]);
                setY(wraperCoordinates[0][1]);
            }
        }
    }
    public boolean getPowerValue(){return power;}
    public int getPoints() {return points;}
    public int getNumOfFood(){return numOfFood;}
    public int getPowerTime(){return powerTime;}
    public void IdentifyAction(){
        if(getUnderElement()==null)return;
        switch (getUnderElement().getSymbol()){
            case 'W'->{ teleTransport();setMazeElement(getUnderElement().getX(),getUnderElement().getY(),getUnderElement());}
            case '.'->{
                setUnderElement(null);
                points++;
                numOfFood++;
            }
            case 'O'->{
                setUnderElement(null);
                if(power)powerTime=powerTime+5000;
                else powerTime=5000;
                setPower(true);
                points+=10;
                numOfFood++;
            }
            case 'F' -> {
                getUnderElement().setSymbol(' ');
                points+=getCurrentLevel()*25;
            }
        }
    }
    public void ateAGhost(){
        if(numOfGhost>=4)numOfGhost=0;
        numOfGhost++;
        points+=numOfGhost*50;
    }
    @Override
    public void checkNeighboors() {
        super.checkNeighboorsWithExtraConstraint('Y');
    }
    @Override
    public void evolve() {
        if(super.move()){
            IdentifyAction();
        }
    }
}
