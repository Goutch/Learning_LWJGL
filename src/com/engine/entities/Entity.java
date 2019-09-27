package com.engine.entities;

import com.engine.events.EventManager;
import com.engine.events.RenderListener;
import com.engine.geometry.Mesh;
import com.engine.rendering.Renderer;
import org.joml.Vector3f;

public class Entity implements RenderListener {
    protected Mesh mesh;
    public Transform transform;

    public Entity(Mesh mesh, Vector3f position, Vector3f rotation, float scale)
    {
        this.mesh=mesh;
        transform=new Transform(position,rotation,scale);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    @Override
    public void render() {
        Renderer.render(this);
    }
}
