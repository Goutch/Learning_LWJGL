package com.engine.entities;


import com.engine.geometry.ColoredMesh;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.VertexColorShader;
import org.joml.Vector3f;

public class ColoredMeshRenderer extends MeshRenderer{
    protected float[] colors;

    public ColoredMeshRenderer(Vector3f position, Vector3f rotation, float scale, ColoredMesh mesh, VertexColorShader shader)
    {
        super(position, rotation, scale, mesh,shader);
    }


    @Override
    public void render() {
        Renderer.render(this);
    }
}
