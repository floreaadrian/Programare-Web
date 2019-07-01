package space.domain;

public class Civilisation
{
    private int id, homePlanetID, type;
    private String name;

    public Civilisation (int id, String name, int homePlanetID, int type)
    {
        this.id = id;
        this. name = name;
        this.homePlanetID = homePlanetID;
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getHomePlanetID()
    {
        return homePlanetID;
    }

    public void setHomePlanetID(int homePlanetID)
    {
        this.homePlanetID = homePlanetID;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
