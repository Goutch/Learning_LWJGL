package com.engine.entity.entity3D.light;


import com.engine.entity.entity3D.Entity3D;
import com.engine.util.Color;
import org.joml.Vector3f;

public abstract class Light extends Entity3D {
    protected Color color;
    public Light(Vector3f position, Vector3f rotation, Color color) {
        super(position, rotation, 1f);
    }
}
