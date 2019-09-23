package com.engine.geometry;

import com.engine.events.EventManager;
import com.engine.events.RenderListener;
import com.engine.rendering.Renderer;
import com.engine.rendering.shader.ShaderProgram;

public class Geometry implements RenderListener {
    public static class Triangle {
        public static final float[] VERTECES = {
            0f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
        };
        public static final int[] INDEXES={
                1,2,3
        };
    }
    public static class Quad{
        public static final float[] VERTECES = {
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
    private ShaderProgram shader;
    private int vaoID;
    private int vertexCount;
    public Geometry(float[] verteces,int[] indexes ,ShaderProgram shader)
    {
        this.vaoID=Loader.loadToVAO(verteces,indexes);
        EventManager.subscribeRender(this);

        this.shader=shader;
        this.vertexCount=verteces.length;
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
}
