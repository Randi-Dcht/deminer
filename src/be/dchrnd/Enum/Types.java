package be.dchrnd.Enum;

public enum Types
{
    Normal("normal","bomb.png"),
    Prof("Mens","x.png");

    private String name;
    private String img;

    private Types(String name, String picture)
    {
        this.name = name;
        img       = picture;
    }

    public String getImg()
    {
        return img;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
