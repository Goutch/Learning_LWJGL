package com.engine.graphics.geometry;

import com.engine.graphics.Color;
import com.engine.events.RenderListener;
import com.engine.util.Vector2f;

public abstract class Geometry implements RenderListener {
    protected  Color color;
    protected Vector2f position;

    Geometry(Vector2f position,Color color)
    {
        this.position=position;
        this.color=color;
    }

    @Override
    public abstract void render();
}
