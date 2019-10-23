package com.engine.entity;

import com.engine.rendering.Renderer;
import com.engine.util.Texture;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class SpriteRenderer extends RectEntity {
    public SpriteRenderer(Vector3f position, Quaternionf rotation, float scale, Vector2f size, PivotPoint pivot, Texture texture) {
        super(position, rotation, scale, size, pivot);
    }

    @Override
    public void render() {
        super.render();
        //Renderer.addToRenderQueue(this);
    }
}
