package com.engine.entity.light;

import com.engine.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class PointLight extends Light {
    protected float radius=10;
    public PointLight(Vector3f position, Color color, float radius) {
        super(position, new Quaternionf(),color);
        this.radius=10;
    }
}
