package com.engine.geometry;


import com.engine.rendering.shader.BaseShader;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.rendering.shader.TextureShader;
import com.engine.rendering.shader.VertexColorShader;
import com.engine.util.Color;
import com.engine.util.Texture;
import com.sun.prism.ps.Shader;

public class Mesh {
    protected float[] vertices;
    protected float[] uvs;
    protected int[] indexes;
    protected float[] colors;
    protected Texture texture;

    protected ShaderProgram shader;
    protected int vaoID;

    public Mesh(float[] vertices, int[] indexes, ShaderProgram shader)
    {
        this.vertices=vertices;
        this.indexes=indexes;
        this.vaoID= MeshLoader.loadToVAO(vertices,indexes);
        this.shader=shader;
    }
    public Mesh(float[] vertices, int[] indexes, Color[] colors, VertexColorShader shader)
    {
        this.vertices=vertices;
        this.indexes=indexes;
        setColors(colors);
        this.vaoID= MeshLoader.loadToVAO(vertices,indexes,this.colors,3);
        this.shader=shader;
    }
    public Mesh(float[] vertices, int[] indexes, float[] uvs, Texture texture, TextureShader shader)
    {
        this.vertices=vertices;
        this.indexes=indexes;
        this.uvs=uvs;
        this.texture=texture;
        this.vaoID= MeshLoader.loadToVAO(vertices,indexes,uvs);
        this.shader=shader;
    }

    public void bindTexture()
    {
        if(texture!=null)
            texture.bind();
    }
    public void unBindTexture()
    {
        if(texture!=null)
            texture.unbind();
    }

    public void clear()
    {

    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertices.length;
    }

    public ShaderProgram getShader(){return shader;}

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
    public void setColors(Color[] colors)
    {
        this.colors=new float[colors.length*3];
        for (int i=0;i<colors.length;i++)
        {
            this.colors[i*3]=colors[i].r;
            this.colors[i*3+1]=colors[i].g;
            this.colors[i*3+2]=colors[i].b;
        }
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
