package be.dchrnd.Objects;

public class Cases
{
    private boolean occupied = false;

    public boolean isOccupied()
    {
        return occupied;
    }

    public void putObject()
    {
        occupied = true;
    }

    @Override
    public String toString()
    {
        if (isOccupied())
            return "[X]";
        else
            return "[_]";
    }
}
