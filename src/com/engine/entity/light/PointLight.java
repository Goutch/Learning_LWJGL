package com.engine.entity.light;

import com.engine.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.LinkedList;

public class PointLight extends Light {
    public static LinkedList<PointLight> Lights=new LinkedList<PointLight>();
    protected float radius=10;
    public PointLight(Vector3f position, Color color, float radius) {
        super(position, new Quaternionf(),color);
        this.radius=10;
    }
    public float gerRadius()
    {
        return radius;
    }

}
