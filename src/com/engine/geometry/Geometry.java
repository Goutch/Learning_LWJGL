package com.engine.geometry;


import com.engine.events.RenderListener;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.util.Color;

public class Geometry implements RenderListener{
    protected ShaderProgram shader;
    protected int vaoID;
    protected int vertexCount;
    public Geometry(float[] vertices, int[] indexes, ShaderProgram shader)
    {
        this.vaoID= GeometryLoader.loadToVAO(vertices,indexes);
        this.shader=shader;
        this.vertexCount=vertices.length;
    }
    public Geometry(float[] vertices, int[] indexes,Color[] colors, ShaderProgram shader)
    {
        float[] vertexColors=new float[colors.length*3];
        for (int i=0;i<colors.length;i++)
        {
            vertexColors[i*3]=colors[i].r;
            vertexColors[i*3+1]=colors[i].g;
            vertexColors[i*3+2]=colors[i].b;
        }
        this.vaoID= GeometryLoader.loadToVAO(vertices,indexes,vertexColors,3);
        this.shader=shader;
        this.vertexCount=vertices.length;
    }
    protected Geometry(){}
    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
    @Override
    public void render() {
        shader.start();
        Renderer.render(this,shader.getAtributeCount());
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
        public static final float[] UVS={

                1f,0f,
                1f,1f,
                0f,1f,
                0f,0f

        };
    }
}
