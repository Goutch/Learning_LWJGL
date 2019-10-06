package com.engine.entity.entity3D;

import com.engine.entity.Entity;
import org.joml.Vector3f;

public class Entity3D extends Entity {
    public Transform transform;
    public Entity3D(Vector3f position, Vector3f rotation, float scale)
    {
        super();
        transform=new Transform(position,rotation,scale);
    }
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
