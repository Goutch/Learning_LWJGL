package com.engine.entities;

import com.engine.util.Color;
import org.joml.Vector3f;

public class Light extends Entity {
    public static Light main=new Light(Transform.ZERO,Transform.ZERO,new Color(0.5f,0.2f,0.1f,1f));
    public Color color;
    public Light(Vector3f position, Vector3f rotation, Color color) {
        super(position, rotation, 1f);
        this.color=color;
    }
}
