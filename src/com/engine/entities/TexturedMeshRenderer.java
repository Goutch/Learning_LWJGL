package com.engine.entities;

import com.engine.geometry.TexturedMesh;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.TextureShader;
import com.engine.util.Texture;
import org.joml.Vector3f;

public class TexturedMeshRenderer extends MeshRenderer {
    protected Texture texture;

    public TexturedMeshRenderer(Vector3f position, Vector3f rotation, float scale, TexturedMesh mesh, Texture texture, TextureShader shader) {
        super(position, rotation, scale, mesh,shader);
        setTexture(texture);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    /**
     * bind the texture for rendering if the texture is not null
     */
    public void bindTexture() {
            texture.bind();
    }
    /**
     * unbind the texture for rendering if the texture is not null
     */
    public void unBindTexture() {
            texture.unbind();
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}
