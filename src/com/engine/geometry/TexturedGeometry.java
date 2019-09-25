package com.engine.geometry;

import com.engine.rendering.Renderer;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.util.Texture;

public class TexturedGeometry extends Geometry {
    Texture texture;
    protected float[] uvs;
    public TexturedGeometry(Texture texture, float[] vertices, int[] indexes, float[] uvs, ShaderProgram shader) {
        this.vaoID= GeometryLoader.loadToVAO(vertices,indexes,uvs);
        this.shader=shader;
        this.vertexCount=vertices.length;
        this.texture=texture;
        this.uvs=uvs;
    }
    @Override
    public void render()
    {
        shader.start();
        texture.bind();
        Renderer.render(this,shader.getAtributeCount());
        texture.unbind();
        shader.stop();
    }
    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
