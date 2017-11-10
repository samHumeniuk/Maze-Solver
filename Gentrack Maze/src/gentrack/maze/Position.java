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
public class Position {
    
    private int xPosition;
    private int yPosition;
    
    public Position(int xPosition, int yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    public int getXPosition()
    {
        return xPosition;
    }
    
    public int getYPosition()
    {
        return yPosition;
    }
    public Position goNorth()
    {
        yPosition--;
        return this;
    }
    public Position goSouth()
    {
        yPosition++;
        return this;
    }
    public Position goEast()
    {
        xPosition++;
        return this;
    }
    public Position goWest()
    {
        xPosition--;
        return this;
    }
    
}
