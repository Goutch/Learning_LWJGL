package com.engine.entities;

import com.engine.util.Color;
import org.joml.Vector3f;

public class Light extends Entity {
    public static Light main=new Light(Transform.ZERO,Transform.ZERO,Color.WHITE);
    public Color color;
    public Light(Vector3f position, Vector3f rotation, Color color) {
        super(position, rotation, 1f);
        this.color=color;
    }
}
