package com.engine.entities;

import com.engine.events.RenderListener;
import com.engine.geometry.Mesh;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.util.Texture;
import com.sun.prism.ps.Shader;
import org.joml.Vector3f;

public class MeshRenderer extends Entity implements RenderListener {

    private Mesh mesh;
    protected ShaderProgram shader;

    public MeshRenderer(Vector3f position, Vector3f rotation, float scale, Mesh mesh,ShaderProgram shader) {
        super(position,rotation,scale);
        this.mesh=mesh;
        this.shader=shader;
    }

    public Mesh getMesh()
    {
        return mesh;
    }
    public ShaderProgram getShader()
    {
        return shader;
    }
    public void setShader(ShaderProgram shader)
    {
        this.shader=shader;
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}
