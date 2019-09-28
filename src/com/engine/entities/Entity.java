package com.engine.entities;

import com.engine.events.EventManager;
import com.engine.events.RenderListener;
import com.engine.geometry.Mesh;
import com.engine.rendering.Renderer;
import org.joml.Vector3f;

/**
 * Represents the base class for all game entities
 */
public class Entity{
    public Transform transform;

    public Entity( Vector3f position, Vector3f rotation, float scale)
    {
        transform=new Transform(position,rotation,scale);
    }
}
