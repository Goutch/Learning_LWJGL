package com.engine.entity;

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
    public Vector3f position=new Vector3f();
    public Vector3f rotation=new Vector3f();
    public float scale;
    private Matrix4f transformMatrix;
    public Transform(Vector3f position,Vector3f rotation,float scale)
    {
        this.position.set(position);
        this.rotation.set(rotation);
        this.scale=scale;
    }

    public Matrix4f toTranformMatrix()
    {
        transformMatrix.identity();
        transformMatrix.translate(position);
        transformMatrix.rotate((float) Math.toRadians(rotation.x),RIGHT);
        transformMatrix.rotate((float) Math.toRadians(rotation.y),UP);
        transformMatrix.rotate((float) Math.toRadians(rotation.z),FOWARD);
        transformMatrix.scale(scale);
        return transformMatrix;
    }



}
