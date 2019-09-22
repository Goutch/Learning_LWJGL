package com.engine.math;

public class Vector3f {
    public static final Vector3f ONE = new Vector3f(1, 1,1);
    public static final Vector3f ZERO = new Vector3f(0, 0,0);
    public static final Vector3f FOWARD=new Vector3f(0,0,1);
    public static final Vector3f BACKWARD=new Vector3f(0,0,-1);
    public static final Vector3f UP=new Vector3f(0,1,0);
    public static final Vector3f DOWN=new Vector3f(0,-1,0);
    public static final Vector3f RIGHT=new Vector3f(1,0,0);
    public static final Vector3f LEFT=new Vector3f(-1,0,0);

    public float x = 0, y = 0, z = 0;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() {
    }

    public void normalize() {

        float sum = x + y + z;
        if (sum != 0) {
            x /= sum;
            y /= sum;
            z /= sum;
        }
    }
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }
    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }
    public void add(Vector3f other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }
    public void multiply(float ammount){
        this.x *= ammount;
        this.y *= ammount;
        this.z *= ammount;
    }
    public void multiply(Vector3f other){
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.x;
    }
}
