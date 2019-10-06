package com.engine.entity.entity3D;

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
    protected Vector3f position;
    protected Vector3f rotation;
    protected float scale;
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
    public void translate(Vector3f translation)
    {
        position.add(translation);
    }
    public void setPosition(Vector3f position)
    {
        this.position.set(position);
    }
    public Vector3f getPosition()
    {
        return new Vector3f().set(position);
    }
    public float px()
    {
        return position.x;
    }
    public void px(float x)
    {
        position.x=x;
    }
    public float py()
    {
        return position.y;
    }
    public void py(float y)
    {
        position.y=y;
    }
    public float pz()
    {
        return position.z;
    }
    public void pz(float z)
    {
        position.z=z;
    }
    public void rotate(Vector3f rotation)
    {
        this.rotation.add(rotation);
    }
    public Vector3f getRotation()
    {
        return new Vector3f().set(rotation);
    }
    public float rx()
    {
        return rotation.x;
    }
    public void rx(float x)
    {
       rotation.x=x;
    }
    public float ry()
    {
        return rotation.y;
    }
    public void ry(float y)
    {
        rotation.y=y;
    }
    public float rz()
    {
        return rotation.z;
    }
    public void rz(float z)
    {
        rotation.z=z;
    }

}
