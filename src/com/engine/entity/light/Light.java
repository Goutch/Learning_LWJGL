package com.engine.entity.light;


import com.engine.entity.Entity;
import com.engine.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public abstract class Light extends Entity {
    protected Color color=new Color(Color.WHITE);
    public Light(Vector3f position, Quaternionf rotation, Color color) {
        super(position, rotation, 1f);
        this.color.set(color);
    }
    public Color getColor()
    {
        return color;
    }
}
