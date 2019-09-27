package com.engine.geometry;


import com.engine.events.DisposeListener;
import com.engine.events.EventManager;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.rendering.shader.TextureShader;
import com.engine.rendering.shader.VertexColorShader;
import com.engine.util.Color;
import com.engine.util.Texture;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class Mesh implements DisposeListener {
    protected float[] vertices;
    protected float[] uvs;
    protected int[] indexes;
    protected float[] colors;
    protected Texture texture;

    protected ShaderProgram shader;
    protected int vaoID;
    protected int[] vbos;

    private void init() {
        EventManager.subscribeDispose(this);
        this.vaoID = MeshLoader.getVAO();
    }
    public Mesh() {
        init();
    }
    public Mesh(float[] vertices, int[] indexes, ShaderProgram shader) {
        init();
        this.vertices = vertices;
        this.indexes = indexes;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes);
        this.shader = shader;
    }

    public Mesh(float[] vertices, int[] indexes, Color[] colors, VertexColorShader shader) {
        init();
        setColors(colors);
        this.vertices = vertices;
        this.indexes = indexes;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes, this.colors, 3);
        this.shader = shader;
    }

    public Mesh(float[] vertices, int[] indexes, float[] uvs, Texture texture, TextureShader shader) {
        init();
        this.vertices = vertices;
        this.indexes = indexes;
        this.uvs = uvs;
        this.texture = texture;
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes, uvs);
        this.shader = shader;
    }

    public void bindTexture() {
        if (texture != null&&uvs!=null)
            texture.bind();
    }

    public void unBindTexture() {
        if (texture != null&&uvs!=null)
            texture.unbind();
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertices.length;
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getUvs() {
        return uvs;
    }

    public int[] getIndexes() {
        return indexes;
    }

    public float[] getColors() {
        return colors;
    }

    public void setVertices(float[] vertices,int[] indexes) {
        this.vertices = vertices;
        this.indexes = indexes;
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        this.vbos = MeshLoader.loadToVAO(vaoID, vertices, indexes);
    }

    public void setColors(float[] colors) {
        this.colors = colors;
    }

    public void setColors(Color[] colors) {
        this.colors = new float[colors.length * 3];
        for (int i = 0; i < colors.length; i++) {
            this.colors[i * 3] = colors[i].r;
            this.colors[i * 3 + 1] = colors[i].g;
            this.colors[i * 3 + 2] = colors[i].b;
        }
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setUvs(float[] uvs) {
        this.uvs = uvs;
    }

    @Override
    public void dispose() {
        EventManager.unSubscribeDispose(this);
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        glDeleteVertexArrays(vaoID);
    }
}