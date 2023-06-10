package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.moveableElements.pacman.PacMan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private int ticks;
    private MazeInfo mazeInfo;
    private int level;
    private int points;
    private int lives;
    private int eatenFood;
    private double timeRegister;
    private char pacManAteSymbol;
    public Game(){
        this.ticks=0;
        this.level=1;
        this.points=0;
        this.lives=3;
        this.eatenFood=0;
        this.timeRegister=0;
        initMazeInfo();
    }
    void initMazeInfo(){
        int width = 0, height = 0;
        try {
            File file = new File(getLevelFilePath());
            if(!file.exists()){
                level--;
                file=new File(getLevelFilePath());
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                width=line.length();
                height++;
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        mazeInfo = new MazeInfo(height,width);
        try {
            File file = new File(getLevelFilePath());
            if(!file.exists()){
                level--;
                file=new File(getLevelFilePath());
            }
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int c;
            int x = 0, y = 0;
            while ((c = br.read()) != -1) {
                if((char)c!='\n')
                    mazeInfo.setInitElements(new Element((char) c,y,x));
                x++;
                if ((char) c == '\n') {
                    y++;
                    x = 0;
                }
            }
            br.close();
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private String getLevelFilePath(){
        if(level<10)return "Levels\\Level0"+level+".txt";
        return "Levels\\Level"+level+".txt";
    }
    public char[][]getBoard(){return mazeInfo.getBoard();}
    public void moveElements(double time){
        for(MoveableElement element: mazeInfo.getMoveableElements()){
            if(element.getUnderElement()!=null)
                mazeInfo.set(element.getUnderElement().getY(),element.getUnderElement().getX(),element.getUnderElement());
            else
                mazeInfo.set(element.getY(),element.getX(),null);
            element.move();
            pacManAteSymbol=mazeInfo.pacManAte();
            mazeInfo.set(element.getY(),element.getX(),element);
            if(element instanceof PacMan pacMan) {
                switch (pacManAteSymbol) {
                    case 'F' -> points += level * 25;
                    case '.' ->{
                        eatenFood++;
                        points++;
                        pacMan.setUnderElement(null);
                    }
                    case 'b','c','i','p'->{
                        if(pacMan.getPower()) points += mazeInfo.getNumOfEatenGhost() * 50;
                        else lives--;
                    }
                    case 'O' ->{
                        eatenFood++;
                        timeRegister=time;
                        mazeInfo.setVulnerable(true);
                        pacMan.setPower(true);
                        points += 10;
                        pacMan.setUnderElement(null);
                    }
                    case 'W' -> mazeInfo.teleTransportPacMan();
                }
                if(pacMan.getPower()&&time-timeRegister>5.0){
                    mazeInfo.setVulnerable(false);
                    pacMan.setPower(false);
                }
            }
            if(element instanceof Ghost ghost){
                if(ghost.getLocked()&&time-timeRegister>5.0){
                    ghost.setLocked(false);
                }
                if(pacmanHasPower()&&pacManAteSymbol==ghost.getSymbol())
                        points += mazeInfo.getNumOfEatenGhost() * 50;
                else if(pacManAteSymbol==ghost.getSymbol()) {
                    lives--;
                }

            }
        }
        if(timeRegister==0)
            timeRegister=time;
        ticks++;
    }
    public boolean levelEnded(){
        if(mazeInfo.getNumOfFood()-eatenFood==0){
            if(level<20) {
                level++;
                ticks = 0; eatenFood = 0;
                initMazeInfo();
            }
            return true;
        }
        if(!mazeInfo.getPacManPower()&&
             (pacManAteSymbol=='c'||pacManAteSymbol=='p'||
                     pacManAteSymbol=='b'||pacManAteSymbol=='i')&&lives>0) {
                //mazeInfo.InitElemPos();
                return true;
        }
        return false;
    }
    public boolean pacmanHasPower(){return mazeInfo.getPacManPower();}
    public boolean elementVulnerable(char c){

        if(mazeInfo.getMoveableElement(c) instanceof Ghost ghost){
            return ghost.getVulnerable();
        }
        return false;
    }
    public int getTicks() {return ticks;}
    public int getLevel() {return level;}
    public int getPoints(){return points;}
    public double getTimeRegister(){return timeRegister;}
    public int getLives(){return lives;}
    public int getDirection(){return mazeInfo.getPacManDirection();}
    public void setTicks(int ticks) {this.ticks = ticks;}
    public void setLevel(int level) {this.level = level;}
    public void setNextDirection(int nextDirection){mazeInfo.setPacManNextDirection(nextDirection);}
    public void setTimeRegister(long time){this.timeRegister=time;}

}
