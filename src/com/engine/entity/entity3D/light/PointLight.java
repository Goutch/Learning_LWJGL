package com.engine.entity.entity3D.light;

import com.engine.util.Color;
import org.joml.Vector3f;

public class PointLight extends Light {
    protected float radius=10;
    public PointLight(Vector3f position, Color color, float radius) {
        super(position, new Vector3f(0,0,0),color);
        this.radius=10;
    }
}
