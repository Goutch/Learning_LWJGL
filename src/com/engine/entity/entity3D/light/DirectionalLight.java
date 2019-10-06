package com.engine.entity.entity3D.light;

import com.engine.entity.entity3D.Entity3D;
import com.engine.entity.entity3D.Transform;
import com.engine.util.Color;
import org.joml.Vector3f;

public class DirectionalLight extends Entity3D {
    public static DirectionalLight main=new DirectionalLight(new Vector3f(0,10,0), Transform.ZERO,Color.WHITE,10);
    float intensity=10;
    public Color color;
    public DirectionalLight(Vector3f position, Vector3f rotation, Color color,float intensity) {
        super(position, rotation, 1f);
        this.color=color;
    }
   //public Vector3f getDirection(){
   //    return new Vector3f((float)Math.cos(Math.toRadians(transform.rotation.z)),(float)Math.cos(Math.toRadians(transform.rotation.x)),(float)Math.cos(Math.toRadians(transform.rotation.y)));
   //}
}
