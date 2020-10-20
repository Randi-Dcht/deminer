package be.dchrnd;

import be.dchrnd.Objects.Board;
import be.dchrnd.Objects.Cases;
import java.util.Random;



public class Control
{
    private static Control me = null;
    public  static int WIDTH  = 50;//nodeSize
    public  static int HEIGHT = 50;//node
    public  static int SPACEX = 3;//space
    public  static int SPACEY = 3;//space

    public static Control getInstance()
    {
        if (me == null)
            me = new Control();
        return me;
    }

    /**
     * The all variables of the program
     * Size of the node
     * Size between two nodes
     * Size of the board
     */
    public int size;//board
    public String PATH = "src/ressource/bomb.png";//TODO
    private Board board;

    private static Random rd = new Random();

    private Control(){}

    public void initLogicBoard(int size_,int sizeCase)
    {
        board = new Board(size_);
        size = size_;
        WIDTH = HEIGHT = sizeCase;
    }

    public void setPath(String PATH)
    {
        this.PATH = PATH;
    }

    public String getPath()
    {
        return PATH;
    }

    public int getNumberCases()
    {
        return size;
    }

    public void placeBomb(int number)
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
    }

    public void debug()
    {
        board.print();
    }

    public static int random(int min, int max)
    {
        int n = rd.nextInt(max);
        if (n >= min && n <= max)
            return n;
        else
            return random(min,max);
    }

    public Cases getCase(int x, int y)
    {
        return board.getCase(x,y);
    }
}
