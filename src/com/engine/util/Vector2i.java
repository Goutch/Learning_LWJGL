package com.engine.util;


public class Vector2i {
    public static final Vector2i ONE = new Vector2i(1, 1);
    public static final Vector2i ZERO = new Vector2i(0, 0);
    public static final Vector2i RIGHT = new Vector2i(1, 0);
    public static final Vector2i LEFT = new Vector2i(-1, 0);
    public static final Vector2i UP = new Vector2i(0, 1);
    public static final Vector2i DOWN = new Vector2i(0, -1);
    public int x = 0, y = 0;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
