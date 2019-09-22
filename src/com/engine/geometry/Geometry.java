package com.engine.geometry;

import com.engine.events.EventManager;
import com.engine.events.RenderListener;
import com.engine.util.Color;

import com.engine.math.Vector3f;

public abstract class Geometry implements RenderListener {
    protected  Color color;
    protected Vector3f position;
    protected float rotation;

    Geometry(Vector3f position,float rotation, Color color)
    {
        EventManager.subscribeRender(this);
        this.position=position;
        this.color=color;
        this.rotation=rotation;
    }
}
