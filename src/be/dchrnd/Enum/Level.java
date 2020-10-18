package be.dchrnd.Enum;

public enum Level
{
    hard(20),
    medium(15),
    easy(10);

    private int max;

    private Level(int max)
    {
        this.max = max;
    }

    public int getMax()
    {
        return max;
    }
}
