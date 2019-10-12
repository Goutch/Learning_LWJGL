package com.engine.entity;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
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
    private Matrix4f transformMatrix=new Matrix4f();
    private Vector3f localPosition =new Vector3f();
    private Quaternionf localRotation =new Quaternionf();
    private float localScale=1f;
    private Quaternionf rotation=new Quaternionf();
    private Vector3f position=new Vector3f();
    private float scale;
    private Transform parent;


    public Transform(Vector3f position,Quaternionf rotation,float scale)
    {
        localPosition.set(position);
        localRotation.set(rotation);
        localScale=scale;
        computeTransformMatrix();
    }
    public Vector3f getLocalPosition()
    {
        return new Vector3f().set(localPosition);
    }
    public Vector3f getGlobalPosition()
    {
        position=parent==null?localPosition:parent.getGlobalPosition().add(localPosition);
        return new Vector3f().set(position);
    }
    public Quaternionf getLocalRotation()
    {
        return new Quaternionf().set(localRotation);
    }
    public Quaternionf getGlobalRotation()
    {
        rotation=parent==null?transformMatrix.getNormalizedRotation(rotation):parent.getTransformMatrix().mul(transformMatrix).getNormalizedRotation(rotation);
        return new Quaternionf().set(rotation);
    }
    public float getLocalScale()
    {
        return scale;
    }
    public float getGlobalScale()
    {
        return parent==null?localScale:parent.getTransformMatrix().mul(transformMatrix).getScale(new Vector3f()).x;
    }
    public void computeTransformMatrix()
    {
        transformMatrix.identity();
        transformMatrix.translate(localPosition);
        transformMatrix.rotate(localRotation);
        transformMatrix.scale(localScale);
    }
    public Matrix4f getTransformMatrix()
    {
        Matrix4f mat=parent==null?
                new Matrix4f().set(transformMatrix):
                new Matrix4f().set(parent.getTransformMatrix()).mul(transformMatrix);
        return mat;
    }

    public void translate(Vector3f translation) {
        localPosition.add(translation);
        computeTransformMatrix();
    }
    public void rotate(float angle,Vector3f axis){
        localRotation.rotateAxis(angle,axis);
        computeTransformMatrix();
    }

    public void setPosition(Vector3f position) {
        localPosition.set(position);
        computeTransformMatrix();
    }
    public void setRotation(Quaternionf rotation)
    {
        localRotation.set(rotation);
        computeTransformMatrix();
    }
    public void setScale()
    {
        transformMatrix.scale(scale);
        computeTransformMatrix();
    }
    public Vector3f forward()
    {
        Quaternionf q=getGlobalRotation();
        return new Vector3f(2 * (q.x*q.z + q.w*q.y),2 * (q.y*q.z - q.w*q.x),1 - 2 * (q.x*q.x + q.y*q.y));
    }
    public Vector3f up()
    {
        Quaternionf q=getGlobalRotation();
        return new Vector3f(2 * (q.x*q.y - q.w*q.z), 1 - 2 * (q.x*q.x + q.z*q.z),2 * (q.y*q.z + q.w*q.x));
    }
    public Vector3f left()
    {
        Quaternionf q=getGlobalRotation();
        return new Vector3f(1 - 2 * (q.y*q.y + q.z*q.z),2 * (q.x*q.y + q.w*q.z),2 * (q.x*q.z - q.w*q.y));
    }
    public void setParent(Transform parent) {

        this.parent = parent;
    }
}
