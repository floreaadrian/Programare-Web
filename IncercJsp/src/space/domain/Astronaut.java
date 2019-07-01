package space.domain;

public class Astronaut
{
    private int id, civilisationId;
    private String name;

    public Astronaut (int id, int civilisationId, String name)
    {
        this.id = id;
        this.civilisationId = civilisationId;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCivilisationId()
    {
        return civilisationId;
    }

    public void setCivilisationId(int civilisationId)
    {
        this.civilisationId = civilisationId;
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
