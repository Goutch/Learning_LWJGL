package com.engine.examples.voxelEngine;

import com.engine.util.Color;
import org.lwjgl.system.CallbackI;

public class Block {
    public static final Block GRASS=new Block(new Color(0.2f,0.8f,0));
    public static final Block DIRT=new Block(new Color(0.5f,0.5f,0));
    public static final Block STONE=new Block(new Color(0.5f,0.5f,0.5f,1));
    public Color color;
    public Block(Color color)
    {
        this.color=color;
    }
}
