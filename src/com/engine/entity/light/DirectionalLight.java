package com.engine.entity.light;

import com.engine.entity.Transform;
import com.engine.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class DirectionalLight extends Light {
    public static DirectionalLight main=new DirectionalLight(new Vector3f(0,0,0), new Quaternionf(),Color.WHITE);
    public DirectionalLight(Vector3f position, Quaternionf rotation, Color color) {
        super(position, rotation, color);
    }
   //public Vector3f getDirection(){
   //    return new Vector3f((float)Math.cos(Math.toRadians(transform.rotation.z)),(float)Math.cos(Math.toRadians(transform.rotation.x)),(float)Math.cos(Math.toRadians(transform.rotation.y)));
   //}
}
