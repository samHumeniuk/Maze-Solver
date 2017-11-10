/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gentrack.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
public class Maze {
    
    private int mazeHeight;
    private int mazeWidth;
    
    private Position startPosition;
    private Position endPosition;
    
    private static final String inputWall = "1";
    private static final String inputNoWall = "0";
    
    private static final String outputWall = "#";
    private static final String outputNoWall = " ";
    private static final String start = "S";
    private static final String end = "E";
    private static final String path = "X";
    
    private String[][] mazeArray;
    
    
    public Maze(String fileName)
    {
        try {
            String currentLine;
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            String[] wordsOnLine;

            currentLine = bufferedReader.readLine();
            wordsOnLine = currentLine.split(" ");
            mazeWidth = Integer.parseInt(wordsOnLine[0]);
            mazeHeight = Integer.parseInt(wordsOnLine[1]);
            
            currentLine = bufferedReader.readLine();
            wordsOnLine = currentLine.split(" ");
            startPosition = new Position(Integer.parseInt(wordsOnLine[0]),Integer.parseInt(wordsOnLine[1]));
            
            currentLine = bufferedReader.readLine();
            wordsOnLine = currentLine.split(" ");
            endPosition = new Position(Integer.parseInt(wordsOnLine[0]),Integer.parseInt(wordsOnLine[1]));
                
            mazeArray = new String[mazeHeight][mazeWidth];
            
            int yPosition = 0;
            while((currentLine = bufferedReader.readLine()) != null) 
            {
                wordsOnLine = currentLine.split(" ");
                mazeArray[yPosition] = wordsOnLine;
                yPosition ++;
            }   

            bufferedReader.close();  
        } catch (IOException ex ) {
            Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void markPosition(String character, Position position)
    {
        mazeArray[position.getYPosition()][position.getXPosition()] = character;
    }
    
    private void calculatePath()
    {
        if(!explore(startPosition, false))
        {
            System.out.println("No Solution was found");
            System.out.println("");
        }
    }
    
    private boolean explore(Position currentPosition, boolean foundEnd)
    {
        if(!foundEnd)
        {  
            String positionTerrain = mazeArray[currentPosition.getYPosition()] [currentPosition.getXPosition()];

            switch (positionTerrain) 
            {
                case inputWall:  return foundEnd;
                case path:  return foundEnd;
                case end:  return true;
                case inputNoWall:  markPosition(path, currentPosition);
            }

            //printMaze();
            foundEnd = explore(currentPosition.goNorth(), foundEnd);
            currentPosition.goSouth();
            foundEnd = explore(currentPosition.goEast(), foundEnd);
            currentPosition.goWest();
            foundEnd = explore(currentPosition.goSouth(), foundEnd);
            currentPosition.goNorth();
            foundEnd = explore(currentPosition.goWest(), foundEnd);
            currentPosition.goEast();
            
        }
        if(!foundEnd)
        {
            markPosition(inputNoWall, currentPosition);
        }
        return foundEnd;
    }
    
    public void printSolvedMaze()
    {
        markPosition(end, endPosition);
        calculatePath();
        markPosition(start, startPosition);
        printMaze();
    }
    
    private void printMaze()
    {
        for(String[] line : mazeArray)
        {
            StringBuilder builder = new StringBuilder();
            for (String word : line)
            {
                String outputChar;
                switch (word) 
                {
                    case inputNoWall:  outputChar = outputNoWall;
                               break;
                    case inputWall:  outputChar = outputWall;
                               break;
                    default:   outputChar = word;
                               break;
                }
                builder.append(outputChar);
            }
            System.out.println(builder.toString());
        }
        System.out.println("");
    }
    
}
