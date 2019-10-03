package com.engine.entities.light;

import com.engine.entities.Entity;
import com.engine.entities.Transform;
import com.engine.util.Color;
import org.joml.Vector3f;

public class DirectionalLight extends Entity {
    public static DirectionalLight main=new DirectionalLight(new Vector3f(0,10,0), Transform.ZERO,Color.WHITE,10);
    float intensity=10;
    public Color color;
    public DirectionalLight(Vector3f position, Vector3f rotation, Color color,float intensity) {
        super(position, rotation, 1f);
        this.color=color;
    }
    public Vector3f getDirection(){
        return new Vector3f((float)Math.cos(Math.toRadians(transform.rotation.z)),(float)Math.cos(Math.toRadians(transform.rotation.x)),(float)Math.cos(Math.toRadians(transform.rotation.y)));
    }
}
