package com.engine.entities;

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
