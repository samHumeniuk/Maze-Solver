/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gentrack.maze;

/**
 *
 * @author Sam
 */
public class GentrackMaze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Maze largeMaze = new Maze("large_input.txt");
        largeMaze.printSolvedMaze();
        
        //Maze noSolutionMaze = new Maze("no_solution.txt");
        //noSolutionMaze.printSolvedMaze();
        
        Maze smallMaze = new Maze("small.txt");
        smallMaze.printSolvedMaze();
        
        Maze mediumMaze = new Maze("medium_input.txt");
        mediumMaze.printSolvedMaze();
        
        Maze verySmallMaze = new Maze("input.txt");
        verySmallMaze.printSolvedMaze();
        
    }
    
}
