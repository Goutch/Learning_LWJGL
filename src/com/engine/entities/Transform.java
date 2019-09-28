package com.engine.entities;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Represents the position, the rotation , and the scale of an entity.
 */
public class Transform {
    public static final Vector3f ONE = new Vector3f(1, 1,1);
    public static final Vector3f ZERO = new Vector3f(0, 0,0);
    public static final Vector3f FOWARD=new Vector3f(0,0,1);
    public static final Vector3f BACKWARD=new Vector3f(0,0,-1);
    public static final Vector3f UP=new Vector3f(0,1,0);
    public static final Vector3f DOWN=new Vector3f(0,-1,0);
    public static final Vector3f RIGHT=new Vector3f(1,0,0);
    public static final Vector3f LEFT=new Vector3f(-1,0,0);
    public Vector3f position;
    public Vector3f rotation;
    public float scale;
    public Transform(Vector3f position,Vector3f rotation,float scale)
    {
        this.position=position;
        this.rotation=rotation;
        this.scale=scale;
    }
    public Matrix4f toTranformMatrix()
    {
        Matrix4f matrix=new Matrix4f();
        matrix.identity();
        matrix.translate(position);
        matrix.rotate((float) Math.toRadians(rotation.x),RIGHT);
        matrix.rotate((float) Math.toRadians(rotation.y),UP);
        matrix.rotate((float) Math.toRadians(rotation.z),FOWARD);
        matrix.scale(scale);
        return matrix;
    }
}
