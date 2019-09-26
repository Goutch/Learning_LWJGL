package com.engine.entities;

import com.engine.events.RenderListener;
import com.engine.geometry.Geometry;
import com.engine.rendering.Renderer;
import org.joml.Vector3f;

public class GameEntity implements RenderListener {
    protected Geometry geometry;
    public Transform transform;


    protected float scale;
    public GameEntity(Geometry geometry, Vector3f position, Vector3f rotation, float scale)
    {
        this.geometry=geometry;
        transform=new Transform(position,rotation,scale);
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public void render() {
        Renderer.render(this);
    }
}
