package com.engine.geometry;


import com.engine.events.RenderListener;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.ShaderProgram;

public class Geometry implements RenderListener{
    private ShaderProgram shader;
    private int vaoID;
    private int vertexCount;
    public Geometry(float[] vertices,int[] indexes ,ShaderProgram shader)
    {
        this.vaoID= GeometryLoader.loadToVAO(vertices,indexes);
        this.shader=shader;
        this.vertexCount=vertices.length;
    }
    public int getVoaID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
    @Override
    public void render() {
        shader.start();
        Renderer.render(this);
        shader.stop();
    }

    public static class Triangle {
        public static final float[] VERTICES = {
                0f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
        };
        public static final int[] INDEXES={
                1,2,3
        };
    }
    public static class Quad{
        public static final float[] VERTICES = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };
        public static final int[] INDEXES={
                0,1,3,
                3,2,1
        };
    }
}
