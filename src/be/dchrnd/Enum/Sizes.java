package be.dchrnd.Enum;

public enum Sizes
{
    Ten(10,70),
    Hundred(100,10);


    final int sizeOf;
    final int sizeCase;

    private Sizes(int sizeOf,int sizeCase)
    {
        this.sizeOf = sizeOf;
        this.sizeCase = sizeCase;
    }

    public int getSize()
    {
        return sizeOf;
    }

    public int getSizeCase()
    {
        return sizeCase;
    }

    @Override
    public String toString()
    {
        return sizeOf + "x" + sizeOf;
    }
}
