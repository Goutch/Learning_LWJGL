package com.engine.entity;

import com.engine.events.DisposeListener;
import com.engine.events.InitListener;
import com.engine.events.RenderListener;
import com.engine.events.UpdateListener;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Entity implements RenderListener, UpdateListener  {
    private static long current_entities_id = 0;
    public final long id = current_entities_id;
    public String name;
    public boolean enabled = true;
    public Transform transform;

    public Entity(Vector3f position, Quaternionf rotation, float scale) {
        current_entities_id++;
        transform = new Transform(position, rotation, scale);
    }
    public Entity(Vector3f position, Quaternionf rotation, float scale,Transform parent) {
        current_entities_id++;
        transform = new Transform(position, rotation, scale);
    }

    @Override
    public void render() {
        if (!enabled) {
            return;
        }
    }

    @Override
    public void update(float deltaTime) {
        if (!enabled) {
            return;
        }
    }
}
