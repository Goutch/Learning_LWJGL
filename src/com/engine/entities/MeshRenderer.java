package com.engine.entities;

import com.engine.events.RenderListener;
import com.engine.geometry.Mesh;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.util.Texture;
import org.joml.Vector3f;

public class MeshRenderer extends Entity implements RenderListener {

    private Mesh mesh;
    protected ShaderProgram shader;
    protected Texture texture;

    /**
     *
     * @param position
     * @param rotation
     * @param scale
     * @param mesh
     * @param shader
     */
    public MeshRenderer(Vector3f position, Vector3f rotation, float scale, Mesh mesh, ShaderProgram shader) {
        super(position, rotation, scale);
        this.mesh = mesh;
        this.shader = shader;
    }

    /**
     *
     * @param position
     * @param rotation
     * @param scale
     * @param mesh
     * @param texture
     * @param shader
     */
    public MeshRenderer(Vector3f position, Vector3f rotation, float scale, Mesh mesh, Texture texture, ShaderProgram shader) {
        this(position, rotation, scale, mesh, shader);
        setTexture(texture);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * bind the texture for rendering if the texture is not null
     */
    public void bindTexture() {
        if (texture != null)
            texture.bind();
    }

    /**
     * unbind the texture for rendering if the texture is not null
     */
    public void unBindTexture() {
        if (texture != null)
            texture.unbind();
    }

    @Override
    public void render() {
        Renderer.render(this);
    }
}
