package be.dchrnd;

import be.dchrnd.Objects.Board;
import be.dchrnd.Objects.Cases;

import java.util.Random;

public class Control
{
    /**
     * The all variables of the program
     * Size of the node
     * Size between two nodes
     * Size of the board
     */
    public static int WIDTH  = 50;//node
    public static int HEIGHT = 50;//node
    public static int SPACEX = 3;//space
    public static int SPACEY = 3;//space
    public static int size;//board
    public static String PATH = "/media/dchtrnd_tour/DchtRnd_Data/Informatique/Demineur/src/ressource/bomb.png";
    /**
     * The board of the game (Logic)
     */
    private static Board board;
    /**
     * The random
     */
    private static Random rd = new Random();

    public static void initLogicBoard(int size_,int sizeCase)
    {
        board = new Board(size_);
        size = size_;
        WIDTH = HEIGHT = sizeCase;
    }

    public static void placeBomb(int number)
    {
        int cnt = 0;
        while (cnt < number)
        {
            int randX = random(0,size);
            int randY = random(0,size);

            if (! board.getCase(randX,randY).isOccupied())
            {
                board.getCase(randX,randY).putObject();
                cnt++;
            }
        }
        board.print();//TODO debug
    }

    public static int random(int min, int max)
    {
        int n = rd.nextInt(max);
        if (n >= min && n <= max)
            return n;
        else
            return random(min,max);
    }

    public static Cases getCase(int x, int y)
    {
        return board.getCase(x,y);
    }
}
