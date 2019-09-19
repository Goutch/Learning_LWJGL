package com.engine.geometry;

import com.engine.util.Color;

import com.engine.util.Vector2f;

public abstract class Geometry {
    protected  Color color;
    protected Vector2f position;

    Geometry(Vector2f position,Color color)
    {
        this.position=position;
        this.color=color;
    }
}
