package com.engine.util;

public class Vector3f {
    public static final Vector2f ONE = new Vector2f(1, 1);
    public static final Vector2f ZERO = new Vector2f(0, 0);
    public static final Vector2f RIGHT = new Vector2f(1, 0);
    public static final Vector2f LEFT = new Vector2f(-1, 0);
    public static final Vector2f UP = new Vector2f(0, 1);
    public static final Vector2f DOWN = new Vector2f(0, -1);

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


    public void add(Vector3f other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }
}
