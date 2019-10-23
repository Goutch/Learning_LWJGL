package com.engine.entity.gui;

import com.engine.entity.RectEntity;
import com.engine.events.EventManager;
import com.engine.events.WindowResizeListener;
import com.engine.geometry.Geometry;
import com.engine.geometry.Mesh;
import com.engine.materials.GUIMaterial;
import com.engine.rendering.GUIRenderer;
import com.engine.rendering.Window;
import com.engine.util.Texture;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Panel extends RectEntity {

    protected GUIMaterial material;


    public Panel(Vector3f position, Vector2f size, PivotPoint pivotPoint, GUIMaterial material) {
        super(position, new Quaternionf(), 1f,size,pivotPoint);
        this.material = material;
    }

    public Panel(Vector3f position, Vector2f size, PivotPoint pivotPoint) {
        this(position, size, pivotPoint, GUIMaterial.DEFAULT);
    }
    public Panel(Vector3f position, Vector2f size, GUIMaterial material) {
        this(position, size, PivotPoint.CENTER, material);
    }

    public Panel(Vector3f position, Vector2f size) {
        this(position, size, PivotPoint.CENTER, GUIMaterial.DEFAULT);
    }

    public Vector2f getSize() {
        return new Vector2f(size);
    }


    public Panel fitTexture() {
        if (material.hasTexture()) {
            Texture texture = material.texture();
            this.setSize(new Vector2f(size.x, size.x * ((float) texture.height() / texture.width())));
        }
        return this;
    }

    public GUIMaterial getMaterial() {
        return material;
    }

    public void setMaterial(GUIMaterial material) {
        this.material = material;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    @Override
    public void render() {
        super.render();
        GUIRenderer.addToRenderQueue(this);
    }
}
