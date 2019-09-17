package com.engine.util;



public class Vector2f {
    public static final Vector2f ONE = new Vector2f(1, 1);
    public static final Vector2f ZERO = new Vector2f(0, 0);
    public static final Vector2f RIGHT = new Vector2f(1, 0);
    public static final Vector2f LEFT = new Vector2f(-1, 0);
    public static final Vector2f UP = new Vector2f(0, 1);
    public static final Vector2f DOWN = new Vector2f(0, -1);

    public float x = 0, y = 0;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void normalize() {

        float sum = x + y;
        if(sum!=0)
        {
            x /= sum;
            y /= sum;
        }

    }


    public void add(Vector2f other) {
        this.x += other.x;
        this.y += other.y;
    }
}
