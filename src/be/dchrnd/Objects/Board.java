package be.dchrnd.Objects;

public class Board
{
    private final Cases[][] board;
    private final int sizeOf;

    public Board(int size)
    {
        sizeOf = size;
        board = new Cases[size][size];
        for (int i=0; i < size; i++)
        {
            for (int j=0; j < size; j++)
                board[j][i] = new Cases();
        }
    }

    public Cases getCase(int x, int y)
    {
        return board[x][y];
    }

    public int getSize()
    {
        return sizeOf;
    }

    public void print()//Testing and debug
    {
        for (int i=0; i < sizeOf; i++)
        {
            for (int j=0; j < sizeOf-1; j++)
                System.out.print(board[j][i]);
            System.out.println(board[i][sizeOf-1]);
        }
    }
}
