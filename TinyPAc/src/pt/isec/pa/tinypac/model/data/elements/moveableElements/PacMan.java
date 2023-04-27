package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;

public class PacMan extends MoveableElement  {
    private int[][] wraperCoordinates;
    private int lives;
    private boolean power;
    private int powerTime;
    private int points;
    private int numOfFood;
    public PacMan(int x, int y, Maze maze) {
        super(x, y, 'P',maze);
        this.lives=3;
        this.power=false;
        this.wraperCoordinates=new int[2][2];
        this.points=0;
        this.numOfFood=0;
        this.powerTime=5000;
    }
    public void setLives(int value){this.lives=value;}
    public void setPower(boolean value){this.power=value;}
    public void setWraperCoordinates(int[][]wraperCoordinates){
        this.wraperCoordinates=wraperCoordinates;
    }
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
    public int getLives(){return lives;}
    public boolean getPowerValue(){return power;}
    public int getPoints() {
        return points;
    }
    public int getNumOfFood(){return numOfFood;}
    public int getPowerTime(){return powerTime;}
    public void IdentifyAction(){
        switch (getMazeElementSymbol(getX(),getY())){
            case 'W'-> teleTransport();
            case '.'->{
                setMazeElement(getY(),getX(),null);
                points++;
                numOfFood++;
            }
            case 'O'->{
                if(power)powerTime=powerTime+5000;
                else powerTime=5000;
                setPower(true);
                setMazeElement(getY(),getX(),null);
                points+=10;
                numOfFood++;
            }
        }
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
