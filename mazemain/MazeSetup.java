/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazemain;

import java.util.Scanner;

/**
 *
 * @author melodytribble
 */

public class MazeSetup {
    String facing;
    boolean northOpen, southOpen, westOpen, eastOpen = false;
    Scanner user_input = new Scanner( System.in );
    
    public MazeSetup(char[][] array){    
    }
    
    public void print(char[][] myMaze){
        String output="";
        for(int y=0; y< myMaze.length; y++){
            output+="\n";
            for(int x=0; x<myMaze[y].length; x++){
                output+= myMaze[y][x];
            }
        }
       System.out.println(output);
    }
    
    public String run(char [][]maze, int xLoc, int yLoc, int handLocationX, int handLocationY){//recursive method
 
        if(maze[yLoc][xLoc]=='F'){
            return "Finished!";
        }
        else {
            facing = findDirection(xLoc, yLoc, handLocationX, handLocationY); //determines direction we are facing
        
            findOpening(maze, xLoc, yLoc, handLocationX, handLocationY);//determines if the direction we are facing is open
            
            if (facing.equals("East")){//FACING EAST

                if(eastOpen){//moves and faces EAST
                    placeX(maze, xLoc, yLoc);//places an X where I was
                    xLoc++;                  //moves forward 1
                    handLocationX++;
                    if(maze[yLoc][xLoc]!='F'){
                        placeO(maze, xLoc, yLoc);//places an O where I am now
                    }
                }
                else if(southOpen){//moves and rotates to face SOUTH
                    placeX(maze, xLoc, yLoc);
                    yLoc++;
                    handLocationX--;
                    if(maze[yLoc][xLoc]!='F'){
                        placeO(maze, xLoc, yLoc);//places an O where I am now
                    }                   
                }
                else{//rotates to the north
                    handLocationY--;
                    handLocationX++;
                }
            print(maze); 
        

            }
            else if(facing.equals("North")){//FACING NORTH

                if(eastOpen){//moves and rotates to face EAST
                    placeX(maze, xLoc, yLoc);
                    xLoc++;
                    handLocationY++;
                    if(maze[yLoc][xLoc]!='F'){
                        placeO(maze, xLoc, yLoc);//places an O where I am now
                    }
                }
                else if(northOpen){//MOVE NORTH
                    placeX(maze, xLoc, yLoc);
                    yLoc--;
                    handLocationY--;
                    if(maze[yLoc][xLoc]!='F'){
                        placeO(maze, xLoc, yLoc);//places an O where I am now
                    }
                }           
                else{//rotates to the west
                    handLocationX--;
                    handLocationY--;

                }
            print(maze); 


            }
            else if (facing.equals("South")){//FACING SOUTH

                    if(southOpen){//MOVES SOUTH
                        placeX(maze, xLoc, yLoc);//places an X where I was
                        yLoc++;                  //moves forward 1
                        handLocationY++;
                        if(maze[yLoc][xLoc]!='F'){
                            placeO(maze, xLoc, yLoc);//places an O where I am now
                        }
                    }
                    else if(westOpen){//moves and rotates to face WEST
                        placeX(maze, xLoc, yLoc);
                        xLoc--;
                        handLocationY--;
                        if(maze[yLoc][xLoc]!='F'){
                            placeO(maze, xLoc, yLoc);//places an O where I am now
                        }                  
                    }
                    else{//rotates to the EAST
                        handLocationX++;
                        handLocationY++;
                    }
                print(maze); 


            }
            else if (facing.equals("West")){//FACING WEST

                    if(westOpen){//MOVES WEST
                        placeX(maze, xLoc, yLoc);//places an X where I was
                        xLoc--;                  //moves forward 1
                        handLocationX--;
                        if(maze[yLoc][xLoc]!='F'){
                            placeO(maze, xLoc, yLoc);//places an O where I am now
                        }
                    }
                    else if(northOpen){//moves and rotates to face NORTH
                        placeX(maze, xLoc, yLoc);
                        yLoc--;
                        handLocationX++;
                        if(maze[yLoc][xLoc]!='F'){
                            placeO(maze, xLoc, yLoc);//places an O where I am now
                        }                    
                    }
                    else{//rotates to the SOUTH
                        handLocationX--;
                        handLocationY++;
                    }
                print(maze); 


            }
        }
        return run(maze, xLoc, yLoc, handLocationX, handLocationY);  
    }
    
    public char getData(char data){//returns the value in a location of the maze
        return data;
    }
    
    public char[][] placeX(char[][] array, int x, int y){//places an X value
        array[y][x] = 'X';
        return array;
    }
    
    public char[][] placeO(char[][] array, int x, int y){//places an O value
        array[y][x] = 'O';
        return array;
    }
    
    public String findDirection(int xLoc, int yLoc, int handLocationX, int handLocationY){
        String facing;
        if(xLoc == handLocationX && handLocationY > yLoc ){
            facing = "East";
        }else if(xLoc ==handLocationX && handLocationY < yLoc){
            facing = "West";
        }else if(yLoc == handLocationY && xLoc > handLocationX) {
            facing = "South";
        }else{ // facing North
            facing = "North";
        }
        return facing;
    }
    
    public void findOpening(char [][] maze, int xLoc, int yLoc, int handLocationX, int handLocationY){
        northOpen = false;
        southOpen = false;
        westOpen = false; 
        eastOpen = false;
        
        if (facing.equals("East")){//facing EAST
            
            if(maze[handLocationY][handLocationX]=='.'||maze[handLocationY][handLocationX]=='X'||maze[handLocationY][handLocationX]=='F'){//open to South
                southOpen=true;
            }
            else if(maze[yLoc][xLoc+1]=='.'||maze[yLoc][xLoc+1]=='X'||maze[yLoc][xLoc+1]=='F'){//EAST path in front of us is clear
                eastOpen= true;               
            }
            else {//must turn to the North
                northOpen = true; 
            }
        }
        else if(facing.equals("North")){//facing NORTH
            
            if(maze[handLocationY][handLocationX]=='.'||maze[handLocationY][handLocationX]=='X'||maze[handLocationY][handLocationX]=='F'){//open to the EAST
                eastOpen = true; 
            }
            else if(maze[yLoc-1][xLoc]=='.'||maze[yLoc-1][xLoc]=='X'||maze[yLoc-1][xLoc]=='F'){//North is clear to take
                northOpen = true;
            }
            else{//must turn west
                westOpen = true;
            }
        }
        else if(facing.equals("South")){//facing SOUTH
            
            if(maze[handLocationY][handLocationX]=='.'||maze[handLocationY][handLocationX]=='X'||maze[handLocationY][handLocationX]=='F'){//open to the WEST
                westOpen = true; 
            }
            else if(maze[yLoc+1][xLoc]=='.'||maze[yLoc+1][xLoc]=='X'||maze[yLoc+1][xLoc]=='F'){//SOUTH is clear to take
                southOpen = true;
                
            }
            else{//must turn EAST
                eastOpen = true;
            }
        }
        else{//facing WEST
            if(maze[handLocationY][handLocationX]=='.'||maze[handLocationY][handLocationX]=='X'||maze[handLocationY][handLocationX]=='F'){//open to the North
                northOpen = true; 
            }
            else if(maze[yLoc][xLoc-1]=='.'||maze[yLoc][xLoc-1]=='X'||maze[yLoc][xLoc-1]=='F'){//WEST path in front of us is clear
                westOpen= true;
            }
            else{ //must turn to the South
                southOpen = true; 
            }       
        }
    }
}
    