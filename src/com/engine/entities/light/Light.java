package com.engine.entities.light;

import com.engine.entities.Entity;
import com.engine.util.Color;
import org.joml.Vector3f;

public abstract class Light extends Entity {
    protected Color color;
    public Light(Vector3f position, Vector3f rotation, Color color) {
        super(position, rotation, 1f);
    }
}
