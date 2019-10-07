package com.engine.entity.light;


import com.engine.entity.Entity;
import com.engine.util.Color;
import org.joml.Vector3f;

public abstract class Light extends Entity {
    protected Color color=Color.WHITE;
    public Light(Vector3f position, Vector3f rotation, Color color) {
        super(position, rotation, 1f);
        this.color=color;
    }
    public Color getColor()
    {
        return color;
    }
}
