package com.example.tictacgame;

public class Player
{
    private String name;
    private int srcImageId;
    private int id;

    public Player(String name, int srcImageId)
    {
        this.name = name;
        this.srcImageId = srcImageId;
    }

    public int getSrcImageId()
    {
        return srcImageId;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return this.hashCode() + srcImageId;
    }
}
