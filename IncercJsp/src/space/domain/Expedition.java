package space.domain;

public class Expedition
{
    private int id, astronautId, planetId;
    private float deathProbability;

    public Expedition (int id, int astronautId, int planetId, float deathProbability)
    {
        this.id = id;
        this.astronautId = astronautId;
        this.planetId = planetId;
        this.deathProbability = deathProbability;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAstronautId()
    {
        return astronautId;
    }

    public void setAstronautId(int astronautId)
    {
        this.astronautId = astronautId;
    }

    public int getPlanetId()
    {
        return planetId;
    }

    public void setPlanetId(int planetId)
    {
        this.planetId = planetId;
    }

    public float getDeathProbability()
    {
        return deathProbability;
    }

    public void setDeathProbability(float deathProbability)
    {
        this.deathProbability = deathProbability;
    }
}
